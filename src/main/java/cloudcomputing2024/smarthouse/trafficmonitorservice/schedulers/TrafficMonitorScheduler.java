package cloudcomputing2024.smarthouse.trafficmonitorservice.schedulers;

import cloudcomputing2024.smarthouse.trafficmonitorservice.infrastructure.ServiceTopicDefinitionRepository;
import cloudcomputing2024.smarthouse.trafficmonitorservice.infrastructure.ServiceTopicMessageCounterService;
import cloudcomputing2024.smarthouse.trafficmonitorservice.messages.ServiceTopicDefinition;
import cloudcomputing2024.smarthouse.trafficmonitorservice.alerts.TrafficExceededAlert;
import cloudcomputing2024.smarthouse.trafficmonitorservice.alerts.TrafficExceededCause;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service
public class TrafficMonitorScheduler {
    private final Logger logger;
    private final ServiceTopicDefinitionRepository serviceTopicDefinitionRepository;
    private final ServiceTopicMessageCounterService serviceTopicMessageCounterService;

    public TrafficMonitorScheduler(ServiceTopicDefinitionRepository serviceTopicDefinitionRepository, ServiceTopicMessageCounterService serviceTopicMessageCounterService) {
        this.logger = LoggerFactory.getLogger(TrafficMonitorScheduler.class);
        this.serviceTopicDefinitionRepository = serviceTopicDefinitionRepository;
        this.serviceTopicMessageCounterService = serviceTopicMessageCounterService;
    }

    @Scheduled(fixedRate = 60000) // Runs Every Minute
    public void monitorTraffic() {
        logger.info("Monitoring Traffic");
        var serviceDefinitions = serviceTopicDefinitionRepository.findAll();

        for (var service : serviceDefinitions) {
            if (!isServiceTrafficExceeded(service)) {
                continue;
            }

            logger.warn("Service Traffic Exceeded: " + service.serviceName());
            alertTrafficExceeded(service);
        }

        logger.info("Resetting Service Counters");
        serviceTopicMessageCounterService.resetCounters();
    }

    private boolean isServiceTrafficExceeded(ServiceTopicDefinition service) {
        var counter = serviceTopicMessageCounterService.getCounter(service.serviceName(), service.topic());
        return counter > service.maxRequestsPerMinute();
    }

    private void alertTrafficExceeded(ServiceTopicDefinition serviceDefinition) {
        for (var alert : serviceDefinition.alertDefinitions()) {
            var notification = alert.notificationType().getStrategy();
            var message = getTrafficExceededMessage(serviceDefinition);
            notification.Notify(alert, message);
        }
    }

    private TrafficExceededAlert getTrafficExceededMessage(ServiceTopicDefinition service) {
        return new TrafficExceededAlert(service.serviceName(), service.topic(), TrafficExceededCause.Count);
    }
}
