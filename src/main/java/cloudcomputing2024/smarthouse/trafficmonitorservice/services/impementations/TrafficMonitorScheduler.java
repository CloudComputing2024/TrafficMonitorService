package cloudcomputing2024.smarthouse.trafficmonitorservice.services.impementations;

import cloudcomputing2024.smarthouse.trafficmonitorservice.infrastructure.ServiceTopicDefinitionRepository;
import cloudcomputing2024.smarthouse.trafficmonitorservice.infrastructure.ServiceTopicMessageCounterService;
import cloudcomputing2024.smarthouse.trafficmonitorservice.presentation.boundaries.ServiceTopicDefinitionBoundary;
import cloudcomputing2024.smarthouse.trafficmonitorservice.domin.datamodel.TrafficExceededCause;
import cloudcomputing2024.smarthouse.trafficmonitorservice.services.abstractions.IServiceTrafficNotificationService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service
public class TrafficMonitorScheduler {
    private static final int TrafficMonitorIntervalMilliseconds = 60000; // 1 Minute
    private final Logger logger = LoggerFactory.getLogger(TrafficMonitorScheduler.class);
    private final ServiceTopicMessageCounterService serviceTopicMessageCounterService;
    private final ServiceTopicDefinitionRepository serviceTopicDefinitionRepository;

    private final IServiceTrafficNotificationService notificationService;

    public TrafficMonitorScheduler(ServiceTopicMessageCounterService serviceTopicMessageCounterService, ServiceTopicDefinitionRepository serviceTopicDefinitionRepository, IServiceTrafficNotificationService notificationService) {
        this.serviceTopicMessageCounterService = serviceTopicMessageCounterService;
        this.serviceTopicDefinitionRepository = serviceTopicDefinitionRepository;
        this.notificationService = notificationService;
    }

    @Scheduled(fixedRate = TrafficMonitorIntervalMilliseconds)
    public void monitorTraffic() {
        logger.info("Monitoring service messages traffic");
        var serviceDefinitions = serviceTopicDefinitionRepository.findAll();

        for (var service : serviceDefinitions) {
            monitorServiceTraffic(service);
        }

        logger.info("Resetting service message counters");
        serviceTopicMessageCounterService.resetCounters();
    }

    private void monitorServiceTraffic(ServiceTopicDefinitionBoundary definition) {
//        try {
//            if (isServiceTrafficExceeded(definition)) {
//                notificationService.sendTrafficExceededNotifications(definition, TrafficExceededCause.Count);
//            }
//        } catch (Exception e) {
//            logger.error("Failed to monitor traffic for topic '" + definition.topic() + "' in service '" + definition.serviceName() + "'", e);
//        }
    }

    private boolean isServiceTrafficExceeded(ServiceTopicDefinitionBoundary definition) {
//        var counter = serviceTopicMessageCounterService.getCounter(definition.serviceName(), definition.topic());
        var counter = 1; //TODO fix this
        return counter > definition.maxRequestsPerMinute();
    }
}
