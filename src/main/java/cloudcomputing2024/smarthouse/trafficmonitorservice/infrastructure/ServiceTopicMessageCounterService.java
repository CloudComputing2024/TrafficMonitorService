package cloudcomputing2024.smarthouse.trafficmonitorservice.infrastructure;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface ServiceTopicMessageCounterService {
    Mono<Long> incrementCounter(String serviceName);

    Mono<Long> getCounter(String serviceName);

    Flux<Long> resetCounters();
}
