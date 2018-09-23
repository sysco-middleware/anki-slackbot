# anki-slackbot
Slack bot that monitors kafka topic and send slack messages when vehicle is out of the track.

## Setup

### Slackbot token
In order to run the application you need to specify slackbot token in the application.properties, under slackBotToken.

### Kafka configuration
In the application.properties you need to specify:
- bootstrap servers (app.kafka.bootstrap-servers)
- topic where VEHICLE_DELOCALIZED messages are (app.kafka.dister-recovery-topic)

# Run
execute mvn clean install or mvn spring-boot:run
