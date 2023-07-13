package codexstester.engine.properties;

public abstract class ExternalPropertyTests extends InternalPropertyTests {

    protected final String externalUrlBaseTest = externalProps.getProperty("external.tests.base-url");
    protected final String externalUriBaseTest = externalProps.getProperty("external.tests.base-uri");
    protected final String externalAuthorizationBasic = externalProps.getProperty("external.tests.header.authorization-basic");
    protected final String externalAuthorizationBasicInvalid = externalProps.getProperty("external.tests.header.authorization-basic-invalid");
    protected final String externalAuthorizationBearer = externalProps.getProperty("external.tests.header.authorization-bearer");
    protected final String externalAuthorizationBearerInvalid = externalProps.getProperty("external.tests.header.authorization-bearer-invalid");
    protected final String externalAppNameAuthorization = externalProps.getProperty("external.tests.header.api-key.app-name");
    protected final String externalTokenAuthorization = externalProps.getProperty("external.tests.header.api-key.token");
    protected final String externalSecretAuthorization = externalProps.getProperty("external.tests.header.api-key.secret");
    protected final String externalValueAuthorization = externalProps.getProperty("external.tests.header.api-key.value");
    protected final String externalGenericAuthorization = externalProps.getProperty("external.tests.header.api-key.generic");
    /*Three Additional Headers*/
    protected final String externalAdditionalHeaderName1 = externalProps.getProperty("external.tests.header.additional-name-1");
    protected final String externalAdditionalHeaderValue1 = externalProps.getProperty("external.tests.header.additional-value-1");
    protected final String externalAdditionalHeaderName2 = externalProps.getProperty("external.tests.header.additional-name-2");
    protected final String externalAdditionalHeaderValue2 = externalProps.getProperty("external.tests.header.additional-value-2");
    protected final String externalAdditionalHeaderName3 = externalProps.getProperty("external.tests.header.additional-name-3");
    protected final String externalAdditionalHeaderValue3 = externalProps.getProperty("external.tests.header.additional-value-3");
    protected final String externalAdditionalHeaderName4 = externalProps.getProperty("external.tests.header.additional-name-4");
    protected final String externalAdditionalHeaderValue4 = externalProps.getProperty("external.tests.header.additional-value-4");
    protected final String externalAdditionalHeaderName5 = externalProps.getProperty("external.tests.header.additional-name-5");
    protected final String externalAdditionalHeaderValue5 = externalProps.getProperty("external.tests.header.additional-value-5");
    /*When Query Parameters is defined*/
    protected final String externalUrlQueryParameters = externalProps.getProperty("external.tests.url.query-string-parameters");

    protected ExternalPropertyTests(String target) {
        super(target);
    }

}
