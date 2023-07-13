package codexstester.engine.properties;

public abstract class InternalPropertyTests extends UnitaryPropertyTests {

    protected final String internalUrlBaseTest = internalProps.getProperty("internal.tests.base-url");
    protected final String internalUriBaseTest = internalProps.getProperty("internal.tests.base-uri");
    protected final String internalAuthorizationBasic = internalProps.getProperty("internal.tests.header.authorization-basic");
    protected final String internalAuthorizationBasicInvalid = internalProps.getProperty("internal.tests.header.authorization-basic-invalid");
    protected final String internalAuthorizationBearer = internalProps.getProperty("internal.tests.header.authorization-bearer");
    protected final String internalAuthorizationBearerInvalid = internalProps.getProperty("internal.tests.header.authorization-bearer-invalid");
    protected final String internalAppNameAuthorization = internalProps.getProperty("internal.tests.header.api-key.app-name");
    protected final String internalTokenAuthorization = internalProps.getProperty("internal.tests.header.api-key.token");
    protected final String internalSecretAuthorization = internalProps.getProperty("internal.tests.header.api-key.secret");
    protected final String internalValueAuthorization = internalProps.getProperty("internal.tests.header.api-key.value");
    protected final String internalGenericAuthorization = internalProps.getProperty("internal.tests.header.api-key.generic");
    /*Three Additional Headers*/
    protected final String internalAdditionalHeaderName1 = internalProps.getProperty("internal.tests.header.additional-name-1");
    protected final String internalAdditionalHeaderValue1 = internalProps.getProperty("internal.tests.header.additional-value-1");
    protected final String internalAdditionalHeaderName2 = internalProps.getProperty("internal.tests.header.additional-name-2");
    protected final String internalAdditionalHeaderValue2 = internalProps.getProperty("internal.tests.header.additional-value-2");
    protected final String internalAdditionalHeaderName3 = internalProps.getProperty("internal.tests.header.additional-name-3");
    protected final String internalAdditionalHeaderValue3 = internalProps.getProperty("internal.tests.header.additional-value-3");
    protected final String internalAdditionalHeaderName4 = internalProps.getProperty("internal.tests.header.additional-name-4");
    protected final String internalAdditionalHeaderValue4 = internalProps.getProperty("internal.tests.header.additional-value-4");
    protected final String internalAdditionalHeaderName5 = internalProps.getProperty("internal.tests.header.additional-name-5");
    protected final String internalAdditionalHeaderValue5 = internalProps.getProperty("internal.tests.header.additional-value-5");
    /*When Query Parameters is defined*/
    protected final String internalUrlQueryParameters = internalProps.getProperty("internal.tests.url.query-string-parameters");

    protected InternalPropertyTests(String target) {
        super(target);
    }
}
