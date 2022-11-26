package conexao;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Conexao  {
   
    public Connection ConectaBD()  {
        
        Connection conexao = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            
            conexao = DriverManager.getConnection("jdbc:mysql://localhost/trackio", "root", "20020319");
            
       
            
            
        } catch (ClassNotFoundException ex) {
            
            System.out.println("Driver do banco de dados não localizado");
            
        } catch (SQLException ex) {
            
            System.out.println("ERRO ao acessar o banco");
            logGenerator.LogError.generateLogError(" Error: Ocorreu uma falha ao acessar o banco de dados ");
        }
        return conexao;
    }
}
