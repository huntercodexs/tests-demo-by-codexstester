package codexstester.engine.external;

import codexstester.engine.dto.HeadersDto;
import codexstester.engine.dto.RequestDto;
import codexstester.engine.internal.InternalRequest1xxTests;
import org.junit.Assert;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;

import static codexstester.engine.util.CodexsHelperTests.codexsHelperLogTerm;

public abstract class AbstractExternalRequestTests extends InternalRequest1xxTests {

    protected AbstractExternalRequestTests(String target) {
        super(target);
    }

    void executeExternalTest(RequestDto requestDto, HeadersDto headersDto) throws Exception {

        /*Fix a bug when getHttpMethod is null*/
        try {
            String method = headersDto.getHttpMethod();
            if (method == null) headersDto.setHttpMethod(HTTP_METHOD_TESTER);
        } catch (Exception ex) {
            headersDto.setHttpMethod(HTTP_METHOD_TESTER);
        }

        switch (headersDto.getHttpMethod()) {
            case HTTP_METHOD_GET:
                assertResultFromRequestByHttpGet(requestDto, headersDto);
                break;
            case HTTP_METHOD_POST:
                assertResultFromRequestByHttpPost(requestDto, headersDto);
                break;
            case HTTP_METHOD_PUT:
                assertResultFromRequestByHttpPut(requestDto, headersDto);
                break;
            case HTTP_METHOD_DELETE:
                assertResultFromRequestByHttpDelete(requestDto, headersDto);
                break;
            case HTTP_METHOD_PATCH:
                assertResultFromRequestByHttpPatch(requestDto, headersDto);
                break;
            case HTTP_METHOD_HEAD:
                assertResultFromRequestByHttpHead(requestDto, headersDto);
                break;
            case HTTP_METHOD_OPTIONS:
                assertResultFromRequestByHttpOptions(requestDto, headersDto);
                break;
            default:
                assertResultExternalTest();
        }
    }

    private void dispatcher(RequestDto requestDto, HeadersDto headersDto, String method) {

        String uri = externalUriBaseTest;

        if (requestDto.getUri() != null && !requestDto.getUri().equals("")) uri = requestDto.getUri();
        if (requestDto.getId() != null && !requestDto.getId().equals("")) uri = uri +"/"+ requestDto.getId();

        String url = externalUrlBaseTest + uri;
        HttpEntity<?> httpEntity = new HttpEntity<>(requestDto.getDataRequest(), externalBuilderHeaders(requestDto, headersDto));

        if (externalUrlQueryParameters != null && !externalUrlQueryParameters.equals("")) {
            url = url + "?" + externalUrlQueryParameters;
        }

        codexsHelperLogTerm("EXTERNAL REQUEST URL IS", url, true);
        codexsHelperLogTerm("HTTP METHOD IS", method, true);

        try {

            ResponseEntity<?> response = null;
            Class<?> responseType = Object.class;

            if (requestDto.getExpectedMessage() != null && !requestDto.getExpectedMessage().equals("")) {
                responseType = String.class;
            }

            switch (method) {
                case HTTP_METHOD_GET:
                    codexsHelperLogTerm("SEND REQUEST BY exchange GET [Object]", url, true);
                    response = genericRestTemplate.exchange(url, HttpMethod.GET, httpEntity, responseType);
                    break;
                case HTTP_METHOD_POST:
                    codexsHelperLogTerm("SEND REQUEST BY postForEntity POST [Object]", url, true);
                    response = genericRestTemplate.postForEntity(url, httpEntity, responseType);
                    break;
                case HTTP_METHOD_DELETE:
                    codexsHelperLogTerm("SEND REQUEST BY exchange DELETE [Object]", url, true);
                    response = genericRestTemplate.exchange(url, HttpMethod.DELETE, httpEntity, responseType);
                    break;
                case HTTP_METHOD_PUT:
                    codexsHelperLogTerm("SEND REQUEST BY exchange PUT [Object]", url, true);
                    response = genericRestTemplate.exchange(url, HttpMethod.PUT, httpEntity, responseType);
                    break;
                case HTTP_METHOD_PATCH:
                    genericRestTemplate.setRequestFactory(externalHttpClientFactory());
                    codexsHelperLogTerm("SEND REQUEST BY exchange PATCH [Object]", url, true);
                    response = genericRestTemplate.exchange(url, HttpMethod.PATCH, httpEntity, responseType);
                    break;
                case HTTP_METHOD_HEAD:
                    codexsHelperLogTerm("SEND REQUEST BY exchange HEAD [Object]", url, true);
                    response = genericRestTemplate.exchange(url, HttpMethod.HEAD, httpEntity, responseType);
                    break;
                case HTTP_METHOD_OPTIONS:
                    codexsHelperLogTerm("SEND REQUEST BY exchange OPTIONS [Object]", url, true);
                    response = genericRestTemplate.exchange(url, HttpMethod.OPTIONS, httpEntity, responseType);
                    break;
                default:
                    throw new RuntimeException("INVALID HTTP METHOD: " + method);
            }

            codexsHelperLogTerm("EXTERNAL RESPONSE IS", response, true);
            codexsHelperLogTerm("EXTERNAL STATUS CODE IS", response.getStatusCode(), true);

            Assert.assertEquals(requestDto.getExpectedCode(), response.getStatusCodeValue());

            if (requestDto.getExpectedMessage() != null && !requestDto.getExpectedMessage().equals("")) {
                codexsHelperLogTerm("RESPONSE[BODY] MATCH", response.getBody(), true);
                Assert.assertEquals(requestDto.getExpectedMessage(), response.getBody());
                resulted(true);
            }

        } catch (HttpClientErrorException ex) {

            codexsHelperLogTerm("HttpClientErrorException[MESSAGE]:", ex.getMessage(), true);
            codexsHelperLogTerm("HttpClientErrorException[BODY]:", ex.getResponseBodyAsString(), true);
            codexsHelperLogTerm("HttpClientErrorException[CODE]:", ex.getStatusCode(), true);

            Assert.assertEquals(requestDto.getExpectedCode(), ex.getRawStatusCode());

            if (requestDto.getExpectedMessage() != null && !requestDto.getExpectedMessage().equals("")) {
                if (!ex.getResponseBodyAsString().equals("")) {
                    codexsTesterAssertExact(requestDto.getExpectedMessage(), ex.getResponseBodyAsString());
                } else {

                    String warn = "+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++\n";
                    warn += "IS NOT WAS POSSIBLE COMPARE THE RECEIVED RESPONSE BECAUSE IT IS EMPTY\n";
                    warn += "IF REQUIRED ALSO COMPARE THE RESPONSE, YOU CAN BE USE THE ADVANCED TESTS\n";
                    warn += "PLEASE, FOR MORE DETAILS GIVE A LOOK IN THE DOCUMENTATION ON GITHUB (README.md)\n";
                    warn += "+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++";
                    codexsHelperLogTerm("!!! W A R N I N G !!!", warn, false);

                    Assert.assertTrue(true);
                    resulted(true);
                }
            }

        } catch (HttpServerErrorException se) {

            codexsHelperLogTerm("HttpServerErrorException[MESSAGE]:", se.getMessage(), true);
            codexsHelperLogTerm("HttpServerErrorException[BODY]:", se.getResponseBodyAsString(), true);
            codexsHelperLogTerm("HttpServerErrorException[CODE]:", se.getStatusCode(), true);

            Assert.assertEquals(requestDto.getExpectedCode(), se.getRawStatusCode());

            if (requestDto.getExpectedMessage() != null && !requestDto.getExpectedMessage().equals("")) {
                if (!se.getResponseBodyAsString().equals("")) {
                    codexsTesterAssertExact(requestDto.getExpectedMessage(), se.getResponseBodyAsString());
                } else {

                    String warn = "+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++\n";
                    warn += "IS NOT WAS POSSIBLE COMPARE THE RECEIVED RESPONSE BECAUSE IT IS EMPTY\n";
                    warn += "IF REQUIRED ALSO COMPARE THE RESPONSE, YOU CAN BE USE THE ADVANCED TESTS\n";
                    warn += "PLEASE, FOR MORE DETAILS GIVE A LOOK IN THE DOCUMENTATION ON GITHUB (README.md)\n";
                    warn += "+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++";
                    codexsHelperLogTerm("!!! W A R N I N G !!!", warn, false);

                    Assert.assertTrue(true);
                    resulted(true);
                }
            }

        } catch (RuntimeException re) {
            codexsHelperLogTerm("RuntimeException[MESSAGE]:", re.getMessage(), true);
            resulted(false);
            Assert.fail("RuntimeException[MESSAGE]: " + re.getMessage());
        }
    }

