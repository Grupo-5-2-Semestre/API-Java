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
public class LogInfo {
   
	
public static void generateLogInfo(String message)  {

      
         Path pathGui = Paths.get("./Downloads/Trackio-JAR/Logs");
     
      
      
        try {
                if (!Files.exists(pathGui)) {
                Files.createDirectory(pathGui);
            }
          
        File logGui = new File("./Downloads/Trackio-JAR/Logs/InfoLogs.txt");
     
      
             if (! logGui.exists()) {
             logGui.createNewFile();
             
             }
             
       FileWriter fw = new FileWriter(logGui, true);
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
        
          Path pathCli = Paths.get("./Downloads/Trackio-JAR-CLI/Logs");
                
                try{
                
                 if (!Files.exists(pathCli)) {
                Files.createDirectory(pathCli);
                
                }
          
              File logCli  = new File("./Downloads/Trackio-JAR-CLI/Logs/InfoLogs.txt");

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

