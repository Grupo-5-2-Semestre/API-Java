
package logErrorGenerator;

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

   
public static void generateLogError(String message)  {
    
Path path = Paths.get("trackio/Logs/ErrorLogs.txt");
		
try{
if(!Files.exists(path)) {
      Files.createDirectory(path);
      }

       File log = new File("trackio/Logs/ErrorLogs.txt");

           if(!log.exists()) {
   log.createNewFile();
  
           }

            FileWriter fw = new FileWriter(log, true);
            BufferedWriter bw = new BufferedWriter(fw);
            bw.write("-----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------\n");
            bw.write(message);
            bw.write("-----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------\n");
            bw.newLine();
            
             bw.close();
            fw.close();
            
      } catch(Exception e){

    System.out.println("Deu ruim");

   
    
}

}
}
