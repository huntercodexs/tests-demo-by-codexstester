package codexstester.engine.util;

import org.json.JSONException;

import java.util.LinkedHashMap;

import static codexstester.engine.util.CodexsHelperTests.codexsHelperLogTerm;

public class CodexsParserJsonTests {

    public static String codexsTesterParseJsonString(String jsonString, boolean debug) throws Exception {
        org.json.JSONObject json = codexsTesterParseOrgJsonObject(jsonString, debug);
        if (debug) codexsHelperLogTerm("ORG JSON STRING PARSED", json.toString(), true);
        return json.toString();
    }

    public static org.json.JSONObject codexsTesterParseOrgJsonObject(Object jsonCandidate, boolean debug) throws Exception {
        org.json.JSONObject orgJson = new org.json.JSONObject(jsonCandidate.toString());
        if (debug) codexsHelperLogTerm("ORG JSON OBJECT PARSED", orgJson, true);
        return orgJson;
    }

    public static net.minidev.json.JSONObject codexsTesterParseNetJsonObject(Object jsonCandidate, boolean debug) throws Exception {
        net.minidev.json.parser.JSONParser jsonParser = new net.minidev.json.parser.JSONParser();
        net.minidev.json.JSONObject json = (net.minidev.json.JSONObject) jsonParser.parse(jsonCandidate.toString());
        if (debug) codexsHelperLogTerm("NET(MINI DEV) JSON OBJECT PARSED", json, true);
        return json;
    }

    public static String codexsTesterJsonRefactorUrl(Object jsonString, boolean debug) throws Exception {
        if (!codexsTesterCheckJsonCompatibility(jsonString, debug)) return jsonString.toString();
        if (debug) codexsHelperLogTerm("codexsTesterJsonRefactorUrl is running", jsonString, true);

        String json = jsonString.toString()
                .replaceAll("http([s])?:([\\\\/\\\\/]+)", "http$1://")
                .replaceAll("[\\\\]+/", "/");
        if (debug) codexsHelperLogTerm("codexsTesterJsonRefactorUrl", json, true);

        return json;
    }

    public static String codexsTesterJsonRefactorEscapeChars(Object jsonString, boolean debug) throws Exception {
        if (!codexsTesterCheckJsonCompatibility(jsonString, debug)) return jsonString.toString();
        if (debug) codexsHelperLogTerm("codexsTesterJsonRefactorEscapeChars is running", jsonString, true);

        String json = jsonString.toString()
                .replaceAll("[\\\\]+\"", "\"")
                .replaceAll("(\\\\u)([0-9]+)(s)", "'$3")
                .replaceAll("’s", "'s");

        if (debug) codexsHelperLogTerm("codexsTesterJsonRefactorEscapeChars", json, true);
        return json;
    }

    public static String codexsTesterJsonRefactorSpaces(Object jsonString, boolean debug) throws Exception {
        if (!codexsTesterCheckJsonCompatibility(jsonString, debug)) return jsonString.toString();
        if (debug) codexsHelperLogTerm("codexsTesterJsonRefactorSpaces is running", jsonString, true);

        String json = jsonString.toString()
                .replaceAll(", \\{", ",{")
                .replaceAll(", \\[", ",[")
                .replaceAll("\", \"", "\",\"")
                .replaceAll("\" ?, ?\"", "\",\"");

        if (debug) codexsHelperLogTerm("codexsTesterJsonRefactorSpaces", json, true);
        return json;
    }

    public static String codexsTesterJsonRefactorArrayFix(Object jsonString, boolean debug) throws Exception {
        if (!codexsTesterCheckJsonCompatibility(jsonString, debug)) return jsonString.toString();
        if (debug) codexsHelperLogTerm("codexsTesterJsonRefactorArrayFix is running", jsonString, true);

        String json = jsonString.toString()
                .replaceAll("\"\\[", "[")
                .replaceAll("]\"", "]")
                .replaceAll("\"\\{", "{")
                .replaceAll("}\"", "}")
                .replaceAll("\\[\"", "[")
                .replaceAll("\"]", "]")
                .replaceAll("\\{\"", "{")
                .replaceAll("\"}", "}")
                .replaceAll("\", \"", "\",\"");

        if (debug) codexsHelperLogTerm("codexsTesterJsonRefactorArrayFix", json, true);
        return json;
    }

