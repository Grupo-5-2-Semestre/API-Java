package conexao;

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

}
