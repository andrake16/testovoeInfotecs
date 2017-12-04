package utils;

import autotests.Settings;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class Logger {

    public static final String ERROR_FILE_PATH = Settings.DEFAULT_PATH_FULL_PREFIX + "error.txt";

    public static void logException(Exception exception) {

        FileWriter logFile = null;
        PrintWriter printWriter = null;

        try {
            logFile = new FileWriter(ERROR_FILE_PATH,false);
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

}