    public static String codexsTesterJsonRefactorComplexArray(Object jsonString, boolean debug) throws Exception {
        if (!codexsTesterCheckJsonCompatibility(jsonString, debug)) return jsonString.toString();
        if (debug) codexsHelperLogTerm("codexsTesterJsonRefactorArray is running", jsonString, true);

        String json = codexsTesterJsonRefactorArrayFix(jsonString, debug);

        json = json
                .replaceAll("(\\{)([0-9a-zA-Z]+)(\":)", "$1\"$2$3")
                .replaceAll("(:\")([0-9Aa-zA-Z-!@#$%¨&_+=)(/\\\\:.? ]+)(})", "$1$2\"$3")
                .replaceAll("([{,])\"([0-9a-zA-Z-_ ]+)\"\\:\\[([0-9a-zA-Z-_’'!@#$%&*)\"(+=/\\\\:;,.| ]+)( ?, ?)?", "$1\"$2\":[\"$3\"$4")
                .replaceAll("( ?, ?\\[ ?)([0-9a-zA-Z-_’!@#$%&*)\\(+=/\\\\:;,.| ]+)(\\])", ",[\"$2\"]")
                .replaceAll("(\\[)?(\"\")(\\])?", "$1\"$3")
                .replaceAll("(\\[\\[)([0-9a-zA-Z-_!@#$%&*=+ ,]+)(\\]\\])", "$1\"$2\"$3");

        if (debug) codexsHelperLogTerm("codexsTesterJsonRefactorArray", json, true);
        return json;
    }

    public static String codexsTesterJsonRefactorObjects(Object jsonString, boolean debug) throws Exception {
        if (!codexsTesterCheckJsonCompatibility(jsonString, debug)) return jsonString.toString();
        if (debug) codexsHelperLogTerm("codexsTesterJsonRefactorObjects is running", jsonString, true);

        String json = jsonString.toString()
                .replaceAll("([ ,{])([0-9a-zA-Z_]+)=([0-9a-zA-Z- \\\\/?%=.@#$!&*)(:]+)(,)?", "$1\"$2\":\"$3\"$4")
                .replaceAll("\"([0-9a-zA-Z-_]+)\"(:)\"([0-9a-zA-Z-_!@#$%&*)(=+, ]+)\"(,)( )?([0-9a-zA-Z-_!@#$%&*)(=+, ]+)(, ?)\"", "\"$1\"$2\"$3$4$5$6\"$7\"")
                .replaceAll("(object)=([0-9a-zA-Z.@#$!&*)(:{]+)(,)?", "\"$1\":\"$2\"$3")
                .replaceAll("(\")(\\{)|(\\})(\")", "$2$3")
                .replaceAll("\"null\", ", null+",")
                .replaceAll("\" ?, ?\"", "\",\"")
                .replaceAll("([ ,{])([0-9a-zA-Z_]+)=", "$1\"$2\":")
                .replaceAll("\\[([0-9a-zA-Z-_ !@#$%&*+=\\?.,':\\|\\\\\\\\/)(]+)]", "[\"$1\"]")
                .replaceAll("\"([0-9a-zA-Z-_ !@#$%&*)(=+:/\\\\.,]+)( )?(\")\\+( )?([0-9a-zA-Z-_ !@#$%&*)(=+:/\\\\.,]+)(,)( )?\"", "\"[$1$2+$4$5\"$6$7\"")
                .replaceAll("(,)?( )?([0-9a-zA-Z-_]+)( )\"([0-9a-zA-Z-_ ]+\")(:)", "$1$2\"$3$4$5$6");

        if (debug) codexsHelperLogTerm("codexsTesterJsonRefactorObjects", json, true);
        return json;
    }

