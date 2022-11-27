/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
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
                              
    Path pathGuiDir = Paths.get("./Trackio-JAR");
    Path pathCliDir = Paths.get("./Trackio-JAR-CLI");
    Path pathGui = Paths.get("./Trackio-JAR/Trackio-Logs");
    Path pathCli = Paths.get("./Trackio-JAR-CLI/Trackio-Logs");
		
    try {
            if (Files.exists(pathGuiDir)) {
                if (!Files.exists(pathGui)) {
                    Files.createDirectory(pathGui);
                }
            } else {
                if (!Files.exists(pathCliDir)) {
                    Files.createDirectory(pathCli);
                }
            }

            File log = new File("./Trackio-Logs/InfoLogs.txt");

           if(!log.exists()) {
                log.createNewFile();
  
           }

            FileWriter fw = new FileWriter(log, true);
            BufferedWriter bw = new BufferedWriter(fw);
            
            bw.write("----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------\n");
            bw.write(message);
            bw.write("----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------\n");
            bw.newLine();
            
             bw.close();
            fw.close();
            
      } catch(Exception e){

   e.printStackTrace();
          

   
    
}

}
}

