package logGenerator;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 *
 * @author lucas
 */
public class LogError {

    public static void generateLogError(String message) {

        Path path = Paths.get("./Downloads/trackio/Logs/ErrorLogs.txt");

        try {
            if (!Files.exists(path)) {
                Files.createDirectory(path);
            }

            File log = new File("./Downloads/trackio/Logs/ErrorLogs.txt");

            if (!log.exists()) {
                log.createNewFile();

            }

            FileWriter fw = new FileWriter(log, true);
            BufferedWriter bw = new BufferedWriter(fw);
            bw.write("----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------\n");
            bw.write(message);
            bw.write("----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------\n");
            bw.newLine();

            bw.close();
            fw.close();

<<<<<<< HEAD:trackio/src/main/java/logErrorGenerator/LogError.java
        } catch (IOException e) {
=======
         logGenerator.LogError.generateLogError( "Error:" + e); 
>>>>>>> 7efaaf46a674a73f6a8509cf7290743c8374c181:trackio/src/main/java/logGenerator/LogError.java

            System.out.println("Deu ruim");

        }

    }
}