    /**
     * @apiNote Using Http GET with Rest Template
     */
    private void assertResultFromRequestByHttpGet(RequestDto requestDto, HeadersDto headersDto) throws Exception {
        dispatcher(requestDto, headersDto, HTTP_METHOD_GET);
    }

    /**
     * @apiNote Using Http POST with Rest Template
     */
    private void assertResultFromRequestByHttpPost(RequestDto requestDto, HeadersDto headersDto) throws Exception {
        dispatcher(requestDto, headersDto, HTTP_METHOD_POST);
    }

    /**
     * @apiNote Using Http DELETE with Rest Template
     */
    private void assertResultFromRequestByHttpDelete(RequestDto requestDto, HeadersDto headersDto) throws Exception {
        dispatcher(requestDto, headersDto, HTTP_METHOD_DELETE);
    }

    /**
     * @apiNote Using Http PUT with Rest Template
     */
    private void assertResultFromRequestByHttpPut(RequestDto requestDto, HeadersDto headersDto) throws Exception {
        dispatcher(requestDto, headersDto, HTTP_METHOD_PUT);
    }

    /**
     * @apiNote Using Http PATCH with Rest Template
     */
    private void assertResultFromRequestByHttpPatch(RequestDto requestDto, HeadersDto headersDto) throws Exception {
        dispatcher(requestDto, headersDto, HTTP_METHOD_PATCH);
    }

    /**
     * @apiNote Using Http HEAD with Rest Template
     */
    private void assertResultFromRequestByHttpHead(RequestDto requestDto, HeadersDto headersDto) throws Exception {
        dispatcher(requestDto, headersDto, HTTP_METHOD_HEAD);
    }

    /**
     * @apiNote Using Http OPTIONS with Rest Template
     */
    private void assertResultFromRequestByHttpOptions(RequestDto requestDto, HeadersDto headersDto) throws Exception {
        dispatcher(requestDto, headersDto, HTTP_METHOD_OPTIONS);
    }

    private void assertResultExternalTest() {
        System.out.println("CODEXS TESTER IS RUNNING IN EXTERNAL MODE");
    }

}