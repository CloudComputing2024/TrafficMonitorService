package cloudcomputing2024.smarthouse.trafficmonitorservice.notifications.strategies;

import cloudcomputing2024.smarthouse.trafficmonitorservice.notifications.NotificationType;
import org.springframework.stereotype.Service;

@Service
public class NotificationStrategyProvider implements INotificationStrategyProvider {
    private final RSocketNotificationStrategy rSocketNotificationStrategy;
    private final KafkaTopicNotificationStrategy kafkaTopicNotificationStrategy;
    private final ApiRequestNotificationStrategy apiRequestNotificationStrategy;

    public NotificationStrategyProvider(RSocketNotificationStrategy rSocketNotificationStrategy, KafkaTopicNotificationStrategy kafkaTopicNotificationStrategy, ApiRequestNotificationStrategy apiRequestNotificationStrategy) {
        this.rSocketNotificationStrategy = rSocketNotificationStrategy;
        this.kafkaTopicNotificationStrategy = kafkaTopicNotificationStrategy;
        this.apiRequestNotificationStrategy = apiRequestNotificationStrategy;
    }

    public NotificationStrategy getStrategy(NotificationType notificationType) {
        return switch (notificationType) {
            case RSocket -> rSocketNotificationStrategy;
            case KafkaTopic -> kafkaTopicNotificationStrategy;
            case ApiRequest -> apiRequestNotificationStrategy;
        };
    }
}
