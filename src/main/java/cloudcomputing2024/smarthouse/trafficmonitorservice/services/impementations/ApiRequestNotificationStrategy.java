package cloudcomputing2024.smarthouse.trafficmonitorservice.services.impementations;

import cloudcomputing2024.smarthouse.trafficmonitorservice.domin.datamodel.TrafficExceededAlert;
import cloudcomputing2024.smarthouse.trafficmonitorservice.domin.entities.AlertDefinitionEntity;
import cloudcomputing2024.smarthouse.trafficmonitorservice.services.abstractions.NotificationStrategy;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import reactor.core.publisher.Mono;

@Service
public class ApiRequestNotificationStrategy implements NotificationStrategy {
    private final Logger logger = LoggerFactory.getLogger(ApiRequestNotificationStrategy.class);
    private static final String UrlParameter = "url";
    private static final String RequestBodyParameter = "requestBody";

    private final RestTemplate restTemplate;

    public ApiRequestNotificationStrategy(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Override
    public Mono<Void> notify(AlertDefinitionEntity definition, TrafficExceededAlert alert) {
        try {
            var url = definition.parameters().get(UrlParameter).toString();
            var requestBody = definition.parameters().get(RequestBodyParameter).toString();

            var headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);
            var requestEntity = new HttpEntity<>(requestBody, headers);

            logger.info("Sending notification to url: {} because of alert: {}", url, alert);
            return Mono.fromRunnable(() -> restTemplate.exchange(url, HttpMethod.POST, requestEntity, Void.class));
        } catch (Exception e) {
            logger.error("An error occurred during a try to send an alert notification via API request");
            return Mono.error(e);
        }
    }
}
