package cloudcomputing2024.smarthouse.trafficmonitorservice.notifications.strategies;

import cloudcomputing2024.smarthouse.trafficmonitorservice.notifications.NotificationType;
import org.springframework.stereotype.Service;

@Service
public class NotificationStrategyProvider implements INotificationStrategyProvider {
    private final KafkaTopicNotificationStrategy kafkaTopicNotificationStrategy;
    private final ApiRequestNotificationStrategy apiRequestNotificationStrategy;

    public NotificationStrategyProvider(KafkaTopicNotificationStrategy kafkaTopicNotificationStrategy, ApiRequestNotificationStrategy apiRequestNotificationStrategy) {
        this.kafkaTopicNotificationStrategy = kafkaTopicNotificationStrategy;
        this.apiRequestNotificationStrategy = apiRequestNotificationStrategy;
    }

    public NotificationStrategy getStrategy(NotificationType notificationType) {
        return switch (notificationType) {
            case KafkaTopic -> kafkaTopicNotificationStrategy;
            case ApiRequest -> apiRequestNotificationStrategy;
        };
    }
}
