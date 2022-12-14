package logGenerator;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 *
 * @author lucas
 */
public class LogError {

    public static void generateLogError(String message) {

        Path path = Paths.get("./TrackioLogs");

        try {
            if (!Files.exists(path)) {
                Files.createDirectory(path);
            }

            File log = new File("./TrackioLogs/ErrorLogs.txt");

            if (!log.exists()) {
                log.createNewFile();

            }

            FileWriter fw = new FileWriter(log, true);
            BufferedWriter bw = new BufferedWriter(fw);
            bw.write("----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------\n");
            bw.write(message);
            bw.write("------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------\n");
            bw.newLine();

            bw.close();
            fw.close();
            
             } catch (Exception e) {

            e.printStackTrace();

        }
        
          Path pathCli = Paths.get("./TrackioLogs");
                
                try{
                
                 if (!Files.exists(pathCli)) {
                Files.createDirectory(pathCli);
                
                }
          
              File logCli  = new File("./TrackioLogs/ErrorLogs.txt");

                if (! logCli.exists()) {
              logCli .createNewFile();
              }
             
            FileWriter fw2 = new FileWriter( logCli, true);
            BufferedWriter bw2 = new BufferedWriter(fw2);
            bw2.write("----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------\n");
            bw2.write(message);
            bw2.write("------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------\n");
            bw2.newLine();

            bw2.close();
            fw2.close();

           
   } catch (Exception e) {

            e.printStackTrace();

        }
        
         
        
        
     
}
}