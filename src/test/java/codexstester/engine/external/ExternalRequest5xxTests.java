package codexstester.engine.external;

import codexstester.engine.dto.HeadersDto;
import codexstester.engine.dto.RequestDto;
import codexstester.engine.http.AvailableHttpStatusTests;

public abstract class ExternalRequest5xxTests extends AbstractExternalRestTemplateTests {

    protected ExternalRequest5xxTests(String target) {
        super(target);
    }

    protected void isOk5xxExternalTest() throws Exception {
        executeExternalTest(new RequestDto(), new HeadersDto());
        System.out.println("isOk5xxExternalTest is done");
    }

    /**
     * STATUS CODE 500 (INTERNAL_SERVER_ERROR_500) TESTS
     * */

    protected void codexsTesterExternal_StatusCode500_RetrieveInternalServerError(HeadersDto headersDto, RequestDto requestDto) throws Exception {
        requestDto.setExpectedCode(AvailableHttpStatusTests.INTERNAL_SERVER_ERROR_500);
        executeExternalTest(requestDto, headersDto);
    }

    /**
     * STATUS CODE 501 (NOT_IMPLEMENTED_501) TESTS
     * */

    protected void codexsTesterExternal_StatusCode501_RetrieveNotImplemented(HeadersDto headersDto, RequestDto requestDto) throws Exception {
        requestDto.setExpectedCode(AvailableHttpStatusTests.NOT_IMPLEMENTED_501);
        executeExternalTest(requestDto, headersDto);
    }

    /**
     * STATUS CODE 502 (BAD_GATEWAY_502) TESTS
     * */

    protected void codexsTesterExternal_StatusCode502_RetrieveBadGateway(HeadersDto headersDto, RequestDto requestDto) throws Exception {
        requestDto.setExpectedCode(AvailableHttpStatusTests.BAD_GATEWAY_502);
        executeExternalTest(requestDto, headersDto);
    }

    /**
     * STATUS CODE 503 (SERVICE_UNAVAILABLE_503) TESTS
     * */

    protected void codexsTesterExternal_StatusCode503_RetrieveServiceUnavailable(HeadersDto headersDto, RequestDto requestDto) throws Exception {
        requestDto.setExpectedCode(AvailableHttpStatusTests.SERVICE_UNAVAILABLE_503);
        executeExternalTest(requestDto, headersDto);
    }

    /**
     * STATUS CODE 504 (GATEWAY_TIMEOUT_504) TESTS
     * */

    protected void codexsTesterExternal_StatusCode504_RetrieveGatewayTimeout(HeadersDto headersDto, RequestDto requestDto) throws Exception {
        requestDto.setExpectedCode(AvailableHttpStatusTests.GATEWAY_TIMEOUT_504);
        executeExternalTest(requestDto, headersDto);
    }

    /**
     * STATUS CODE 505 (HTTP_VERSION_NOT_SUPPORTED_505) TESTS
     * */

    protected void codexsTesterExternal_StatusCode505_RetrieveHttpVersionNotSupported(HeadersDto headersDto, RequestDto requestDto) throws Exception {
        requestDto.setExpectedCode(AvailableHttpStatusTests.HTTP_VERSION_NOT_SUPPORTED_505);
        executeExternalTest(requestDto, headersDto);
    }

    /**
     * STATUS CODE 506 (VARIANT_ALSO_NEGOTIATES_506) TESTS
     * */

    protected void codexsTesterExternal_StatusCode506_RetrieveVariantAlsoNegotiates(HeadersDto headersDto, RequestDto requestDto) throws Exception {
        requestDto.setExpectedCode(AvailableHttpStatusTests.VARIANT_ALSO_NEGOTIATES_506);
        executeExternalTest(requestDto, headersDto);
    }

    /**
     * STATUS CODE 507 (INSUFFICIENT_STORAGE_507) TESTS
     * */

    protected void codexsTesterExternal_StatusCode507_RetrieveInsuficientStorage(HeadersDto headersDto, RequestDto requestDto) throws Exception {
        requestDto.setExpectedCode(AvailableHttpStatusTests.INSUFFICIENT_STORAGE_507);
        executeExternalTest(requestDto, headersDto);
    }

    /**
     * STATUS CODE 508 (LOOP_DETECTED_WEBDAV_EN_US_508) TESTS
     * */

    protected void codexsTesterExternal_StatusCode508_RetrieveLoopDetected(HeadersDto headersDto, RequestDto requestDto) throws Exception {
        requestDto.setExpectedCode(AvailableHttpStatusTests.LOOP_DETECTED_WEBDAV_EN_US_508);
        executeExternalTest(requestDto, headersDto);
    }

    /**
     * STATUS CODE 510 (NOT_EXTENDED_510) TESTS
     * */

    protected void codexsTesterExternal_StatusCode510_RetrieveNotExtended(HeadersDto headersDto, RequestDto requestDto) throws Exception {
        requestDto.setExpectedCode(AvailableHttpStatusTests.NOT_EXTENDED_510);
        executeExternalTest(requestDto, headersDto);
    }

    /**
     * STATUS CODE 511 (NETWORK_AUTHENTICATION_REQUIRED_511) TESTS
     * */

    protected void codexsTesterExternal_StatusCode511_RetrieveNetworkAuthenticationRequired(HeadersDto headersDto, RequestDto requestDto) throws Exception {
        requestDto.setExpectedCode(AvailableHttpStatusTests.NETWORK_AUTHENTICATION_REQUIRED_511);
        executeExternalTest(requestDto, headersDto);
    }

}
