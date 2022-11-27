package conexao;

import coletardados.PegaDados;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import javax.swing.JOptionPane;
import javax.swing.Timer;

import logar.LogarUsuario;
import org.springframework.jdbc.core.JdbcTemplate;
import slack.SlackBd;
import oshi.jna.platform.linux.LinuxLibc;

public class ConexaoUsuario {

    //data/hora atual
    LocalDateTime agora = LocalDateTime.now();

    // formatar a data
    DateTimeFormatter formatterData = DateTimeFormatter.ofPattern("uuuu/MM/dd");
    String dataFormatada = formatterData.format(agora);

    // formatar a hora
    DateTimeFormatter formatterHora = DateTimeFormatter.ofPattern("HH:mm:ss");
    String horaFormatada = formatterHora.format(agora);

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

    public void getSlackBd() {
        SlackBd slack = new SlackBd();

        try {
            slack.postDisk();
        } catch (IOException e) {
            System.out.println(e);
        }
        try {
            slack.postRam();
        } catch (IOException e) {
            System.out.println(e);
        }
        try {
            slack.postCpuTemp();
        } catch (IOException e) {
            System.out.println(e);
        }
        try {
            slack.postGpuTemp();
        } catch (IOException e) {
            System.out.println(e);
        }
        try {
            slack.postGpuUse();
        } catch (IOException e) {
            System.out.println(e);
        }

    }

    public void guardarDados() {
        JdbcTemplate conexao = new Database().getConnection();
        JdbcTemplate conexao2 = new Database().getConnection2();
        PegaDados pegadados = new PegaDados();
        String identificador = pegadados.getHostname();
        logGenerator.LogInfo.generateLogInfo("Info: Captura de dados iniciada - API Trackio | " + "Data:" + dataFormatada + " Hora:" + horaFormatada + "\n");
        int delay = 5000; //milliseconds
        String sqlIdMaquina = "SELECT idMaquina FROM Maquina where numeroSerie = '" + identificador + "';";
        Object objetoMaquina = conexao.queryForList(sqlIdMaquina);
        String idMaquinaString = objetoMaquina.toString();
        String idMaquina = "";
        for (int i = 0; i < idMaquinaString.length(); i++) {
            if (i > 11 && i < (idMaquinaString.length() - 2)) {
                idMaquina += idMaquinaString.charAt(i);
            }
        }

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
            conexao2.execute(sqlInsert);
        }
        String queryidCompMaquina = String.format("Select idMaquinaComponente from MaquinasComponentes "
                + "join Maquina on idMaquina = fkMaquina where numeroSerie = '%s'", identificador);
        List idMaquinaComp = conexao.queryForList(queryidCompMaquina);

        System.out.println("Preparando para inserir");
        System.out.println("Dados inseridos: ");

        ActionListener taskPerformer = new ActionListener() {

            public void actionPerformed(ActionEvent evt) {

                System.out.println(String.format("insert into [dbo].[LogMaquina] (fkMaquinaComponente,fkTipoValor,valor) values "
                        + "(%d,1,%.0f),(%d,3,%d),(%d,2,%.0f),(%d,2,%d),(%d,2,%.0f),(%d,2,%d),(%d,4,%.0f),(%d,4,%d)",
                        formatarID(idMaquinaComp.get(0)),
                        pegadados.getUsoProcessador(),
                        formatarID(idMaquinaComp.get(1)),
                        pegadados.pegaDadosJSensorRpm(),
                        formatarID(idMaquinaComp.get(2)),
                        pegadados.getMemoriaTotal() / 1000000000,
                        formatarID(idMaquinaComp.get(3)),
                        pegadados.getDiscoTotal() / 1000000000,
                        formatarID(idMaquinaComp.get(4)),
                        pegadados.getMemoriaEmUso() / 1000000000,
                        formatarID(idMaquinaComp.get(5)),
                        pegadados.getDiscoEmUso() / 1000000000,
                        formatarID(idMaquinaComp.get(6)),
                        pegadados.getTemperatura(),
                        formatarID(idMaquinaComp.get(7)),
                        pegadados.pegaDadosJSensorTemp()
                ));

                String sql = String.format("insert into [dbo].[LogMaquina] (fkMaquinaComponente,fkTipoValor,valor) values "
                        + "(%d,1,%.0f),(%d,3,%d),(%d,2,%.0f),(%d,2,%d),(%d,2,%.0f),(%d,2,%d),(%d,4,%.0f),(%d,4,%d)",
                        formatarID(idMaquinaComp.get(0)),
                        pegadados.getUsoProcessador(),
                        formatarID(idMaquinaComp.get(1)),
                        pegadados.pegaDadosJSensorRpm(),
                        formatarID(idMaquinaComp.get(2)),
                        pegadados.getMemoriaTotal() / 1000000000,
                        formatarID(idMaquinaComp.get(3)),
                        pegadados.getDiscoTotal() / 1000000000,
                        formatarID(idMaquinaComp.get(4)),
                        pegadados.getMemoriaEmUso() / 1000000000,
                        formatarID(idMaquinaComp.get(5)),
                        pegadados.getDiscoEmUso() / 1000000000,
                        formatarID(idMaquinaComp.get(6)),
                        pegadados.getTemperatura(),
                        formatarID(idMaquinaComp.get(7)),
                        pegadados.pegaDadosJSensorTemp()
                );

                conexao.execute(sql);
                conexao2.execute(sql);
                //cpu //1
                //gpu //3
                //ram //2
                //disco //2
                //temp //4
            }
        };
        new Timer(delay, taskPerformer).start();

    }

    //Método que envia idMaquina para o Slack
    public String pegaIdMaquina() {
        JdbcTemplate conexao = new Database().getConnection();
        PegaDados pegadados = new PegaDados();
        String identificador = pegadados.getHostname();
        int delay = 5000; //milliseconds
        String sqlIdMaquina = "SELECT idMaquina FROM Maquina where numeroSerie = '" + identificador + "';";
        Object objetoMaquina = conexao.queryForList(sqlIdMaquina);
        String idMaquinaString = objetoMaquina.toString();
        String idMaquina = "";
        for (int i = 0; i < idMaquinaString.length(); i++) {
            if (i > 11 && i < (idMaquinaString.length() - 2)) {
                idMaquina += idMaquinaString.charAt(i);
            }
        }

        return idMaquina;
    }

    public Integer formatarID(Object objetoId) {
        String resultado = objetoId.toString();
        String id = "";
        for (int i = 0; i < resultado.length(); i++) {
            if (i > 20 && i < (resultado.length() - 1)) {
                id += resultado.charAt(i);
            }
        }
        return Integer.parseInt(id);
    }

}
