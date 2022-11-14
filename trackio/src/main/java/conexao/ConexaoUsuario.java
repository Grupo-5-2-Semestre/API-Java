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

    public ResultSet autenticacaoUsuario(LogarUsuario logarusuario) {
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
        System.out.println(identificador);
        String sqlIdMaquina = "SELECT idMaquina FROM Maquina where numeroSerie = '" + identificador + "';";
        Object objetoMaquina = conexao.queryForList(sqlIdMaquina);
        String idMaquinaString = objetoMaquina.toString();
        System.out.println(idMaquinaString);
        String idMaquina = "";
        System.out.println("Estou Aqui");
        for (int i = 0; i < idMaquinaString.length(); i++) {
            if (i > 11 && i < (idMaquinaString.length() - 2)) {
                idMaquina += idMaquinaString.charAt(i);
            }
        }
        System.out.println("Passei do for");
        System.out.println(idMaquina);
        System.out.println("printei id");
        System.out.println(objetoMaquina);

        String sqlExisteComp = "SELECT * FROM MaquinasComponentes where fkMaquina = '" + idMaquina + "';";
        List objetoExisteComp = conexao.queryForList(sqlExisteComp);
        System.out.println(objetoExisteComp);

        if (objetoExisteComp.isEmpty()) {
            System.out.println("Inserindo novos componentes");
            String sqlInsert = String.format("insert into MaquinasComponentes (fkComponente,fkMaquina,statusMC) VALUES "
                    + "(1," + idMaquina + ",1),"
                    + "(2," + idMaquina + ",1),")
                    + "(3," + idMaquina + ",1),"
                    + "(4," + idMaquina + ",1),"
                    + "(6," + idMaquina + ",1),"
                    + "(7," + idMaquina + ",1),"
                    + "(9," + idMaquina + ",1),"
                    + "(10," + idMaquina + ",1);";

            conexao.execute(sqlInsert);
        }
        String queryidCompMaquina = String.format("Select idMaquinaComponente from MaquinasComponentes "
                + "join Maquina on idMaquina = fkMaquina where numeroSerie = '%s'", identificador);
        List idMaquinaComp = conexao.queryForList(queryidCompMaquina);

        ActionListener taskPerformer = new ActionListener() {

            public void actionPerformed(ActionEvent evt) {
                System.out.println("Preparando para inserir");
                System.out.println(pegadados.pegaDadosJSensorRpm());
                System.out.println("Dados inseridos: ");
                System.out.println(String.format("insert into [dbo].[LogMaquina] (fkMaquinaComponente,fkTipoValor,valor) values "
                        + "(%d,1,%.0f),(%d,3,%d),(%d,2,%.0f),(%d,2,%.d),(%d,2,%.0f),(%d,2,%d),(%d,4,%d),(%d,4,%d)",
                        idMaquinaComp.get(0),
                        pegadados.getUsoProcessador(),
                        idMaquinaComp.get(1),
                        pegadados.pegaDadosJSensorRpm(),
                        idMaquinaComp.get(2),
                        pegadados.getMemoriaTotal() / 1000000000,
                        idMaquinaComp.get(3),
                        pegadados.getDiscoTotal()/1000000000,
                        idMaquinaComp.get(4),
                        pegadados.getMemoriaEmUso() / 1000000000,
                        idMaquinaComp.get(5),
                        pegadados.getDiscoEmUso()/1000000000,
                        idMaquinaComp.get(6),
                        pegadados.getTemperatura(),
                        idMaquinaComp.get(7),
                        pegadados.pegaDadosJSensorTemp()
                ));
                
                String sql = String.format("insert into [dbo].[LogMaquina] (fkMaquinaComponente,fkTipoValor,valor) values "
                        + "(%d,1,%.0f),(%d,3,%d),(%d,2,%.0f),(%d,2,%d),(%d,2,%.0f),(%d,2,%d),(%d,4,%.0f),(%d,4,%d)",
                        idMaquinaComp.get(0),
                        pegadados.getUsoProcessador(),
                        idMaquinaComp.get(1),
                        pegadados.pegaDadosJSensorRpm(),
                        idMaquinaComp.get(2),
                        pegadados.getMemoriaTotal() / 1000000000,
                        idMaquinaComp.get(3),
                        pegadados.getDiscoTotal()/1000000000,
                        idMaquinaComp.get(4),
                        pegadados.getMemoriaEmUso() / 1000000000,
                        idMaquinaComp.get(5),
                        pegadados.getDiscoEmUso()/1000000000,
                        idMaquinaComp.get(6),
                        pegadados.getTemperatura(),
                        idMaquinaComp.get(7),
                        pegadados.pegaDadosJSensorTemp()
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