    public static String codexsTesterJsonRefactorArrayFromString(Object jsonString, boolean debug) throws Exception {
        if (!codexsTesterCheckJsonCompatibility(jsonString, debug)) return jsonString.toString();
        if (debug) codexsHelperLogTerm("codexsTesterJsonRefactorArrayFromString is running", jsonString, true);

        String json = jsonString.toString()
                .replaceAll("( )?([0-9a-zA-Z- !@#$%&*)'(+=;:./\\\\|]+)\"\\](,)?", "\"$2\"]$3")
                .replaceAll("([0-9a-zA-Z- !@#$%&*)'(+=;:./\\\\|]+),", "\"$1\",")
                .replaceAll("(\\[)(\")+(?!,)", "[\"")
                .replaceAll("(?!,)(\")+(\\])", "\"]")
                .replaceAll("\"\"([0-9a-zA-Z-_ !@#$%&*)(=+,.'/\\\\]+)\"", "\"$1")
                .replaceAll("\"?(true|false|null)\"?", "$1")
                .replaceAll("(\")+", "\"");

        if (debug) codexsHelperLogTerm("codexsTesterJsonRefactorArrayFromString", json, true);
        return json;
    }

    public static String codexsTesterJsonRefactorDatetime(Object jsonString, boolean debug) throws Exception {
        if (!codexsTesterCheckJsonCompatibility(jsonString, debug)) return jsonString.toString();
        if (debug) codexsHelperLogTerm("codexsTesterJsonRefactorDatetime is running", jsonString, true);

        String json = jsonString.toString()
                .replaceAll("(\\\")( ?: ?)([0-9]+[-/.][0-9]+[-/.][0-9]+)( [0-9]+:[0-9]+:[0-9]+)?(,)?", "$1$2\"$3$4\"$5");

        if (debug) codexsHelperLogTerm("codexsTesterJsonRefactorDatetime", json, true);
        return json;
    }

    public static String codexsTesterJsonRefactorSanitize(Object jsonString, boolean debug) throws Exception {
        if (!codexsTesterCheckJsonCompatibility(jsonString, debug)) return jsonString.toString();
        if (debug) codexsHelperLogTerm("codexsTesterJsonRefactorSanitize is running", jsonString, true);

        String json = jsonString.toString()
                .replaceAll("\\[([0-9a-zA-Z ]+\")", "[\"$1")
                .replaceAll("(\"[0-9a-zA-Z ]+)\\]", "$1\"]")
                .replaceAll("\"?(true|false|null)\"?", "$1")
                .replaceAll("(\")([0-9a-zA-Z-_ .]+)(\")(:)(\")?([0-9]+)(\")?", "$1$2$3$4$6")
                /*TODO: Check this line*/
                .replaceAll("(\")+", "\"")
                /*Clear all spaces in final stage*/
                .replaceAll(", \\{", ",{")
                .replaceAll("}, \"", "},\"")
                .replaceAll(", \\[", ",[")
                .replaceAll("\\], \"", "],\"")
                .replaceAll("\" ?, ?\"", "\",\"")
                .replaceAll(", ?\"", ",\"")
                .replaceAll("\"\\[", "\"")
                .replaceAll("\\]\"", "\"")
                .replaceAll("\"\\{", "\"")
                .replaceAll("\\}\"", "\"")
                /* " XYZ123", => "XYZ123", */
                .replaceAll("\" ([0-9a-zA-Z- _!@#$%&*)(=+.,\\?]+)\"(,)?", "\"$1\"$2");

        if (debug) codexsHelperLogTerm("codexsTesterJsonRefactorSanitize", json, true);
        return json;
    }

    public static String codexsTesterJsonRefactorArrayNumber(Object jsonString, boolean debug) throws Exception {
        if (!codexsTesterCheckJsonCompatibility(jsonString, debug)) return jsonString.toString();
        if (debug) codexsHelperLogTerm("codexsTesterJsonRefactorArrayNumber is running", jsonString, true);

        String json = jsonString.toString().replaceAll("(\")([0-9]+)(\")", "$2");

        if (debug) codexsHelperLogTerm("codexsTesterJsonRefactorArrayNumber", json, true);
        return json;
    }

