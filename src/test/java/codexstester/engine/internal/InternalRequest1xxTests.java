package codexstester.engine.internal;

import codexstester.engine.dto.HeadersDto;
import codexstester.engine.dto.RequestDto;
import codexstester.engine.http.AvailableHttpStatusTests;

public abstract class InternalRequest1xxTests extends InternalRequest2xxTests {

    protected InternalRequest1xxTests(String target) {
        super(target);
    }

    protected void isOk1xxInternalTest() throws Exception {
        executeInternalTest(new RequestDto(), new HeadersDto());
        System.out.println("isOk1xxInternalTest is done");
    }

    /**
     * STATUS CODE 100 (CONTINUE_100) TESTS
     * */
    protected void codexsTesterInternal_StatusCode100_RetrieveContinue(HeadersDto headersDto, RequestDto requestDto) throws Exception {
        requestDto.setExpectedCode(AvailableHttpStatusTests.CONTINUE_100);
        executeInternalTest(requestDto, headersDto);
    }

    /**
     * STATUS CODE 101 (SWITCHING_PROTOCOL_101) TESTS
     * */
    protected void codexsTesterInternal_StatusCode101_RetrieveSwitchingProtocol(HeadersDto headersDto, RequestDto requestDto) throws Exception {
        requestDto.setExpectedCode(AvailableHttpStatusTests.SWITCHING_PROTOCOL_101);
        executeInternalTest(requestDto, headersDto);
    }

    /**
     * STATUS CODE 102 (PROCESSING_WEBDAV_EN_US_102) TESTS
     * */
    protected void codexsTesterInternal_StatusCode102_RetrieveProcessing(HeadersDto headersDto, RequestDto requestDto) throws Exception {
        requestDto.setExpectedCode(AvailableHttpStatusTests.PROCESSING_WEBDAV_EN_US_102);
        executeInternalTest(requestDto, headersDto);
    }

    /**
     * STATUS CODE 103 (EARLY_HINTS_103) TESTS
     * */
    protected void codexsTesterInternal_StatusCode103_RetrieveEarlyHints(HeadersDto headersDto, RequestDto requestDto) throws Exception {
        requestDto.setExpectedCode(AvailableHttpStatusTests.EARLY_HINTS_103);
        executeInternalTest(requestDto, headersDto);
    }

}
