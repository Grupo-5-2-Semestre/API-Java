package conexao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JOptionPane;
import logar.LogarUsuario;
import org.apache.commons.dbcp2.BasicDataSource;
import org.springframework.jdbc.core.JdbcTemplate;

public class Database {
    private JdbcTemplate connection;

    public Database() {

      BasicDataSource dataSource = new BasicDataSource();

      dataSource​.setDriverClassName("org.h2.Driver");

      dataSource​.setUrl("jdbc:h2:file:./banco_teste");

      dataSource​.setUsername("sa");

      dataSource​.setPassword("");  

      this.connection = new JdbcTemplate(dataSource);

    }
    
    public ResultSet teste(LogarUsuario logarusuario) {
        String connectionUrl = 
                "jdbc:sqlserver://srv-trackio.database.windows.net:1433;"
                + "database=bd-trackio;"
                + "user=admin-trackio@srv-trackio;"
                + "password=#Gfgrupo5;"
                + "encrypt=true;"
                + "trustServerCertificate=false;"
                + "hostNameInCertificate=*.database.windows.net;"
                + "loginTimeout=30;";

        ResultSet resultSet = null;
        
        try (Connection connection = DriverManager.getConnection(connectionUrl);
                Statement statement = connection.createStatement();) {
            
            String selectSql = "select * from [dbo].[Usuario] where nomeUsuario = '"
                    + logarusuario.getNomeUsuario()
                    + "' and senhaUsuario = '"
                    + logarusuario.getSenhaUsuario()
                    +"'";
            
            resultSet = statement.executeQuery(selectSql);

        }
        // Handle any errors that may have occurred.
        catch (SQLException erro) {
            JOptionPane.showMessageDialog(null, "LogarUsuario: " + erro);
        }
        return resultSet;
    }
    
    public JdbcTemplate getConnection() {

        return connection;

    }
}
