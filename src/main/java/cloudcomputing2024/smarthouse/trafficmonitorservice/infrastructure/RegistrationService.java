package cloudcomputing2024.smarthouse.trafficmonitorservice.infrastructure;

import cloudcomputing2024.smarthouse.trafficmonitorservice.presentation.boundaries.AlertDefinitionBoundary;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RegistrationService implements IRegistrationService {
    @Override
    public void RegisterService(String serviceName, List<AlertDefinitionBoundary> alertDefinitionBoundary) {

    }
}
