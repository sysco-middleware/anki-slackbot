package no.sysco.middleware.disasterslackbot;

import com.fasterxml.jackson.databind.ObjectMapper;
import no.sysco.middleware.disasterslackbot.model.VehicleDelocalized;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.text.ParseException;
import java.util.Collections;
import java.util.Properties;

@SpringBootApplication(scanBasePackages = {"me.ramswaroop.jbot", "no.sysco.middleware"})
public class DisasterSlackbotApplication implements CommandLineRunner {

    private static final Logger logger = LoggerFactory.getLogger(DisasterSlackbotApplication.class);


    @Value("${app.kafka.bootstrap-servers}")
    private String bootstrapServers;

    @Value("${app.kafka.consumer-group-id}")
    private String groupId;

    @Value("${app.kafka.dister-recovery-topic}")
    private String disasterRecoveryTopic;

    @Bean
	public RestTemplate restTemplate() {
		return new RestTemplate();
	}

	@Autowired
    SlackBot bot;

	public static void main(String[] args) {
		SpringApplication.run(DisasterSlackbotApplication.class, args);
	}

    @Override
    public void run(String... args) throws Exception {
        logger.info("Starting disaster recovery slack boot");

        listenToDisasterMessages();
    }

    private void listenToDisasterMessages() {
        logger.info("Creating kafka topic listener for topic [{}]...", disasterRecoveryTopic);

        Properties properties = new Properties();

        properties.setProperty(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServers);
        properties.setProperty(ConsumerConfig.GROUP_ID_CONFIG, groupId);
        properties.setProperty(ConsumerConfig.ENABLE_AUTO_COMMIT_CONFIG, "false");

        //only uncomment this if you want all the previous messages on the topic to be processed
        properties.setProperty(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, "earliest");

        properties.setProperty(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());
        properties.setProperty(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());

        KafkaConsumer<String, String> kafkaConsumer = new KafkaConsumer<>(properties);

        kafkaConsumer.subscribe(Collections.singleton(disasterRecoveryTopic));

        ObjectMapper mapper = new ObjectMapper();

        logger.info("Listening to vehicle disaster messages...");

        try {
            while (true) {
                ConsumerRecords<String, String> records = kafkaConsumer.poll(1000);

                for (ConsumerRecord<String, String> record : records) {
                    logger.info("Received msg [{}]", record.value());

                    if(record.value().contains("VEHICLE_DELOCALIZED")) { //just in case check... if other messages end on this topic

                        VehicleDelocalized event = mapper.readValue(record.value(), VehicleDelocalized.class);
                        bot.sendMessageToDisasterChannel(
                                event.getCarName(),
                                event.getDateTimeString(),
                                event.getLastKnownTrack(),
                                event.getLap());
                    }

                }
            }
        } catch (IOException e) {
            logger.error("Error occured while listening to messages. Stopping the listener!", e);
            throw new RuntimeException(e);
        }
    }
}