    public static String codexsTesterJsonRefactor(String refactorMode, Object jsonString, boolean debug) throws Exception {
        if (!codexsTesterCheckJsonCompatibility(jsonString, debug)) return jsonString.toString();
        if (debug) codexsHelperLogTerm("codexsTesterJsonRefactor is running", jsonString, true);

        String jsonRefactor = jsonString.toString();

        switch (refactorMode) {
            case "easy":
                jsonRefactor = codexsTesterJsonRefactorUrl(jsonRefactor, debug);
                break;
            case "middle":
                jsonRefactor = codexsTesterJsonRefactorUrl(jsonRefactor, debug);
                jsonRefactor = codexsTesterJsonRefactorEscapeChars(jsonRefactor, debug);
                jsonRefactor = codexsTesterJsonRefactorSpaces(jsonRefactor, debug);
                jsonRefactor = codexsTesterJsonRefactorSanitize(jsonRefactor, debug);
                break;
            case "regular":
                jsonRefactor = codexsTesterJsonRefactorUrl(jsonRefactor, debug);
                jsonRefactor = codexsTesterJsonRefactorEscapeChars(jsonRefactor, debug);
                jsonRefactor = codexsTesterJsonRefactorComplexArray(jsonRefactor, debug);
                jsonRefactor = codexsTesterJsonRefactorObjects(jsonRefactor, debug);
                jsonRefactor = codexsTesterJsonRefactorSanitize(jsonRefactor, debug);
                break;
            case "complex":
                jsonRefactor = codexsTesterJsonRefactorUrl(jsonRefactor, debug);
                jsonRefactor = codexsTesterJsonRefactorEscapeChars(jsonRefactor, debug);
                jsonRefactor = codexsTesterJsonRefactorSpaces(jsonRefactor, debug);
                jsonRefactor = codexsTesterJsonRefactorComplexArray(jsonRefactor, debug);
                jsonRefactor = codexsTesterJsonRefactorObjects(jsonRefactor, debug);
                jsonRefactor = codexsTesterJsonRefactorArrayFromString(jsonRefactor, debug);
                jsonRefactor = codexsTesterJsonRefactorSanitize(jsonRefactor, debug);
                jsonRefactor = codexsTesterJsonRefactorArrayNumber(jsonRefactor, debug);
                break;
            default:
                String error = "\n[EXCEPTION] Invalid refactorMode, use: [easy, middle, regular, complex]\n";
                error += "See the README.md documentation in github project to more details\n";
                throw new RuntimeException(error);
        }

        if (debug) codexsHelperLogTerm("JSON REFACTOR", jsonRefactor, true);
        return jsonRefactor;
    }

    public static org.json.JSONObject codexsTesterObjectToOrgJson(Object object, boolean debug) throws Exception {
        if (debug) codexsHelperLogTerm("codexsTesterObjectToOrgJson is running", object, true);

        String json = object.toString()
                .replaceAll("(^[0-9a-zA-Z]+)(\\()|(=[0-9a-zA-Z]+)(\\))$", "$3")
                .replaceAll("([^0-9a-zA-Z-_!@#$%&*(=+;.,\\/\\]\\[\\\\\\}\\{])$", "")
                .replaceAll("(\\\\u)([0-9]+)(s)", "'$3")
                .replaceAll("\\\\/", "/")
                .replaceAll("(, ?)([a-zA-Z_][0-9a-zA-Z_ ]+)(=)", "$1\"$2\":")
                .replaceAll("^([a-zA-Z_][0-9a-zA-Z_ ]+)(=)", "\"$1\":")
                .replaceAll("\\[([0-9a-zA-Z-_, ]+)\\]", "[\"$1\"]")
                .replaceAll("(\\?)([0-9a-zA-Z_ ]+)(=)", "$1$2°_EQ_°")
                .replaceAll("([a-zA-Z_][0-9a-zA-Z_ ]+)(=)", "\"$1\":")
                .replaceAll("(\\\" ?: ?)(?!null)([a-zA-Z_][0-9a-zA-Z_!@#$%&*)(=+. ]+)", "$1\"$2\"")
                .replaceAll("°_EQ_°", "=");

        json = "{"+json+"}";
        json = codexsTesterJsonRefactorDatetime(json, debug);
        org.json.JSONObject orgJson = codexsTesterParseOrgJsonObject(json, debug);

        if (debug) codexsHelperLogTerm("codexsTesterObjectToOrgJson", json, true);
        return orgJson;
    }

