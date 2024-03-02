package cloudcomputing2024.smarthouse.trafficmonitorservice.notifications.strategies;

import cloudcomputing2024.smarthouse.trafficmonitorservice.messages.AlertDefinition;
import cloudcomputing2024.smarthouse.trafficmonitorservice.alerts.TrafficExceededAlert;

public class KafkaTopicNotificationStrategy implements NotificationStrategy {
    @Override
    public void Notify(AlertDefinition alertDefinition, TrafficExceededAlert alert) {
        System.out.printf("KafkaTopicNotificationStrategy: Notify to %s by %s because of %s exceeded.\n",  alertDefinition.notificationType().name(), alert.cause());
    }
}
