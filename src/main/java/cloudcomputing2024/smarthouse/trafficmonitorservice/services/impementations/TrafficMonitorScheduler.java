package cloudcomputing2024.smarthouse.trafficmonitorservice.services.impementations;

import cloudcomputing2024.smarthouse.trafficmonitorservice.domin.datamodel.TrafficExceededCause;
import cloudcomputing2024.smarthouse.trafficmonitorservice.domin.entities.ServiceTopicDefinitionEntity;
import cloudcomputing2024.smarthouse.trafficmonitorservice.infrastructure.ServiceTopicDefinitionRepository;
import cloudcomputing2024.smarthouse.trafficmonitorservice.infrastructure.IServiceTopicMessageCounterService;
import cloudcomputing2024.smarthouse.trafficmonitorservice.services.abstractions.IServiceTrafficNotificationService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.Duration;

@Component
public class TrafficMonitorScheduler implements CommandLineRunner {
    private static final Duration TrafficMonitorInterval = Duration.ofSeconds(20);

    private final IServiceTopicMessageCounterService serviceTopicMessageCounterService;
    private final ServiceTopicDefinitionRepository serviceTopicDefinitionRepository;

    private final IServiceTrafficNotificationService notificationService;

    public TrafficMonitorScheduler(IServiceTopicMessageCounterService IServiceTopicMessageCounterService, ServiceTopicDefinitionRepository serviceTopicDefinitionRepository, IServiceTrafficNotificationService notificationService) {
        this.serviceTopicMessageCounterService = IServiceTopicMessageCounterService;
        this.serviceTopicDefinitionRepository = serviceTopicDefinitionRepository;
        this.notificationService = notificationService;
    }

    @Override
    public void run(String... args) {
        Flux.interval(TrafficMonitorInterval)
                .flatMap(interval -> monitorTraffic())
                .thenMany(serviceTopicMessageCounterService.resetCounters())
                .then()
                .subscribe();
    }

    private Flux<Void> monitorTraffic() {
        return serviceTopicDefinitionRepository
                .findAll()
                .filterWhen(this::isServiceTrafficExceeded)
                .flatMap(definition -> notificationService.sendTrafficExceededNotifications(definition, TrafficExceededCause.Count));
    }

    private Mono<Boolean> isServiceTrafficExceeded(ServiceTopicDefinitionEntity definition) {
        return serviceTopicMessageCounterService
                .getCounter(definition.serviceName())
                .map(counter -> counter > definition.maxRequestsPerMinute());
    }
}
