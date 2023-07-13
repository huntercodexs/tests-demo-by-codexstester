package codexstester.engine.internal;

import codexstester.engine.dto.HeadersDto;
import codexstester.engine.dto.RequestDto;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultMatcher;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static codexstester.engine.util.CodexsHelperTests.codexsHelperLogTerm;

public abstract class AbstractInternalMockMvcTests extends AbstractInternalRequestTests {

    protected AbstractInternalMockMvcTests(String target) {
        super(target);
    }

    protected String codexsTesterInternalDispatcher(RequestDto requestDto, HeadersDto headersDto) {

        String uri = internalUriBaseTest;
        String method = headersDto.getHttpMethod();
        MockHttpServletRequestBuilder requestBuilder = null;
        ResultMatcher status = statusMockMvcTranslator(requestDto);

        if (status == null) {
            String err = "Missing HTTP-STATUS from RequestDto: use setExpectedCode";
            throw new RuntimeException(err);
        }

        if (requestDto.getUri() != null && !requestDto.getUri().equals("")) uri = requestDto.getUri();
        if (requestDto.getId() != null && !requestDto.getId().equals("")) uri = uri + "/" + requestDto.getId();

        String url = internalUrlBaseTest + uri;

        if (internalUrlQueryParameters != null && !internalUrlQueryParameters.equals("")) {
            url = url + "?" + internalUrlQueryParameters;
        }

        codexsHelperLogTerm("INTERNAL DISPATCHER REQUEST URL IS", url, true);

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

            //codexsHelperLogTerm("INTERNAL DISPATCHER RESULT IS", result, true);
            //codexsHelperLogTerm("INTERNAL DISPATCHER RESPONSE IS", result.getResponse(), true);
            codexsHelperLogTerm("INTERNAL DISPATCHER CONTENT AS STRING IS", result.getResponse().getContentAsString(), true);

            return result.getResponse().getContentAsString();

        } catch (Exception ex) {
            codexsHelperLogTerm("EXCEPTION[MOCK-MVC] DISPATCHER", ex.getMessage(), true);
        }

        return null;

    }

}