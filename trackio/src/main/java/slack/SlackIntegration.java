package slack;

import com.github.seratch.jslack.Slack;
import com.github.seratch.jslack.api.webhook.Payload;
import com.github.seratch.jslack.api.webhook.WebhookResponse;
import conexao.Conexao;
import conexao.ConexaoUsuario;
import java.sql.Connection;

public class SlackIntegration {

    private static String webHooksUrl = "https://hooks.slack.com/services/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";
    private static String oAuthToken = "xoxb-4328363629682-4325432005829-W0tK8NCoLLgonlPGVhLYvhLM";
    private static String slackChannel = "alertas";
    ConexaoUsuario conexao = new ConexaoUsuario();

    public void enviarMensagemCpu(Insercao insersao, String nomeComponente) {
        sendMessageToSlack(String.format("Computador com o ID %s em estado crítico devido ao"
                + " componente %s! Pois seu uso está em %.0f%%", conexao.pegaIdMaquina(),
                nomeComponente, insersao.getTemperatura()));
    }

    public void enviarMensagemDisco(Insercao insersao, String nomeComponente) {
        sendMessageToSlack(String.format("Computador com o ID %s em estado crítico devido ao"
                + " componente %s! Pois seu uso está em %d%%", conexao.pegaIdMaquina(),
                nomeComponente, insersao.getDiscoEmUso()));
    }

    public void enviarMensagemRam(Insercao insersao, String nomeComponente) {
        sendMessageToSlack(String.format("Computador com o ID %s em estado crítico devido ao"
                + " componente %s! Pois seu uso está em %.0f%%", conexao.pegaIdMaquina(),
                nomeComponente, insersao.getMemoriaEmUso()));
    }

    public void enviarMensagemTempGpu(Insercao insersao, String nomeComponente) {
        sendMessageToSlack(String.format("Computador com o ID %s em estado crítico devido ao"
                + " componente %s! Pois seu uso está em %d%%", conexao.pegaIdMaquina(),
                nomeComponente, insersao.getDadosJsensorTemp()));
    }

    public void enviarMensagemUsoGpu(Insercao insersao, String nomeComponente) {
        sendMessageToSlack(String.format("Computador com o ID %s em estado crítico devido ao"
                + " componente %s! Pois seu uso está em %d%%", conexao.pegaIdMaquina(),
                nomeComponente, insersao.getDadosJsensorRpm()));
    }

    public static void sendMessageToSlack(String message) {
        try {
            StringBuilder msgbuilder = new StringBuilder();
            msgbuilder.append(message);

            Payload payload = Payload.builder().channel(slackChannel).text(msgbuilder.toString()).build();

            WebhookResponse wbResp = Slack.getInstance().send(webHooksUrl, payload);

        } catch (Exception e) {
            e.printStackTrace();
            
          

        }

    }
}
