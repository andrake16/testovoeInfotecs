package utils;

import autotests.Settings;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.json.JSONObject;
import org.json.XML;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;

public class XmlJSONconverter {
    private static final String ENCODING = "UTF8";
    private static final String INPUT_XML_DEFAULT_PATH = "toJSON.xml";
    private static final String OUTPUT_JSON_DEFAULT_PATH = Settings.DEFAULT_PATH_FULL_PREFIX + "convertedJson.txt";

    public static void convertXMLtoJSON() {
       convertXMLtoJSON(INPUT_XML_DEFAULT_PATH, OUTPUT_JSON_DEFAULT_PATH);
    }

    private static void convertXMLtoJSON(String inputFilePath, String outputFilePAth) {

        System.out.println("start partsing xml file");

        String xmlString = "";

        try {
            InputStream inputStream = XmlJSONconverter.class.getClassLoader().getResourceAsStream(inputFilePath);
            xmlString = IOUtils.toString(inputStream,ENCODING);
            xmlString = xmlString.replaceAll("[\u00A0]+","");
            //System.out.println(xmlString);
            JSONObject jsonObject = XML.toJSONObject(xmlString);
            String jsonPrettyPrintString = jsonObject.toString();
            FileUtils.writeStringToFile(new File(outputFilePAth),jsonPrettyPrintString,ENCODING,false);
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("end converting try to JSON");
    }

}
