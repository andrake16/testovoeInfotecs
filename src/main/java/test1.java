import java.io.*;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import com.google.common.io.Resources;
import org.apache.commons.io.FileUtils;
import org.json.JSONObject;
import org.json.XML;
import xmlJsonUtils.converter;


public class test1 {
    public static void main(String[] args)  {
        createDummyFile();
        pingInfotecs();
        converter.convertXMLtoJSON();

    }

    private static void createDummyFile() {
        final String filepath = "e:\\test.txt";
        final String dataToWrite = "z";
        //Long amountOfDataToWrite = 1024L * 1024L * 1024L;
        Long amountOfDataToWrite = 1024L * 1024L;

        File file = new File(filepath);
        FileWriter fileWriter = null;
        BufferedWriter bufferedWriter = null;

        try {
            fileWriter = new FileWriter(file);
            bufferedWriter = new BufferedWriter(fileWriter);
            for(long i = 0 ; i < amountOfDataToWrite ; i++)
                bufferedWriter.write(dataToWrite);

        } catch (IOException e) {
            e.printStackTrace();
            logException(e);

        }

        finally {
            try {
                if(bufferedWriter != null)
                    bufferedWriter.close();
                if(fileWriter != null)
                    fileWriter.close();
            } catch (IOException e) {
                e.printStackTrace();
                logException(e);
            }

        }
    }

    private static void logException(Exception exception) {

        final String logFilePath = "e:\\error.txt";

        FileWriter logFile = null;
        PrintWriter printWriter = null;

        try {
            logFile = new FileWriter(logFilePath,true);
            printWriter = new PrintWriter(logFile);
            exception.printStackTrace(printWriter);

        } catch (IOException e) {
            e.printStackTrace();
        }

        finally {
            try {
                if (printWriter != null) {
                    printWriter.close();
                }
                if (logFile != null) {
                    logFile.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }



    }

    private static void pingInfotecs()  {
        String hostAdress = "infotecs.ru";

        List<String> commands = new ArrayList<String>();
        commands.add("ping");
        commands.add("-n");
        commands.add("10");
        commands.add(hostAdress);

        ProcessBuilder processBuilder = new ProcessBuilder(commands);
        Process process = null;
        BufferedReader brProcessInputStream = null;
        BufferedReader brProcessErrorStream = null;
        String line = "";
        StringBuilder fullPingResponse = new StringBuilder();

        try {
            process = processBuilder.start();
            System.out.println("ping starts");
            try {
                boolean finished = process.waitFor(10, TimeUnit.SECONDS);
                if(!finished) process.destroy();
                process.waitFor(1,TimeUnit.SECONDS);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        if (process != null) {
            brProcessInputStream = new BufferedReader( new InputStreamReader(process.getInputStream()) );
            brProcessErrorStream = new BufferedReader( new InputStreamReader(process.getErrorStream()) );

            try {
                while( (line = brProcessInputStream.readLine()) != null) {
                    System.out.println(line);
                    fullPingResponse.append(line);
                }
                while( (line = brProcessErrorStream.readLine()) != null )
                    System.out.println(line);
            } catch (IOException e) {
                e.printStackTrace();
            }
            finally {
                try {
                    brProcessInputStream.close();
                    brProcessErrorStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        if(fullPingResponse.toString().contains("Reply from")) {
            System.out.println("is reachable");
        }
        else System.out.println("not reachable");

    }

    private static void convertXMLtoJSON() {

        String filepath = "toJSON.xml";
        String xmlString = "";

        URL url = Resources.getResource(filepath);

        try {
            File file = new File(url.toURI());
            xmlString = FileUtils.readFileToString(file, "UTF-8");
            xmlString = xmlString.replaceAll("[\u00A0]+","");
            //System.out.println(xmlString);
            JSONObject jsonObject = XML.toJSONObject(xmlString);
            String jsonPrettyPrintString = jsonObject.toString();
            FileUtils.writeStringToFile(new File("e:\\convertedJson.txt"),jsonPrettyPrintString,"UTF-8",false);
        } catch (URISyntaxException | IOException e) {
            e.printStackTrace();
        }



    }







}
