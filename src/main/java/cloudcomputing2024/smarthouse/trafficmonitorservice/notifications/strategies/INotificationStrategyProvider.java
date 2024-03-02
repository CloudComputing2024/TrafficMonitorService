package cloudcomputing2024.smarthouse.trafficmonitorservice.notifications.strategies;

import cloudcomputing2024.smarthouse.trafficmonitorservice.notifications.NotificationType;

public interface INotificationStrategyProvider {
    NotificationStrategy getStrategy(NotificationType notificationType);
}
