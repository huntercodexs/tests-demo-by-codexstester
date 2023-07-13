package codexstester.engine.internal;

import codexstester.engine.dto.HeadersDto;
import codexstester.engine.dto.RequestDto;
import codexstester.engine.http.AvailableHttpMethodTests;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultMatcher;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static codexstester.engine.util.CodexsHelperTests.codexsHelperLogTerm;

public abstract class AbstractInternalRequestTests extends AvailableHttpMethodTests {

    protected AbstractInternalRequestTests(String target) {
        super(target);
    }

    void executeInternalTest(RequestDto requestDto, HeadersDto headersDto) throws Exception {

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
                assertResultInternalTest();
        }
    }

    private void dispatcher(RequestDto requestDto, HeadersDto headersDto, String method) throws Exception {

        MockHttpServletRequestBuilder requestBuilder = null;
        String uri = internalUriBaseTest;
        ResultMatcher status = statusMockMvcTranslator(requestDto);

        if (requestDto.getUri() != null && !requestDto.getUri().equals("")) uri = requestDto.getUri();
        if (requestDto.getId() != null && !requestDto.getId().equals("")) uri = uri + "/" + requestDto.getId();

        String url = internalUrlBaseTest + uri;

        if (internalUrlQueryParameters != null && !internalUrlQueryParameters.equals("")) {
            url = url + "?" + internalUrlQueryParameters;
        }

        codexsHelperLogTerm("INTERNAL REQUEST URL IS", url, true);

        switch (method) {
            case "GET":
                requestBuilder = MockMvcRequestBuilders.get(url);
                codexsHelperLogTerm("HTTP METHOD IN REQUEST BUILDER IS", "GET", true);
                break;
            case "POST":
                requestBuilder = MockMvcRequestBuilders.post(url);
                codexsHelperLogTerm("HTTP METHOD IN REQUEST BUILDER IS", "POST", true);
                break;
            case "PUT":
                requestBuilder = MockMvcRequestBuilders.put(url);
                codexsHelperLogTerm("HTTP METHOD IN REQUEST BUILDER IS", "PUT", true);
                break;
            case "DELETE":
                requestBuilder = MockMvcRequestBuilders.delete(url);
                codexsHelperLogTerm("HTTP METHOD IN REQUEST BUILDER IS", "DELETE", true);
                break;
            case "PATCH":
                requestBuilder = MockMvcRequestBuilders.patch(url);
                codexsHelperLogTerm("HTTP METHOD IN REQUEST BUILDER IS", "PATCH", true);
                break;
            case "HEAD":
                requestBuilder = MockMvcRequestBuilders.head(url);
                codexsHelperLogTerm("HTTP METHOD IN REQUEST BUILDER IS", "HEAD", true);
                break;
            case "OPTIONS":
                requestBuilder = MockMvcRequestBuilders.options(url);
                codexsHelperLogTerm("HTTP METHOD IN REQUEST BUILDER IS", "OPTIONS", true);
                break;
            default:
                codexsHelperLogTerm("EXCEPTION[INVALID-HTTP-METHOD]", method, true);
                throw new RuntimeException("EXCEPTION[INVALID-HTTP-METHOD]: " + method);
        }

        MvcResult result = null;

        String cType = headersDto.getContentType();
        String aType = headersDto.getAccepted();

        if (cType == null || cType.equals("")) cType = MediaType.APPLICATION_JSON_VALUE;
        if (aType == null || aType.equals("")) aType = MediaType.APPLICATION_JSON_VALUE;

        try {
            result = genericMockMvc.perform(
                    requestBuilder
                            .content(requestDto.getDataRequest())
                            .contentType(cType)
                            .accept(aType)
                            .headers(internalBuilderHeaders(requestDto, headersDto))
            ).andExpect(status).andReturn();

            codexsHelperLogTerm("INTERNAL RESPONSE IS", result.getResponse().getContentAsString(), true);

            /*Assert Content as String*/
            if (requestDto.getExpectedMessage() != null && !requestDto.getExpectedMessage().equals("")) {
                if (!result.getResponse().getContentAsString().equals("")) {
                    codexsHelperLogTerm("TRY ASSERT INTEGRATION", result.getResponse().getContentAsString(), true);
                    codexsHelperLogTerm(">>> EXPECTED MESSAGE", requestDto.getExpectedMessage(), false);
                    codexsHelperLogTerm("<<< RECEIVED MESSAGE", result.getResponse().getContentAsString(), true);
                    assertInternalTests(requestDto.getExpectedMessage(), result.getResponse().getContentAsString());
                }
            }

        } catch (Exception ex) {
            codexsHelperLogTerm("EXCEPTION[MOCK-MVC]", ex.getMessage(), true);

            /*Try to assert content as string*/
            if (requestDto.getExpectedMessage() != null && !requestDto.getExpectedMessage().equals("")) {
                codexsHelperLogTerm("TRY ASSERT INTEGRATION IN EXCEPTION", ex.getMessage(), true);
                codexsHelperLogTerm(">>> EXPECTED MESSAGE", requestDto.getExpectedMessage(), false);
                codexsHelperLogTerm("<<< RECEIVED MESSAGE", ex.getMessage(), true);
                assertInternalTests(requestDto.getExpectedMessage(), ex.getMessage());
            }
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

    private void assertResultInternalTest() {
        System.out.println("CODEXS TESTER IS RUNNING IN INTERNAL MODE");
    }

}