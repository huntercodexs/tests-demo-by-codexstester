package codexstester.test.unitary;

import codexstester.setup.bridge.TestsDemoBridgeTests;
import net.minidev.json.JSONObject;
import org.junit.Test;

import java.util.Arrays;

public class TestsDemoUnitaryTests extends TestsDemoBridgeTests {

    @Test
    public void testParams() {
        JSONObject j1 = new JSONObject();
        j1.put("name", "Test 1");

        JSONObject j2 = new JSONObject();
        j2.put("name", "Test 2");

        params(j1, j2);
    }

    private void params(JSONObject... j) {
        System.out.println(Arrays.toString(j));
    }

    private String rgDocument(String value, String rgUf) {
        if (value == null || value.equals("")) return "";
        if (rgUf == null) rgUf = "";
        if (!rgUf.contains("SSP") && !rgUf.equals("")) return "";

        //SSP CP = SSPSC, SSP/SP = SSPSP
        rgUf = rgUf.replaceAll("[^A-Z]+", "");

        if (rgUf.equals("SSPSP") || rgUf.equals("SP")) {
            rgUf = "";
        } else {
            //SSPCRJ = RJ, SSPSC = SC
            rgUf = rgUf.replaceAll("SSP", "");
        }

        return "RG"+value.replaceAll("[^0-9]+", "")+rgUf;
    }

    @Test
    public void rgTest() {
        System.out.println(" > ["+rgDocument("9090909090", "CNH")+"]");
        System.out.println(" > ["+rgDocument("7878787878", "SSP SC")+"]");
        System.out.println(" > ["+rgDocument("6767676767", "SSPSP")+"]");
        System.out.println(" > ["+rgDocument("1212121212", "SSCMG")+"]");
        System.out.println(" > ["+rgDocument("2020202020", "SSP/RJ")+"]");
    }

}
