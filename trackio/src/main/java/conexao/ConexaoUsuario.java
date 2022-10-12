package conexao;

import coletardados.PegaDados;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;
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

        String sql = "insert into LogMaquina (medicao) values (?),(?),(?),(?),(?),(?),(?),(?);";
        try {
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
        } catch (Exception erro) {
            JOptionPane.showMessageDialog(null, "Iserir dados: " + erro);
        }
    }
}
