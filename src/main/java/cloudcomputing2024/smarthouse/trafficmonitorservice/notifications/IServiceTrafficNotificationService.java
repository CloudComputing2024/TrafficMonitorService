package cloudcomputing2024.smarthouse.trafficmonitorservice.notifications;

import cloudcomputing2024.smarthouse.trafficmonitorservice.alerts.TrafficExceededCause;
import cloudcomputing2024.smarthouse.trafficmonitorservice.messages.ServiceTopicDefinition;

public interface IServiceTrafficNotificationService {
    void sendTrafficExceededNotifications(ServiceTopicDefinition definition, TrafficExceededCause cause);
}
