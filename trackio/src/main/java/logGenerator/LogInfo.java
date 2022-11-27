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
<<<<<<< HEAD

      
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
=======
                              
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
>>>>>>> fb25aaddce30af5a4e88ec4a2ceb53f5b6bf3dae
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

