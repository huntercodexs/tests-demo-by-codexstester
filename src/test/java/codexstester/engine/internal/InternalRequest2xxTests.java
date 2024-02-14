package codexstester.engine.internal;

import codexstester.engine.dto.HeadersDto;
import codexstester.engine.dto.RequestDto;
import codexstester.engine.http.AvailableHttpStatusTests;

public abstract class InternalRequest2xxTests extends InternalRequest3xxTests {

    protected InternalRequest2xxTests(String target) {
        super(target);
    }

    protected void isOk2xxInternalTest() throws Exception {
        executeInternalTest(new RequestDto(), new HeadersDto());
        System.out.println("isOk2xxInternalTest is done");
    }

    /**
     * STATUS CODE 200 (OK_200) TESTS
     * */
    protected void codexsTesterInternal_StatusCode200_RetrieveOK(HeadersDto headersDto, RequestDto requestDto) throws Exception {
        requestDto.setExpectedCode(AvailableHttpStatusTests.OK_200);
        executeInternalTest(requestDto, headersDto);
    }

    /**
     * STATUS CODE 201 (CREATED_201) TESTS
     * */
    protected void codexsTesterInternal_StatusCode201_RetrieveCreated(HeadersDto headersDto, RequestDto requestDto) throws Exception {
        requestDto.setExpectedCode(AvailableHttpStatusTests.CREATED_201);
        executeInternalTest(requestDto, headersDto);
    }

    /**
     * STATUS CODE 202 (ACCEPTED_202) TESTS
     * */
    protected void codexsTesterInternal_StatusCode202_RetrieveAccepted(HeadersDto headersDto, RequestDto requestDto) throws Exception {
        requestDto.setExpectedCode(AvailableHttpStatusTests.ACCEPTED_202);
        executeInternalTest(requestDto, headersDto);
    }

    /**
     * STATUS CODE 203 (NON_AUTHORITATIVE_INFORMATION_203) TESTS
     * */
    protected void codexsTesterInternal_StatusCode203_RetrieveNonAuthoritativeInformation(HeadersDto headersDto, RequestDto requestDto) throws Exception {
        requestDto.setExpectedCode(AvailableHttpStatusTests.NON_AUTHORITATIVE_INFORMATION_203);
        executeInternalTest(requestDto, headersDto);
    }

    /**
     * STATUS CODE 204 (NO_CONTENT_204) TESTS
     * */
    protected void codexsTesterInternal_StatusCode204_RetrieveNoContent(HeadersDto headersDto, RequestDto requestDto) throws Exception {
        requestDto.setExpectedCode(AvailableHttpStatusTests.NO_CONTENT_204);
        executeInternalTest(requestDto, headersDto);
    }

    /**
     * STATUS CODE 205 (RESET_CONTENT_205) TESTS
     * */
    protected void codexsTesterInternal_StatusCode205_RetrieveResetContent(HeadersDto headersDto, RequestDto requestDto) throws Exception {
        requestDto.setExpectedCode(AvailableHttpStatusTests.RESET_CONTENT_205);
        executeInternalTest(requestDto, headersDto);
    }

    /**
     * STATUS CODE 206 (PARTIAL_CONTENT_206) TESTS
     * */
    protected void codexsTesterInternal_StatusCode206_RetrievePartialContent(HeadersDto headersDto, RequestDto requestDto) throws Exception {
        requestDto.setExpectedCode(AvailableHttpStatusTests.PARTIAL_CONTENT_206);
        executeInternalTest(requestDto, headersDto);
    }

    /**
     * STATUS CODE 207 (MULT_STATUS_WEBDAV_EN_US_207) TESTS
     * */
    protected void codexsTesterInternal_StatusCode207_RetrieveMultStatusWebdav(HeadersDto headersDto, RequestDto requestDto) throws Exception {
        requestDto.setExpectedCode(AvailableHttpStatusTests.MULTI_STATUS_WEBDAV_EN_US_207);
        executeInternalTest(requestDto, headersDto);
    }

    /**
     * STATUS CODE 208 (MULTI_STATUS_WEBDAV_EN_US_208) TESTS
     * */
    protected void codexsTesterInternal_StatusCode208_RetrieveMultiStatus(HeadersDto headersDto, RequestDto requestDto) throws Exception {
        requestDto.setExpectedCode(AvailableHttpStatusTests.MULTI_STATUS_WEBDAV_EN_US_208);
        executeInternalTest(requestDto, headersDto);
    }

    /**
     * STATUS CODE 226 (IM_USED_HTTP_DELTA_ENCODING_226) TESTS
     * */
    protected void codexsTesterInternal_StatusCode226_RetrieveImUsedHttpDeltaEncoding(HeadersDto headersDto, RequestDto requestDto) throws Exception {
        requestDto.setExpectedCode(AvailableHttpStatusTests.IM_USED_HTTP_DELTA_ENCODING_226);
        executeInternalTest(requestDto, headersDto);
    }

}
