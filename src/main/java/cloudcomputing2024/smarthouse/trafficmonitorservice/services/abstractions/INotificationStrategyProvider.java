package cloudcomputing2024.smarthouse.trafficmonitorservice.services.abstractions;

import cloudcomputing2024.smarthouse.trafficmonitorservice.domin.datamodel.NotificationType;

public interface INotificationStrategyProvider {
    NotificationStrategy getStrategy(NotificationType notificationType);
}
