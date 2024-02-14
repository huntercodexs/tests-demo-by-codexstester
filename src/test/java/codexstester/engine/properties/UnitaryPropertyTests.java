package codexstester.engine.properties;

import codexstester.engine.core.CodexTesterPropertiesLoaderTests;

public abstract class UnitaryPropertyTests extends CodexTesterPropertiesLoaderTests {

    protected final String unitaryUrlBaseTest = unitaryProps.getProperty("unitary.tests.base-url");
    protected final String unitaryUriBaseTest = unitaryProps.getProperty("unitary.tests.base-uri");
    protected final String unitaryAuthorizationBasic = unitaryProps.getProperty("unitary.tests.header.authorization-basic");
    protected final String unitaryAuthorizationBasicInvalid = unitaryProps.getProperty("unitary.tests.header.authorization-basic-invalid");
    protected final String unitaryAuthorizationBearer = unitaryProps.getProperty("unitary.tests.header.authorization-bearer");
    protected final String unitaryAuthorizationBearerInvalid = unitaryProps.getProperty("unitary.tests.header.authorization-bearer-invalid");
    protected final String unitaryAppNameAuthorization = unitaryProps.getProperty("unitary.tests.header.api-key.app-name");
    protected final String unitaryTokenAuthorization = unitaryProps.getProperty("unitary.tests.header.api-key.token");
    protected final String unitarySecretAuthorization = unitaryProps.getProperty("unitary.tests.header.api-key.secret");
    protected final String unitaryValueAuthorization = unitaryProps.getProperty("unitary.tests.header.api-key.value");
    protected final String unitaryGenericAuthorization = unitaryProps.getProperty("unitary.tests.header.api-key.generic");
    /*Three Additional Headers*/
    protected final String unitaryAdditionalHeaderName1 = unitaryProps.getProperty("unitary.tests.header.additional-name-1");
    protected final String unitaryAdditionalHeaderValue1 = unitaryProps.getProperty("unitary.tests.header.additional-value-1");
    protected final String unitaryAdditionalHeaderName2 = unitaryProps.getProperty("unitary.tests.header.additional-name-2");
    protected final String unitaryAdditionalHeaderValue2 = unitaryProps.getProperty("unitary.tests.header.additional-value-2");
    protected final String unitaryAdditionalHeaderName3 = unitaryProps.getProperty("unitary.tests.header.additional-name-3");
    protected final String unitaryAdditionalHeaderValue3 = unitaryProps.getProperty("unitary.tests.header.additional-value-3");
    protected final String unitaryAdditionalHeaderName4 = unitaryProps.getProperty("unitary.tests.header.additional-name-4");
    protected final String unitaryAdditionalHeaderValue4 = unitaryProps.getProperty("unitary.tests.header.additional-value-4");
    protected final String unitaryAdditionalHeaderName5 = unitaryProps.getProperty("unitary.tests.header.additional-name-5");
    protected final String unitaryAdditionalHeaderValue5 = unitaryProps.getProperty("unitary.tests.header.additional-value-5");
    /*When Query Parameters is defined*/
    protected final String unitaryUrlQueryParameters = unitaryProps.getProperty("unitary.tests.url.query-string-parameters");

    protected UnitaryPropertyTests(String target) {
        super(target);
    }

}
