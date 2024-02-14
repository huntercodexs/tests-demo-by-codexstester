package codexstester.engine.internal;

import codexstester.engine.dto.HeadersDto;
import codexstester.engine.dto.RequestDto;
import codexstester.engine.http.AvailableHttpStatusTests;

public abstract class InternalRequest3xxTests extends InternalRequest4xxTests {

    protected InternalRequest3xxTests(String target) {
        super(target);
    }

    protected void isOk3xxInternalTest() throws Exception {
        executeInternalTest(new RequestDto(), new HeadersDto());
        System.out.println("isOk3xxInternalTest is done");
    }

    /**
     * STATUS CODE 300 (MULTIPLE_CHOICE_300) TESTS
     * */
    protected void codexsTesterInternal_StatusCode300_RetrieveMultipleChoice(HeadersDto headersDto, RequestDto requestDto) throws Exception {
        requestDto.setExpectedCode(AvailableHttpStatusTests.MULTIPLE_CHOICE_300);
        executeInternalTest(requestDto, headersDto);
    }

    /**
     * STATUS CODE 301 (MOVED_PERMANENTLY_301) TESTS
     * */
    protected void codexsTesterInternal_StatusCode301_RetrieveMovedPermanently(HeadersDto headersDto, RequestDto requestDto) throws Exception {
        requestDto.setExpectedCode(AvailableHttpStatusTests.MOVED_PERMANENTLY_301);
        executeInternalTest(requestDto, headersDto);
    }

    /**
     * STATUS CODE 302 (FOUND_302) TESTS
     * */
    protected void codexsTesterInternal_StatusCode302_RetrieveFound(HeadersDto headersDto, RequestDto requestDto) throws Exception {
        requestDto.setExpectedCode(AvailableHttpStatusTests.FOUND_302);
        executeInternalTest(requestDto, headersDto);
    }

    /**
     * STATUS CODE 303 (SEE_OTHER_303) TESTS
     * */
    protected void codexsTesterInternal_StatusCode303_RetrieveSeeOther(HeadersDto headersDto, RequestDto requestDto) throws Exception {
        requestDto.setExpectedCode(AvailableHttpStatusTests.SEE_OTHER_303);
        executeInternalTest(requestDto, headersDto);
    }

    /**
     * STATUS CODE 304 (NOT_MODIFIED_304) TESTS
     * */
    protected void codexsTesterInternal_StatusCode304_RetrieveNotModified(HeadersDto headersDto, RequestDto requestDto) throws Exception {
        requestDto.setExpectedCode(AvailableHttpStatusTests.NOT_MODIFIED_304);
        executeInternalTest(requestDto, headersDto);
    }

    /**
     * STATUS CODE 305 (USE_PROXY_DEPRECATED_305) TESTS
     * */
    protected void codexsTesterInternal_StatusCode305_RetrieveUseProxyDeprecated(HeadersDto headersDto, RequestDto requestDto) throws Exception {
        requestDto.setExpectedCode(AvailableHttpStatusTests.USE_PROXY_DEPRECATED_305);
        executeInternalTest(requestDto, headersDto);
    }

    /**
     * STATUS CODE 306 (UNUSED_DEPRECATED_306) TESTS
     * */
    protected void codexsTesterInternal_StatusCode306_RetrieveUnusedDeprecated(HeadersDto headersDto, RequestDto requestDto) throws Exception {
        requestDto.setExpectedCode(AvailableHttpStatusTests.UNUSED_DEPRECATED_306);
        executeInternalTest(requestDto, headersDto);
    }

    /**
     * STATUS CODE 307 (TEMPORARY_REDIRECT_307) TESTS
     * */
    protected void codexsTesterInternal_StatusCode307_RetrieveTemporaryRedirect(HeadersDto headersDto, RequestDto requestDto) throws Exception {
        requestDto.setExpectedCode(AvailableHttpStatusTests.TEMPORARY_REDIRECT_307);
        executeInternalTest(requestDto, headersDto);
    }

    /**
     * STATUS CODE 308 (PERMANENT_REDIRECT_308) TESTS
     * */
    protected void codexsTesterInternal_StatusCode308_RetrievePermanentRedirect(HeadersDto headersDto, RequestDto requestDto) throws Exception {
        requestDto.setExpectedCode(AvailableHttpStatusTests.PERMANENT_REDIRECT_308);
        executeInternalTest(requestDto, headersDto);
    }

}
