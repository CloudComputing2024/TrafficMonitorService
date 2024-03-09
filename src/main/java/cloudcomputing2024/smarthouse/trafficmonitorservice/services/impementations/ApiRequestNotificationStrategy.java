package cloudcomputing2024.smarthouse.trafficmonitorservice.services.impementations;

import cloudcomputing2024.smarthouse.trafficmonitorservice.domin.datamodel.TrafficExceededAlert;
import cloudcomputing2024.smarthouse.trafficmonitorservice.domin.entities.AlertDefinitionEntity;
import cloudcomputing2024.smarthouse.trafficmonitorservice.services.abstractions.NotificationStrategy;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import reactor.core.publisher.Mono;

@Service
public class ApiRequestNotificationStrategy implements NotificationStrategy {
    private final Logger logger = LoggerFactory.getLogger(ApiRequestNotificationStrategy.class);
    private final ObjectMapper objectMapper;
    private final RestTemplate restTemplate;

    public ApiRequestNotificationStrategy(ObjectMapper objectMapper, RestTemplate restTemplate) {
        this.objectMapper = objectMapper;
        this.restTemplate = restTemplate;
    }

    @Override
    public Mono<Void> Notify(AlertDefinitionEntity alertDefinition, TrafficExceededAlert alert) {
        return Mono.empty();
//        try {
//            HttpHeaders headers = new org.springframework.http.HttpHeaders();
//            headers.setContentType(MediaType.APPLICATION_JSON);
//
//            String alertJson = objectMapper.writeValueAsString(alert);
//            HttpEntity<String> requestEntity = new HttpEntity<>(alertJson, headers);
//            restTemplate.exchange(url, HttpMethod.POST, requestEntity, Void.class);
//            logger.info("Alert notification was sent successfully via API request");
//        } catch (Exception e) {
//            logger.error("An error occurred during a try to send an alert notification via API request");
//        }

    }
}