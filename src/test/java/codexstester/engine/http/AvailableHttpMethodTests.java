package codexstester.engine.http;

public abstract class AvailableHttpMethodTests extends AvailableHttpStatusTests {

    protected static final String HTTP_METHOD_GET = "GET";
    protected static final String HTTP_METHOD_POST = "POST";
    protected static final String HTTP_METHOD_DELETE = "DELETE";
    protected static final String HTTP_METHOD_PUT = "PUT";
    protected static final String HTTP_METHOD_PATCH = "PATCH";
    protected static final String HTTP_METHOD_HEAD = "HEAD";
    protected static final String HTTP_METHOD_OPTIONS = "OPTIONS";
    protected static final String HTTP_METHOD_TESTER = "TESTER";

    protected AvailableHttpMethodTests(String target) {
        super(target);
    }

}
