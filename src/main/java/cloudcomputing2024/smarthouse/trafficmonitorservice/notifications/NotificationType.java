package cloudcomputing2024.smarthouse.trafficmonitorservice.notifications;

import cloudcomputing2024.smarthouse.trafficmonitorservice.notifications.strategies.*;

public enum NotificationType {

    EMAIL {
        @Override
        public NotificationStrategy getStrategy() {
            return new EmailNotificationStrategy();
        }
    }, SMS {
        @Override
        public NotificationStrategy getStrategy() {
            return new SmsNotificationStrategy();
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