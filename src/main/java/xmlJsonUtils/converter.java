package xmlJsonUtils;

import com.google.common.io.Resources;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.json.JSONObject;
import org.json.XML;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URISyntaxException;
import java.net.URL;

public class converter {

    public static void convertXMLtoJSON() {

        String filepath = "toJSON.xml";
        String xmlString = "";

        try {
            InputStream inputStream = converter.class.getClassLoader().getResourceAsStream(filepath);
            xmlString = IOUtils.toString(inputStream,"UTF-8");
            xmlString = xmlString.replaceAll("[\u00A0]+","");
            //System.out.println(xmlString);
            JSONObject jsonObject = XML.toJSONObject(xmlString);
            String jsonPrettyPrintString = jsonObject.toString();
            FileUtils.writeStringToFile(new File("e:\\convertedJson.txt"),jsonPrettyPrintString,"UTF-8",false);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}
