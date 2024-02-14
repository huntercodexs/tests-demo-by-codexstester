package codexstester.setup.bridge;

import codexstester.engine.bridge.CodexsTesterCoreBridgeTests;
import com.huntercodexs.demojobs.TestsDemoApplication;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@SpringBootTest(classes = TestsDemoApplication.class) /*INSERT HERE THEM MAIN CLASS FROM PROJECT (EXAMPLE: ApplicationName.class)*/
public class TestsDemoBridgeTests extends CodexsTesterCoreBridgeTests {

    protected TestsDemoBridgeTests() {
        super("tests-demo/");
    }

    @Override
    @Before
    public void setUp() {
        super.setUp();
    }

}