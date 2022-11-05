
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
public class logs {

	
public static void generateLog(String message)  {
		
Path path = Paths.get("C:/Users/lucas/OneDrive/Área de Trabalho/PI - 2° Semestre/Logs/Trackio-Logs.txt");
		
try{
if(!Files.exists(path)) {
      Files.createDirectory(path);

      }

       File log = new File("C:/Users/lucas/OneDrive/Área de Trabalho/PI - 2° Semestre/Logs/Trackio-Logs.txt");

           if(!log.exists()) {
   log.createNewFile();
  
           }

            FileWriter fw = new FileWriter(log, true);
            BufferedWriter bw = new BufferedWriter(fw);

            bw.write(message);
            bw.newLine();
            
             bw.close();
            fw.close();
            
      } catch(Exception e){

    System.out.println("Deu ruim");

}

}
}

