package cloudcomputing2024.smarthouse.trafficmonitorservice.services.impementations;

import cloudcomputing2024.smarthouse.trafficmonitorservice.domin.datamodel.TrafficExceededCause;
import cloudcomputing2024.smarthouse.trafficmonitorservice.domin.entities.ServiceTopicDefinitionEntity;
import cloudcomputing2024.smarthouse.trafficmonitorservice.infrastructure.ServiceTopicDefinitionRepository;
import cloudcomputing2024.smarthouse.trafficmonitorservice.infrastructure.ServiceTopicMessageCounterService;
import cloudcomputing2024.smarthouse.trafficmonitorservice.services.abstractions.IServiceTrafficNotificationService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.Duration;

@Component
public class TrafficMonitorScheduler implements CommandLineRunner {
    private static final Duration TrafficMonitorInterval = Duration.ofMinutes(1);

    private final ServiceTopicMessageCounterService serviceTopicMessageCounterService;
    private final ServiceTopicDefinitionRepository serviceTopicDefinitionRepository;

    private final IServiceTrafficNotificationService notificationService;

    public TrafficMonitorScheduler(ServiceTopicMessageCounterService serviceTopicMessageCounterService, ServiceTopicDefinitionRepository serviceTopicDefinitionRepository, IServiceTrafficNotificationService notificationService) {
        this.serviceTopicMessageCounterService = serviceTopicMessageCounterService;
        this.serviceTopicDefinitionRepository = serviceTopicDefinitionRepository;
        this.notificationService = notificationService;
    }

    @Override
    public void run(String... args) {
        Flux.interval(TrafficMonitorInterval)
                .flatMap(ignore -> monitorTraffic())
                .subscribe();
    }

    private Mono<Void> monitorTraffic() {
        return serviceTopicDefinitionRepository
                .findAll()
                .filterWhen(this::isServiceTrafficExceeded)
                .flatMap(definition -> notificationService.sendTrafficExceededNotifications(definition.serviceName(), TrafficExceededCause.Count))
                .thenMany(serviceTopicMessageCounterService.resetCounters())
                .then();
    }

    private Mono<Boolean> isServiceTrafficExceeded(ServiceTopicDefinitionEntity definition) {
        return serviceTopicMessageCounterService
                .getCounter(definition.serviceName())
                .map(counter -> counter > definition.maxRequestsPerMinute());
    }
}
