package conexao;

import org.apache.commons.dbcp2.BasicDataSource;
import org.springframework.jdbc.core.JdbcTemplate;

public class Database {
    private JdbcTemplate connection;
    private JdbcTemplate connection2;

    public Database() {

      BasicDataSource dataSource = new BasicDataSource();
      BasicDataSource dataSource2 = new BasicDataSource();

      dataSource​.setDriverClassName("com.microsoft.sqlserver.jdbc.SQLServerDriver");

      dataSource​.setUrl("jdbc:sqlserver://srv-trackio.database.windows.net:1433;database=bd-trackio");
      
      dataSource​.setUsername("admin-trackio@srv-trackio");

      dataSource​.setPassword("#Gfgrupo5"); 
      
      dataSource​2.setDriverClassName("com.mysql.cj.jdbc.Driver");

      dataSource​2.setUrl("jdbc:mysql://172.17.0.2:3306/bancoTrackio");
      
      dataSource​2.setUsername("root");

      dataSource​2.setPassword("urubu100");  

      dataSource​2.setPassword("urubu100");  */

      this.connection = new JdbcTemplate(dataSource);
      this.connection2 = new JdbcTemplate(dataSource2);

    }
    
    public JdbcTemplate getConnection() {
        
        return connection;

    }
    
    public JdbcTemplate getConnection2() {
        
        return connection2;

    }
}
