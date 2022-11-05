package conexao;

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
      
     /* dataSource​.setDriverClassName("com.mysql.cj.jdbc.Driver");

      dataSource​.setUrl("jdbc:mysql://172.31.17.43:3306/trackio");
      
      dataSource​.setUsername("root");

      dataSource​.setPassword("urubu100");  */

      this.connection = new JdbcTemplate(dataSource);

    }
    
    public JdbcTemplate getConnection() {
        
        return connection;

    }
}
