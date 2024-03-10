package cloudcomputing2024.smarthouse.trafficmonitorservice.infrastructure.Kafka;

import cloudcomputing2024.smarthouse.trafficmonitorservice.presentation.boundaries.MessageBoundary;
import cloudcomputing2024.smarthouse.trafficmonitorservice.services.abstractions.IKafkaMessageHandlerWorkflow;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.annotation.PostConstruct;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.function.Consumer;

@Configuration
public class KafkaMessageListener {
    private ObjectMapper jackson;
    private final Log logger = LogFactory.getLog(KafkaMessageListener.class);
    private final IKafkaMessageHandlerWorkflow messageHandlerWorkflow;

    public KafkaMessageListener(IKafkaMessageHandlerWorkflow messageHandlerWorkflow) {
        this.messageHandlerWorkflow = messageHandlerWorkflow;
    }
    
    @PostConstruct
    public void init() {
        this.jackson = new ObjectMapper();
    }

    @Bean
    public Consumer<String> messageSink(){
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
                this.logger.error(e);
            }
        };
    }
}
