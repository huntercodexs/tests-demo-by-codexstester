package codexstester.engine.external;

import codexstester.engine.dto.HeadersDto;
import codexstester.engine.dto.RequestDto;
import org.junit.Assert;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;

import static codexstester.engine.util.CodexsHelperTests.codexsHelperLogTerm;

public abstract class AbstractExternalRestTemplateTests extends AbstractExternalRequestTests {

    protected AbstractExternalRestTemplateTests(String target) {
        super(target);
    }

    protected ResponseEntity<?> codexsTesterExternalDispatcher(RequestDto requestDto, HeadersDto headersDto) {

        try {
            int code = requestDto.getExpectedCode();
        } catch (RuntimeException re) {
            String err = "Missing HTTP-STATUS from RequestDto: use setExpectedCode";
            throw new RuntimeException(err);
        }

        String uri = externalUriBaseTest;
        String method = headersDto.getHttpMethod();

        if (requestDto.getUri() != null && !requestDto.getUri().equals("")) uri = requestDto.getUri();
        if (requestDto.getId() != null && !requestDto.getId().equals("")) uri = uri +"/"+ requestDto.getId();

        String url = externalUrlBaseTest + uri;
        HttpEntity<?> httpEntity = new HttpEntity<>(requestDto.getDataRequest(), externalBuilderHeaders(requestDto, headersDto));

        if (externalUrlQueryParameters != null && !externalUrlQueryParameters.equals("")) {
            url = url + "?" + externalUrlQueryParameters;
        }

        codexsHelperLogTerm("EXTERNAL DISPATCHER REQUEST URL IS", url, true);
        codexsHelperLogTerm("HTTP METHOD IS", method, true);

        ResponseEntity<?> response = null;

        try {

            switch (method) {
                case HTTP_METHOD_GET:
                    try {
                        response = genericRestTemplate.exchange(url, HttpMethod.GET, httpEntity, Object.class);
                    } catch (Exception ex) {
                        response = genericRestTemplate.exchange(url, HttpMethod.GET, httpEntity, String.class);
                    }
                    break;
                case HTTP_METHOD_POST:
                    try {
                        response = genericRestTemplate.postForEntity(url, httpEntity, Object.class);
                    } catch (Exception ex) {
                        response = genericRestTemplate.postForEntity(url, httpEntity, String.class);
                    }
                    break;
                case HTTP_METHOD_DELETE:
                    try {
                        response = genericRestTemplate.exchange(url, HttpMethod.DELETE, httpEntity, Object.class);
                    } catch (Exception ex) {
                        response = genericRestTemplate.exchange(url, HttpMethod.DELETE, httpEntity, String.class);
                    }
                    break;
                case HTTP_METHOD_PUT:
                    try {
                        response = genericRestTemplate.exchange(url, HttpMethod.PUT, httpEntity, Object.class);
                    } catch (Exception ex) {
                        response = genericRestTemplate.exchange(url, HttpMethod.PUT, httpEntity, String.class);
                    }
                    break;
                case HTTP_METHOD_PATCH:
                    genericRestTemplate.setRequestFactory(externalHttpClientFactory());
                    try {
                        response = genericRestTemplate.exchange(url, HttpMethod.PATCH, httpEntity, Object.class);
                    } catch (Exception ex) {
                        response = genericRestTemplate.exchange(url, HttpMethod.PATCH, httpEntity, String.class);
                    }
                    break;
                case HTTP_METHOD_HEAD:
                    try {
                        response = genericRestTemplate.exchange(url, HttpMethod.HEAD, httpEntity, Object.class);
                    } catch (Exception ex) {
                        response = genericRestTemplate.exchange(url, HttpMethod.HEAD, httpEntity, String.class);
                    }
                    break;
                case HTTP_METHOD_OPTIONS:
                    try {
                        response = genericRestTemplate.exchange(url, HttpMethod.OPTIONS, httpEntity, Object.class);
                    } catch (Exception ex) {
                        response = genericRestTemplate.exchange(url, HttpMethod.OPTIONS, httpEntity, String.class);
                    }
                    break;
                default:
                    throw new RuntimeException("INVALID HTTP METHOD: " + method);
            }

            codexsHelperLogTerm("EXTERNAL DISPATCHER RESPONSE IS", response, true);

            if (response.getStatusCodeValue() != requestDto.getExpectedCode()) {
                codexsHelperLogTerm("EXTERNAL DISPATCHER ERROR [INVALID STATUS CODE]", response.getStatusCodeValue(), true);
                Assert.assertEquals(response.getStatusCodeValue(), requestDto.getExpectedCode());
            }

            return response;

        } catch (HttpClientErrorException ex) {

            codexsHelperLogTerm("EXCEPTION[MESSAGE] HttpClientErrorException: ", ex.getMessage(), true);
            codexsHelperLogTerm("EXCEPTION[BODY] HttpClientErrorException: ", ex.getResponseBodyAsString(), true);

        } catch (HttpServerErrorException se) {

            codexsHelperLogTerm("EXCEPTION[MESSAGE] HttpServerErrorException: ", se.getMessage(), true);
            codexsHelperLogTerm("EXCEPTION[BODY] HttpServerErrorException: ", se.getResponseBodyAsString(), true);

        } catch (RuntimeException re) {
            codexsHelperLogTerm("EXCEPTION[MESSAGE] RuntimeException: ", re.getMessage(), true);
        }

        return null;
    }

}