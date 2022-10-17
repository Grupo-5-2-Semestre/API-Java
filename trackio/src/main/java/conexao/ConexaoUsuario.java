package conexao;

import coletardados.PegaDados;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.Timer;
import logar.LogarUsuario;
import org.springframework.jdbc.core.JdbcTemplate;

public class ConexaoUsuario {
    
    Connection conexao;
    
    public ResultSet autenticacaoUsuario (LogarUsuario logarusuario) {
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

    public void guardarDados() {
        JdbcTemplate conexao = new Database().getConnection();
        PegaDados pegadados = new PegaDados();

        int delay = 5000; //milliseconds
        ActionListener taskPerformer = new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                String sql = String.format("insert into [dbo].[LogMaquina] (fkMaquinaComponente,fkTipoValor,valor) values "
                        + "(3,1,%.2f),(3,1,%.2f),(3,1,%.2f),(1,1,%.2f)",
                        pegadados.getMemoriaDisponivel()/10000,
                        pegadados.getMemoriaEmUso()/10000,
                        pegadados.getMemoriaTotal()/10000,
                        pegadados.getUsoProcessador());
                pegadados.pegaDadosJSensor();
                
                conexao.execute(sql);
            }
        };
  new Timer(delay, taskPerformer).start();
            
            
            
        
    }
}
