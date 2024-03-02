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
    private static final int TrafficMonitorInterval = 60000; // 1 Minute
    private final Logger logger;
    private final ServiceTopicDefinitionRepository serviceTopicDefinitionRepository;
    private final ServiceTopicMessageCounterService serviceTopicMessageCounterService;

    public TrafficMonitorScheduler(ServiceTopicDefinitionRepository serviceTopicDefinitionRepository, ServiceTopicMessageCounterService serviceTopicMessageCounterService) {
        this.logger = LoggerFactory.getLogger(TrafficMonitorScheduler.class);
        this.serviceTopicDefinitionRepository = serviceTopicDefinitionRepository;
        this.serviceTopicMessageCounterService = serviceTopicMessageCounterService;
    }

    @Scheduled(fixedRate = TrafficMonitorInterval)
    public void monitorTraffic() {
        logger.info("Monitoring Traffic");
        var serviceDefinitions = serviceTopicDefinitionRepository.findAll();

        for (var service : serviceDefinitions) {
            MonitorServiceTraffic(service);
        }

        logger.info("Resetting Service Counters");
        serviceTopicMessageCounterService.resetCounters();
    }

    private void MonitorServiceTraffic(ServiceTopicDefinition service) {
        try {
            if (isServiceTrafficExceeded(service)) {
                logger.warn("Service Traffic Exceeded: " + service.serviceName());
                alertTrafficExceeded(service);
            }
        } catch (Exception e) {
            logger.error("Error Monitoring Service Traffic: " + service.serviceName(), e);
        }
    }

    private boolean isServiceTrafficExceeded(ServiceTopicDefinition service) {
        var counter = serviceTopicMessageCounterService.getCounter(service.serviceName(), service.topic());
        return counter > service.maxRequestsPerMinute();
    }

    private static void alertTrafficExceeded(ServiceTopicDefinition serviceDefinition) {
        for (var alert : serviceDefinition.alertDefinitions()) {
            var notification = alert.notificationType().getStrategy();
            var message = getTrafficExceededMessage(serviceDefinition);
            notification.Notify(alert, message);
        }
    }

    private static TrafficExceededAlert getTrafficExceededMessage(ServiceTopicDefinition service) {
        return new TrafficExceededAlert(service.serviceName(), service.topic(), TrafficExceededCause.Count);
    }
}
