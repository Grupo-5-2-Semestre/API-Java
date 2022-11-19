package slack;

import com.github.seratch.jslack.Slack;
import com.github.seratch.jslack.api.webhook.Payload;
import com.github.seratch.jslack.api.webhook.WebhookResponse;

public class SlackIntegration {

    private static String webHooksUrl = "https://hooks.slack.com/services/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";
    private static String oAuthToken = "xoxb-4328363629682-4325432005829-W0tK8NCoLLgonlPGVhLYvhLM";
    private static String slackChannel = "alertas";

    public static void main(String[] args) {
        sendMessageToSlack("segunda mensagem");

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
