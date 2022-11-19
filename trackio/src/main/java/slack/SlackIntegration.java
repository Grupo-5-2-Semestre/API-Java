package slack;

import com.github.seratch.jslack.Slack;
import com.github.seratch.jslack.api.webhook.Payload;
import com.github.seratch.jslack.api.webhook.WebhookResponse;

public class SlackIntegration {

    private static String webHooksUrl = "https://hooks.slack.com/services/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";
    private static String oAuthToken = "xoxb-4328363629682-4325432005829-W0tK8NCoLLgonlPGVhLYvhLM";
    private static String slackChannel = "alertas";

    public void enviarMensagemCpu(Insersao insersao, String nomeComponente) {
        sendMessageToSlack(String.format("Computador em estado crítico devido ao"
                + " componente %s! Pois seu uso está em %0.f %",
                nomeComponente, insersao.getTemperatura()));
    }

    public void enviarMensagemDisco(Insersao insersao, String nomeComponente) {
        sendMessageToSlack(String.format("Computador em estado crítico devido ao"
                + " componente %s! Pois seu uso está em %d %",
                nomeComponente, insersao.getDiscoEmUso()));
    }

    public void enviarMensagemRam(Insersao insersao, String nomeComponente) {
        sendMessageToSlack(String.format("Computador em estado crítico devido ao"
                + " componente %s! Pois seu uso está em %d %",
                nomeComponente, insersao.getDiscoEmUso()));
    }

    public void enviarMensagemTempGpu(Insersao insersao, String nomeComponente) {
        sendMessageToSlack(String.format("Computador em estado crítico devido ao"
                + " componente %s! Pois seu uso está em %d %",
                nomeComponente, insersao.getDiscoEmUso()));
    }

    public void enviarMensagemUsoGpu(Insersao insersao, String nomeComponente) {
        sendMessageToSlack(String.format("Computador em estado crítico devido ao"
                + " componente %s! Pois seu uso está em %d %",
                nomeComponente, insersao.getDiscoEmUso()));
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
