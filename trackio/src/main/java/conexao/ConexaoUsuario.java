package conexao;

import coletardados.PegaDados;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import javax.swing.JOptionPane;
import javax.swing.Timer;
import logGenerator.logs;
import logar.LogarUsuario;
import org.springframework.jdbc.core.JdbcTemplate;

public class ConexaoUsuario {
    
    Connection conexao;
    
    public ResultSet autenticacaoUsuario (LogarUsuario logarusuario)  {
        conexao = new Conexao().ConectaBD();
        
       
        try {
            String sql = "select * from usuario where nomeUsuario = ? and senhaUsuario = ?";
            
            PreparedStatement pstm = conexao.prepareStatement(sql);
            pstm.setString(1, logarusuario.getNomeUsuario());
            pstm.setString(2, logarusuario.getSenhaUsuario());
            
            ResultSet rs = pstm.executeQuery();
            return rs;
            
        } catch (SQLException erro) {
            
            JOptionPane.showMessageDialog(null, "LogarUsuario: " + erro);
    
         
            
        return null;
    }
    }
    
    public void guardarDados () {
        
                  // data/hora atual
                    LocalDateTime agora = LocalDateTime.now();

                    // formatar a data
                    DateTimeFormatter formatterData = DateTimeFormatter.ofPattern("uuuu/MM/dd");
                    String dataFormatada = formatterData.format(agora);

                    // formatar a hora
                    DateTimeFormatter formatterHora = DateTimeFormatter.ofPattern("HH:mm:ss");
                    String horaFormatada = formatterHora.format(agora);

                 logs.generateLog(" Captura de dados iniciada - API Trackio " + "Data:" + dataFormatada + " Hora:" + horaFormatada );
                    
        
        JdbcTemplate conexao = new Database().getConnection();
        PegaDados pegadados = new PegaDados();

        int delay = 5000; //milliseconds
        ActionListener taskPerformer = new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                String sql = String.format("insert into [dbo].[LogMaquina] (fkMaquinaComponente,fkTipoValor,valor) values "
                        + "(3,1,%.0f),(3,1,%.0f),(3,1,%.0f),(1,1,%.0f),(2,4,%d)",
                        pegadados.getMemoriaDisponivel()/1000000000,
                        pegadados.getMemoriaEmUso()/1000000000,
                        pegadados.getMemoriaTotal()/1000000000,
                        pegadados.getUsoProcessador(),
                        pegadados.pegaDadosJSensor());
                
                 conexao.execute(sql);
                
            }
        };
  new Timer(delay, taskPerformer).start();
            
            
            
        
    }
}
