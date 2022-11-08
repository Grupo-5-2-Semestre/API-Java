package conexao;

import coletardados.PegaDados;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
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
        String identificador = pegadados.getHostname();
        int delay = 5000; //milliseconds
        String sqlInsert = String.format("insert into MaquinasComponentes (fkComponente,fkMaquina,statusMC) VALUES "
                + "(1,6,1),"
                + "(2,6,1)")
        ActionListener taskPerformer = new ActionListener() {

            public void actionPerformed(ActionEvent evt) {
                String queryidCompMaquina = String.format("Select idMaquinaComponente from MaquinasComponentes "
                        + "join Maquina on idMaquina = fkMaquina where numeroSerie = '%s'",identificador);
                List idMaquinaComp = conexao.queryForList(queryidCompMaquina);
                System.out.println(pegadados.pegaDadosJSensor());
                String sql = String.format("insert into [dbo].[LogMaquina] (fkMaquinaComponente,fkTipoValor,valor) values "
                        + "(%d,1,%.0f),(%d,2,%.0f),(%d,3,%.0f),(%d,4,%.0f),(%d,5,%d)",
                        idMaquinaComp.get(0),
                        pegadados.getUsoProcessador(),
                        idMaquinaComp.get(1),
                        pegadados.pegaDadosJSensor(),
                        idMaquinaComp.get(2),
                        pegadados.getMemoriaTotal()/1000000000,
                        idMaquinaComp.get(3),
                        pegadados.getDiscoTotal()
                        
                );
                
                conexao.execute(sql);
                //cpu //1
                //gpu //3
                //ram //2
                //disco //2
                //temp //4
                
            }
        };
  new Timer(delay, taskPerformer).start();
            
            
            
        
    }
}
