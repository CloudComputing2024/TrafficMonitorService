package cloudcomputing2024.smarthouse.trafficmonitorservice.infrastructure.Kafka;

import java.util.HashMap;
import java.util.function.Consumer;

import cloudcomputing2024.smarthouse.trafficmonitorservice.presentation.boundaries.MessageBoundary;
import cloudcomputing2024.smarthouse.trafficmonitorservice.services.abstractions.IKafkaMessageHandlerWorkflow;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.fasterxml.jackson.databind.ObjectMapper;

import jakarta.annotation.PostConstruct;

@Configuration
public class KafkaMessageListener {
    private ObjectMapper jackson;
    private Log logger = LogFactory.getLog(KafkaMessageListener.class);
    private IKafkaMessageHandlerWorkflow messageHandlerWorkflow;

    public KafkaMessageListener(IKafkaMessageHandlerWorkflow messageHandlerWorkflow) {
        this.messageHandlerWorkflow = messageHandlerWorkflow;
    }

    @PostConstruct
    public void init() {
        this.jackson = new ObjectMapper();
    }

    @Bean
    public Consumer<String> demoMessageSink(){
        return stringInput->{
            try {
                this.logger.trace("*** received: " + stringInput);

                MessageBoundary message = this.jackson.readValue(stringInput, MessageBoundary.class);

                if (message.getMessageDetails() == null) {
                    message.setMessageDetails(new HashMap<>());
                }

                messageHandlerWorkflow.HandleMessage(message);

                this.logger.info("*** store d: " );
            }catch (Exception e) {
                e.printStackTrace();
                this.logger.error(e);
            }
        };
    }
}
