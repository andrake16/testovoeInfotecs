package utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class Pinger {

    public static void ping(String address)  {

        List<String> commands = new ArrayList<String>();
        commands.add("ping");
        commands.add("-n");
        commands.add("10");
        commands.add(address);

        ProcessBuilder processBuilder = new ProcessBuilder(commands);
        Process process = null;
        BufferedReader brProcessInputStream = null;
        BufferedReader brProcessErrorStream = null;
        String line = "";
        StringBuilder fullPingResponse = new StringBuilder();

        try {
            process = processBuilder.start();
            System.out.println("\n ping starts \n");
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
            System.out.println("\n is reachable");
        }
        else System.out.println("\n not reachable");

    }


}
