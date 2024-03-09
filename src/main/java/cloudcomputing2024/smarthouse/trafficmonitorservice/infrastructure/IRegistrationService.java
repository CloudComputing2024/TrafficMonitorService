package cloudcomputing2024.smarthouse.trafficmonitorservice.infrastructure;

import cloudcomputing2024.smarthouse.trafficmonitorservice.presentation.boundaries.AlertDefinitionBoundary;

import java.util.List;

public interface IRegistrationService {
    void RegisterService(String serviceName, List<AlertDefinitionBoundary> alertDefinitionBoundary);
}
