package conexao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import javax.swing.JOptionPane;
import logar.LogarUsuario;
import org.apache.commons.dbcp2.BasicDataSource;
import org.springframework.jdbc.core.JdbcTemplate;

public class Database {
    private JdbcTemplate connection;

    public Database() {

      BasicDataSource dataSource = new BasicDataSource();

      dataSource​.setDriverClassName("com.microsoft.sqlserver.jdbc.SQLServerDriver");

      dataSource​.setUrl("jdbc:sqlserver://srv-trackio.database.windows.net:1433;database=bd-trackio");
      
      dataSource​.setUsername("admin-trackio@srv-trackio");

      dataSource​.setPassword("#Gfgrupo5");  

      this.connection = new JdbcTemplate(dataSource);

    }
    
    public JdbcTemplate getConnection() {
        
        return connection;

    }
}
