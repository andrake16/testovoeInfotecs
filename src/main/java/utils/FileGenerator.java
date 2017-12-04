package utils;

import autotests.Settings;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class FileGenerator {

    private static final String OUTPUT_FILE_PATH = Settings.DEFAULT_PATH_FULL_PREFIX + "test.txt";

    public static void createDummyFile() {

        System.out.println("start generate file");

        final String dataToWrite = "z";
        Long amountOfDataToWrite = 1024L * 1024L * 1024L;

        File file = new File(OUTPUT_FILE_PATH);
        FileWriter fileWriter = null;
        BufferedWriter bufferedWriter = null;

        try {
            Files.createDirectories(Paths.get(Settings.DEFAULT_PATH_FULL_PREFIX));
            fileWriter = new FileWriter(file);
            bufferedWriter = new BufferedWriter(fileWriter);
            for(long i = 0 ; i < amountOfDataToWrite ; i++)
                bufferedWriter.write(dataToWrite);

        } catch (IOException e) {
            e.printStackTrace();
            Logger.logException(e);

        }

        finally {
            try {
                if(bufferedWriter != null)
                    bufferedWriter.close();
                if(fileWriter != null)
                    fileWriter.close();
            } catch (IOException e) {
                e.printStackTrace();
                Logger.logException(e);
            }

        }

        System.out.println("end generate file");
    }

}
