package codexstester.test.unitary;

import codexstester.setup.bridge.TestsDemoBridgeTests;
import net.minidev.json.JSONObject;
import org.junit.Test;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.ParseException;
import java.util.Arrays;
import java.util.Locale;

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

    private String brCurrency(double value) {
        if (value <= 0) value = 0.00;
        Locale localBrazil = new Locale("pt", "BR");
        NumberFormat brCurrency = NumberFormat.getCurrencyInstance(localBrazil);
        return brCurrency.format(value)
                .replaceAll("[^0-9R$., ]+", "")
                .replaceAll("R[$]", "R\\$ ");
    }

    private double currencySum(double current, double add) {
        System.out.println(brCurrency(current) +"+"+ brCurrency(add));
        double sum = current + add;
        System.out.println(brCurrency((float) sum));
        return sum;
    }

    private double currencySumFromString(String current, String add) {
        System.out.println(brCurrency(Double.parseDouble(current)) +"+"+ brCurrency(Double.parseDouble(add)));
        double sum = Double.parseDouble(current) + Double.parseDouble(add);
        System.out.println(brCurrency((float) sum));
        return sum;
    }

    @Test
    public void moneyCalculator() {

        double result = currencySum(0.00, 0.01);
        result += currencySum(0.01, 0.10);
        result += currencySum(0.10, 0.11);
        result += currencySum(0.11, 1.11);
        result += currencySum(1.00, 1.01);
        result += currencySum(1.00, 1.10);
        result += currencySum(11.00, 111.10);
        result += currencySum(1111.00, 11.10);
        result += currencySum(111111.00, 111.10);
        result += currencySum(111.00, 11.01);
        result += currencySum(111111111.00, 11.01);
        result += currencySum(999111111111.00, 11.01);

        System.out.println("Total");
        System.out.println(brCurrency(result));

        /*Proof*/
        double proff = currencySum(0.00, 1.00);
        proff += currencySum(0.00, 1.00);
        proff += currencySum(0.00, 1.00);
        proff += currencySum(0.00, 1.00);
        proff += currencySum(0.00, 1.00);
        proff += currencySum(0.00, 1.00);

        System.out.println("Total");
        System.out.println(brCurrency(proff));
        codexsTesterAssertText("R$ 6,00", brCurrency(proff));

    }

    @Test
    public void stringToDoubleTest() {

        /*Proof*/
        double proff = currencySumFromString("0.00", "1.00");
        proff += currencySumFromString("0.00", "1.00");
        proff += currencySumFromString("0.00", "1.00");
        proff += currencySumFromString("0.00", "1.00");
        proff += currencySumFromString("0.00", "1.00");
        proff += currencySumFromString("0.00", "1.00");

        System.out.println("Total");
        System.out.println(brCurrency(proff));
        codexsTesterAssertText("R$ 6,00", brCurrency(proff));

    }

    @Test
    public void doubleTest() {
        System.out.println(Double.parseDouble("123.45001"));
        System.out.println(Double.parseDouble("123.45001d"));
        System.out.println(Double.parseDouble("123.45000"));
        System.out.println(Double.parseDouble("123.45001D"));

        System.out.println(Double.valueOf("123.45d"));
        System.out.println(Double.valueOf("123.4500d"));
        System.out.println(Double.valueOf("123.45D"));

        System.out.println(Double.valueOf("12345"));
    }

    @Test
    public void decimalFormatTest () throws ParseException {
        String str1 = "1.000.000.000,00";
        double resulted1 = DecimalFormat.getNumberInstance().parse(str1).doubleValue();
        System.out.println(brCurrency(resulted1));

        String str2 = "1,000,000,000.00";
        double resulted2 = DecimalFormat.getNumberInstance().parse(str2
                .replaceAll(",", "")
                .replaceAll("\\.", ",")
        ).doubleValue();
        System.out.println(brCurrency(resulted2));

        String str3 = "1000000000,00";
        double resulted3 = DecimalFormat.getNumberInstance().parse(str3).doubleValue();
        System.out.println(brCurrency(resulted3));

        String str4 = "1000000000.00";
        double resulted4 = DecimalFormat.getNumberInstance().parse(str4
                .replaceAll("\\.", ",")
        ).doubleValue();
        System.out.println(brCurrency(resulted4));
    }

}





