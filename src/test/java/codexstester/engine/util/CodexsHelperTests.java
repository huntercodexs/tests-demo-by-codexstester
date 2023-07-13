package codexstester.engine.util;

import net.minidev.json.JSONObject;
import org.springframework.test.util.ReflectionTestUtils;
import org.springframework.util.DigestUtils;
import org.springframework.util.ReflectionUtils;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Random;
import java.util.UUID;

public class CodexsHelperTests {

    public static int codexsHelperRandomNumber(int n) {
        Random generator = new Random();
        return generator.nextInt(n);
    }

    public static String codexsHelperMd5(String data) {
        return DigestUtils.md5DigestAsHex(data.getBytes());
    }

    public static String codexsHelperGuideGenerator(String tcn) {
        if (tcn == null || tcn.equals("")) {
            return UUID.randomUUID().toString();
        }
        return tcn;
    }

    public static String codexsHelperToday() {
        Date now = new Date();
        return new SimpleDateFormat("yyy-MM-dd").format(now);
    }

    public static String codexsHelperOneYearAgo() {
        Date now = new Date();
        String today = new SimpleDateFormat("yyy-MM-dd").format(now);
        String[] getDate = today.split("-");
        int yearCurrent = Integer.parseInt(getDate[0]);
        return (yearCurrent-1)+"-"+getDate[1]+"-"+getDate[2];
    }

    public static String codexsHelperFiveYearAgo() {
        Date now = new Date();
        String today = new SimpleDateFormat("yyy-MM-dd").format(now);
        String[] getDate = today.split("-");
        int yearCurrent = Integer.parseInt(getDate[0]);
        return (yearCurrent-6)+"-"+getDate[1]+"-"+getDate[2];
    }

    public static void codexsHelperLogTerm(String title, Object data, boolean line) {
        System.out.println("\n"+title);
        if (data != null && !data.equals("")) {
            System.out.println(data);
        }
        if (line) {
            for (int i = 0; i < 120; i++) System.out.print("-");
        }
        System.out.println("\n");
    }

    public static void codexsHelperLogTermTests(String title, Object data, boolean line) {
        if (line) {
            for (int i = 0; i < 120; i++) System.out.print("-");
            System.out.print("\n");
        }
        if (data != null && !data.equals("")) {
            System.out.println(title + ": " + data);
        } else if (data == null) {
            System.out.println(title + ": null");
        } else {
            System.out.println(title);
        }
    }

    public static JSONObject codexsHelperQueryStringToJson(String queryString) {

        String[] splitter = queryString.split("&");
        JSONObject jsonData = new JSONObject();

        for (String split : splitter) {
            String[] splitter2 = split.split("=");
            jsonData.appendField(splitter2[0].trim(), splitter2[1].trim());
        }

        return jsonData;

    }

    public static String codexsHelperJsonToString(JSONObject json) {
        return json.toJSONString();
    }

    public static JSONObject codexsHelperStringToJsonSimple(String string) {

        codexsHelperLogTerm("CODEXS TESTER STRING TO JSON", string, true);

        JSONObject jsonData = new JSONObject();
        String strClean = string.replaceAll("[\"{\\[\\]}'/\\\\]+", "");
        String[] splitter = strClean.split(",");

        if (!string.contains(":")) {

            for (String splitOne : splitter) {
                String[] splitter2 = splitOne.split("=");

                try {
                    jsonData.appendField(splitter2[0].trim(), splitter2[1].trim());
                } catch (RuntimeException re) {
                    codexsHelperLogTerm("EXCEPTION ON codexsTesterStringToJson", re.getMessage(), true);
                    jsonData.appendField(splitter2[0].trim(), "");
                }
            }

        } else {

            for (String splitOne : splitter) {
                String[] splitter2 = splitOne.split(":");

                try {
                    jsonData.appendField(splitter2[0].trim(), splitter2[1].trim());
                } catch (RuntimeException re) {
                    codexsHelperLogTerm("EXCEPTION ON codexsTesterStringToJson", re.getMessage(), true);
                    jsonData.appendField(splitter2[0].trim(), "");
                }
            }
        }

        return jsonData;
    }

    public static Object codexsHelperToPrivateMethods(Object instance, String method, List<?> args) {
        switch (args.size()) {
            case 1:
                return ReflectionTestUtils.invokeMethod(instance, method, args.get(0));
            case 2:
                return ReflectionTestUtils.invokeMethod(instance, method, args.get(0), args.get(1));
            case 3:
                return ReflectionTestUtils.invokeMethod(instance, method, args.get(0), args.get(1), args.get(2));
            case 4:
                return ReflectionTestUtils.invokeMethod(instance, method, args.get(0), args.get(1), args.get(2), args.get(3));
            case 5:
                return ReflectionTestUtils.invokeMethod(instance, method, args.get(0), args.get(1), args.get(2), args.get(3), args.get(4));
            default:
                throw new RuntimeException("Args is too long (max 5 args)");
        }
    }

    public static String codexsHelperReadFile(String filepath) {
        StringBuilder dataFile = new StringBuilder();

        try {
            FileReader activateFile = null;
            try {
                activateFile = new FileReader(filepath);
            } catch (FileNotFoundException e) {
                throw new RuntimeException(e);
            }
            BufferedReader readActivateFile = new BufferedReader(activateFile);

            String lineFile = "";
            try {
                lineFile = readActivateFile.readLine();
                dataFile = new StringBuilder(lineFile);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

            while (lineFile != null) {
                lineFile = readActivateFile.readLine();
                if (lineFile != null) dataFile.append(lineFile).append("\n");
            }

            activateFile.close();

        } catch (IOException e) {
            throw new RuntimeException("FILE READER EXCEPTION: " + e.getMessage());
        }

        return dataFile.toString();
    }

}
