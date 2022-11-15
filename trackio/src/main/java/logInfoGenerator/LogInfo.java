/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package logInfoGenerator;

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

                              
Path path = Paths.get("./Downloads/trackio/Logs/InfoLogs.txt");
		
try{
if(!Files.exists(path)) {
      Files.createDirectory(path);
      }

       File log = new File("./Downloads/trackio/Logs/InfoLogs.txt");

           if(!log.exists()) {
   log.createNewFile();
  
           }

            FileWriter fw = new FileWriter(log, true);
            BufferedWriter bw = new BufferedWriter(fw);
            
            bw.write("-----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------\n");
            bw.write(message);
            bw.write("----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------\n");
            bw.newLine();
            
             bw.close();
            fw.close();
            
      } catch(Exception e){

    System.out.println("Deu ruim");

   
    
}

}
}