    public static net.minidev.json.JSONObject codexsTesterObjectToNetJson(Object object, boolean debug) throws Exception {
        if (debug) codexsHelperLogTerm("codexsTesterObjectToOrgJson is running", object, true);

        String json = object.toString()
                .replaceAll("(^[0-9a-zA-Z]+)(\\()|(=[0-9a-zA-Z]+)(\\))$", "$3")
                .replaceAll("([^0-9a-zA-Z-_!@#$%&*(=+;.,\\/\\]\\[\\\\\\}\\{])$", "")
                .replaceAll("(\\\\u)([0-9]+)(s)", "'$3")
                .replaceAll("\\\\/", "/")
                .replaceAll("(, ?)([a-zA-Z_][0-9a-zA-Z_ ]+)(=)", "$1\"$2\":")
                .replaceAll("^([a-zA-Z_][0-9a-zA-Z_ ]+)(=)", "\"$1\":")
                .replaceAll("\\[([0-9a-zA-Z-_, ]+)\\]", "[\"$1\"]")
                .replaceAll("(\\?)([0-9a-zA-Z_ ]+)(=)", "$1$2°_EQ_°")
                .replaceAll("([a-zA-Z_][0-9a-zA-Z_ ]+)(=)", "\"$1\":")
                .replaceAll("(\\\" ?: ?)(?!null)([a-zA-Z_][0-9a-zA-Z_!@#$%&*)(=+. ]+)", "$1\"$2\"")
                .replaceAll("°_EQ_°", "=");

        json = "{"+json+"}";
        json = codexsTesterJsonRefactorDatetime(json, debug);
        net.minidev.json.JSONObject netJson = codexsTesterParseNetJsonObject(json, debug);

        if (debug) codexsHelperLogTerm("codexsTesterObjectToOrgJson", json, true);
        return netJson;
    }

    public static boolean codexsTesterCheckJsonCompatibility(Object jsonString, boolean debug) throws Exception {

        String[] compatibleTypes = new String[]{
            "java.util.ArrayList",
            "org.json.JSONArray",
            "org.json.JSONObject",
            "net.minidev.json.JSONObject",
            "net.minidev.json.JSONArray",
            "java.util.List",
            "java.lang.String",
            "java.lang.Object",
            "java.util.HashMap",
            "java.util.Map",
            "java.util.LinkedHashMap",
            "java.util.LinkedHash",
            "java.util.LinkedMap",
            "java.util.LinkedList"
        };

        String foundType = jsonString.getClass().getName();

        if (debug) codexsHelperLogTerm("codexsTesterCheckJsonCompatibility is running", jsonString, true);
        if (debug) codexsHelperLogTerm("foundType", foundType, true);

        try {
            codexsTesterParseOrgJsonObject(jsonString, debug);
        } catch (JSONException je) {
            try {
                codexsTesterParseNetJsonObject(jsonString.toString(), debug);
            } catch (Exception ex) {

                for (String compatibleType : compatibleTypes) {
                    if (compatibleType.equals(foundType)) {
                        return true;
                    }
                }

                String message = "[EXCEPTION]: Invalid JSON Format";
                message += "\n---------------------------------------------------------------------\n";
                message += "\n[JSON]\n";
                message += jsonString;
                message += "\n---------------------------------------------------------------------\n";
                message += "\n[TYPED]\n";
                message += foundType;
                message += "\n---------------------------------------------------------------------\n";
                message += "\n[MESSAGE]\n";
                message += ex.getMessage();
                message += "\n---------------------------------------------------------------------\n";
                throw new RuntimeException(message);
            }
        }

        if (foundType.equals("java.lang.String") || foundType.equals("java.lang.Object")) {
            if (
                    (jsonString.toString().contains("{") && jsonString.toString().contains("}") && jsonString.toString().contains(":")) ||
                    (jsonString.toString().contains("[") && jsonString.toString().contains("]") && jsonString.toString().contains(","))
            ) {
                return true;
            }
        }

        for (String compatibleType : compatibleTypes) {
            if (compatibleType.equals(foundType)) {
                return true;
            }
        }
        return false;
    }

