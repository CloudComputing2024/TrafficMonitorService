package cloudcomputing2024.smarthouse.trafficmonitorservice.notifications;

import cloudcomputing2024.smarthouse.trafficmonitorservice.notifications.strategies.*;

public enum NotificationType {

    RSocket {
        @Override
        public NotificationStrategy getStrategy() {
            return new RSocketNotificationStrategy();
        }
    }, KAFKA_TOPIC {
        @Override
        public NotificationStrategy getStrategy() {
            return new KafkaTopicNotificationStrategy();
        }
    }, API_REQUEST {
        @Override
        public NotificationStrategy getStrategy() {
            return new ApiRequestNotificationStrategy();
        }
    };

    public abstract NotificationStrategy getStrategy();

}