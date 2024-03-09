package cloudcomputing2024.smarthouse.trafficmonitorservice.infrastructure.Kafka;

import java.util.HashMap;
import java.util.function.Consumer;

import cloudcomputing2024.smarthouse.trafficmonitorservice.infrastructure.IMonitoringService;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.fasterxml.jackson.databind.ObjectMapper;

import jakarta.annotation.PostConstruct;

@Configuration
public class DemoMessageListener {
    private ObjectMapper jackson;
    private IMonitoringService monitoringService;
    private Log logger = LogFactory.getLog(DemoMessageListener.class);

    public DemoMessageListener(IMonitoringService monitoringService) {
        this.monitoringService = monitoringService;
    }

    @PostConstruct
    public void init() {
        this.jackson = new ObjectMapper();
    }

    @Bean
    public Consumer<String> demoMessageSink(){
        return stringInput->{
//            try {
//                this.logger.trace("*** received: " + stringInput);
//
//                DemoMessageBoundary message = this.jackson.readValue(stringInput, DemoMessageBoundary.class);
//
//                if (message.getMessageDetails() == null) {
//                    message.setMessageDetails(new HashMap<>());
//                }
//                message.getMessageDetails()
//                        .put("status", "received-from-kafka");
//
//                DemoMessageBoundary storedMessage = this.demoService
//                        .store(message)
//                        .block();
//
//                this.logger.info("*** stored: " + storedMessage);
//            }catch (Exception e) {
//                e.printStackTrace();
//                this.logger.error(e);
//            }
        };
    }
}
