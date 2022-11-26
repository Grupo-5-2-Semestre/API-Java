package slack;

import conexao.ConexaoUsuario;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import javax.swing.Timer;
import logGenerator.LogError;

/**
 *
 * @author giova
 */
public class SlackBd {

    SlackIntegration conexaoSlack = new SlackIntegration();
    ConexaoUsuario conexaoBd = new ConexaoUsuario();
    LogError log = new LogError();
    Insercao insersao = new Insercao();

    public void postDisk() throws IOException {
        try {
            if (insersao.getDiscoEmUso() > 90) {
                conexaoSlack.enviarMensagemDisco(insersao, "Disco");
            }
        } catch (Exception e) {
            System.out.println(e);
            log.generateLogError(String.format("Erro ao enviar para o Slack! \n Erro: %s", e));
        }

    }

    public void postRam() throws IOException {
        try {
            if (insersao.getMemoriaEmUso() > 80) {
                conexaoSlack.enviarMensagemRam(insersao, "Ram");
            }
        } catch (Exception e) {
            System.out.println(e);
            log.generateLogError(String.format("Erro ao enviar para o Slack! \n Erro: %s", e));
        }

    }

    public void postCpuTemp() throws IOException {
        try {
            if (insersao.getTemperatura() > 65 || insersao.getTemperatura() < 40) {
                conexaoSlack.enviarMensagemCpu(insersao, "Processador");
            }
        } catch (Exception e) {
            System.out.println(e);
            log.generateLogError(String.format("Erro ao enviar para o Slack! \n Erro: %s", e));
        }

    }

    public void postGpuTemp() throws IOException {
        try {
            if (insersao.getDadosJsensorTemp() > 85 || insersao.getDadosJsensorTemp() < 55) {
                conexaoSlack.enviarMensagemTempGpu(insersao, "GPU Temperatura");
            }
        } catch (Exception e) {
            System.out.println(e);
            log.generateLogError(String.format("Erro ao enviar para o Slack! \n Erro: %s", e));
        }

    }

    public void postGpuUse() throws IOException {
        try {
            if (insersao.getDadosJsensorRpm() > 2600 || insersao.getDadosJsensorRpm() < 1000) {
                conexaoSlack.enviarMensagemUsoGpu(insersao, "GPU Uso");
            }
        } catch (Exception e) {
            System.out.println(e);
            log.generateLogError(String.format("Erro ao enviar para o Slack! \n Erro: %s", e));
        }

    }

}
