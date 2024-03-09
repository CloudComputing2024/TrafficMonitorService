package cloudcomputing2024.smarthouse.trafficmonitorservice.services.impementations;

import cloudcomputing2024.smarthouse.trafficmonitorservice.infrastructure.ServiceTopicDefinitionRepository;
import cloudcomputing2024.smarthouse.trafficmonitorservice.infrastructure.ServiceTopicMessageCounterService;
import cloudcomputing2024.smarthouse.trafficmonitorservice.presentation.boundaries.ServiceTopicDefinitionBoundary;
import cloudcomputing2024.smarthouse.trafficmonitorservice.services.abstractions.IServiceTrafficNotificationService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

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

    @Scheduled(fixedDelay = TrafficMonitorIntervalMilliseconds)
    public void monitorTraffic() {
        logger.info("Monitoring service messages traffic");

//        serviceTopicDefinitionRepository
//                .findAll()
//                .filterWhen(this::isServiceTrafficExceeded)
//                .map(definition -> notificationService.sendTrafficExceededNotifications(definition, TrafficExceededCause.Count))
//                .thenMany(serviceTopicMessageCounterService.resetCounters())
//                .then()
//                .block();
    }

    private Mono<Boolean> isServiceTrafficExceeded(ServiceTopicDefinitionBoundary definition) {
        return Mono.just(true);
//        return serviceTopicMessageCounterService
//                .getCounter(definition.serviceName(), definition.topic())
//                .map(counter -> counter > definition.maxRequestsPerMinute());
    }
}
