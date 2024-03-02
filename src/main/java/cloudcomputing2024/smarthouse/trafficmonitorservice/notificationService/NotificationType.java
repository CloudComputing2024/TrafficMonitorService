package cloudcomputing2024.smarthouse.trafficmonitorservice.notificationService;

public enum NotificationType {

    EMAIL {
        @Override
        public INotificationStrategy getStrategy() {
            return new EmailNotificationStrategy();
        }
    },
    SMS {
        @Override
        public INotificationStrategy getStrategy() {
            return new SmsNotificationStrategy();
        }
    },
    KAFKA_TOPIC {
        @Override
        public INotificationStrategy getStrategy() {
            return new KafkaTopicNotificationStrategy();
        }
    },
    API_REQUEST {
        @Override
        public INotificationStrategy getStrategy() {
            return new ApiRequestNotificationStrategy();
        }
    };
    public abstract INotificationStrategy getStrategy();

}