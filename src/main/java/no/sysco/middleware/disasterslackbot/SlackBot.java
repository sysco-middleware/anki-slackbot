package no.sysco.middleware.disasterslackbot;

import me.ramswaroop.jbot.core.common.EventType;
import me.ramswaroop.jbot.core.common.JBot;
import me.ramswaroop.jbot.core.slack.Bot;
import me.ramswaroop.jbot.core.slack.models.Message;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Profile;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;

import java.io.IOException;

@JBot
@Profile("slack")
public class SlackBot extends Bot {

    private static final Logger logger = LoggerFactory.getLogger(SlackBot.class);

    private WebSocketSession session;

    /**
     * Slack token from application.properties file. You can get your slack token
     * next <a href="https://my.slack.com/services/new/bot">creating a new bot</a>.
     */
    @Value("${slackBotToken}")
    private String slackToken;

    @Value("${disasterRecoveryChannelId}")
    private String channelId;

    @Override
    public String getSlackToken() {
        return slackToken;
    }

    @Override
    public Bot getSlackBot() {
        return this;
    }

    @Override
    public void afterConnectionEstablished(WebSocketSession session) {
        super.afterConnectionEstablished(session);
        this.session = session;
    }

    public void sendMessageToDisasterChannel(String carName, String date, Integer trackId, Integer lap) throws IOException {
        String text = String.format("Car [%s] has crashed!\n" +
                "Date of the crash [%s]\n" +
                "Last known track [%d]\n" +
                "Lap [%d]", carName, date, trackId, lap);

        Message reply = new Message(text);

        reply.setType(EventType.MESSAGE.name().toLowerCase());

        reply.setChannel(channelId);
        session.sendMessage(new TextMessage(reply.toJSONString()));
    }

}
