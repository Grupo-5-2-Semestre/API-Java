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
        conexao  = new Conexao().ConectaBD();
        PegaDados pegadados = new PegaDados();

        int delay = 5000; //milliseconds
        ActionListener taskPerformer = new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
            try {
                String sql = "insert into LogMaquina (medicao) values (?),(?),(?),(?),(?),(?),(?),(?);";

                PreparedStatement pstm = conexao.prepareStatement(sql);
                pstm.setDouble(1, pegadados.getMemoriaDisponivel());
                pstm.setDouble(2, pegadados.getMemoriaEmUso());
                pstm.setDouble(3, pegadados.getMemoriaTotal());
                pstm.setDouble(4, pegadados.getTempoDeAtividade());
                pstm.setDouble(5, pegadados.getTotalProcessos());
                pstm.setDouble(6, pegadados.getTotalThreads());
                pstm.setDouble(7, pegadados.getUsoProcessador());
                pstm.setDouble(8, pegadados.getNumeroCpusFisicas());
                
                pegadados.pegaDadosJSensor();

                pstm.execute();
                pstm.close();
            } catch (SQLException erro) {
                JOptionPane.showMessageDialog(null, "Iserir dados: " + erro);
            }

            }
        };
  new Timer(delay, taskPerformer).start();
            
            
            
        
    }
}