    public static boolean codexsTesterIsNetJson(Object jsonString, boolean debug) {

        if (jsonString == null) return false;

        String[] compatibleTypes = new String[]{
                "net.minidev.json.JSONObject",
                "net.minidev.json.JSONArray"
        };

        String foundType = jsonString.getClass().getName();

        if (debug) codexsHelperLogTerm("codexsTesterIsNetJson is running", jsonString, true);
        if (debug) codexsHelperLogTerm("foundType", foundType, true);

        for (String compatibleType : compatibleTypes) {
            if (compatibleType.equals(foundType)) {
                return true;
            }
        }
        return false;
    }

    public static boolean codexsTesterIsOrgJson(Object jsonString, boolean debug) {

        if (jsonString == null) return false;

        String[] compatibleTypes = new String[]{
                "org.json.JSONArray",
                "org.json.JSONObject"
        };

        String foundType = jsonString.getClass().getName();

        if (debug) codexsHelperLogTerm("codexsTesterIsOrgJson is running", jsonString, true);
        if (debug) codexsHelperLogTerm("foundType", foundType, true);

        for (String compatibleType : compatibleTypes) {
            if (compatibleType.equals(foundType)) {
                return true;
            }
        }
        return false;
    }

    public static net.minidev.json.JSONObject codexsTesterOrgJsonToNetJson(org.json.JSONObject jsonOrg, boolean debug) throws Exception {
        if (debug) codexsHelperLogTerm("codexsTesterOrgJsonToNetJson is running", jsonOrg, true);

        net.minidev.json.JSONObject netJsonResulted = codexsTesterParseNetJsonObject(jsonOrg.toString(), debug);

        if (debug) codexsHelperLogTerm("codexsTesterOrgJsonToNetJson", netJsonResulted, true);
        return netJsonResulted;
    }

    public static org.json.JSONObject codexsTesterNetJsonToOrgJson(net.minidev.json.JSONObject jsonNet, boolean debug) throws Exception {
        if (debug) codexsHelperLogTerm("codexsTesterNetJsonToOrgJson is running", jsonNet, true);

        org.json.JSONObject orgJsonResulted = codexsTesterParseOrgJsonObject(jsonNet.toJSONString(), debug);

        if (debug) codexsHelperLogTerm("codexsTesterNetJsonToOrgJson", orgJsonResulted, true);
        return orgJsonResulted;
    }

    public static org.json.JSONObject codexsTesterOrgJsonFromLinkedHashMap(LinkedHashMap<?, ?> linkedHashMap, Object[] expectedFields, boolean debug) throws Exception {

        org.json.JSONObject jsonResponse = new org.json.JSONObject();

        for (Object field : expectedFields) {
            if (linkedHashMap.containsKey(field.toString())) {
                jsonResponse.put(field.toString(), linkedHashMap.get(field.toString()));
            }
        }

        return jsonResponse;
    }

    public static net.minidev.json.JSONObject codexsTesterNetJsonFromLinkedHashMap(LinkedHashMap<?, ?> linkedHashMap, Object[] expectedFields, boolean debug) throws Exception {

        net.minidev.json.JSONObject jsonResponse = new net.minidev.json.JSONObject();

        for (Object field : expectedFields) {
            if (linkedHashMap.containsKey(field.toString())) {
                jsonResponse.put(field.toString(), linkedHashMap.get(field.toString()));
            }
        }

        return jsonResponse;
    }

}
