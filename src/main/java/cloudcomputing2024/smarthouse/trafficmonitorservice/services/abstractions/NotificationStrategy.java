package cloudcomputing2024.smarthouse.trafficmonitorservice.services.abstractions;

import cloudcomputing2024.smarthouse.trafficmonitorservice.presentation.boundaries.AlertDefinitionBoundary;
import cloudcomputing2024.smarthouse.trafficmonitorservice.domin.datamodel.TrafficExceededAlert;

public interface NotificationStrategy {
    void Notify(AlertDefinitionBoundary alertDefinitionBoundary, TrafficExceededAlert alert);
}
