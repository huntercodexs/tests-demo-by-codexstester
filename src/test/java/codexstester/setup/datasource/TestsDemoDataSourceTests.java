package codexstester.setup.datasource;

import codexstester.engine.security.SecurityTests;

/**
 * SAMPLE DATA SOURCE
 * Use this file to create all tests source
 * */
public class TestsDemoDataSourceTests extends SecurityTests {

    /**
     * DEFAULT ATTRIBUTES
     * Change it as needed
     * */

    public static final boolean ignoreOAuth2Tests = true;
    public static final String samplePort = "33001";
    public static final String sampleEndpointUri = "/huntercodexs/anny-service/api/any-resource";
    public static final String sampleWebhookUrl = "http://your-domain.com/api/1.1/receptor";
    public static final String sampleOauth2Token = "d4cd86a0-809a-40aa-a590-ef68873dcd7b";

    public static String dataSourceSampleResponse() {
        return "This is a expected sample response";
    }

    public static int dataSourceSampleSum(int a, int b) {
        return a + b;
    }

}
