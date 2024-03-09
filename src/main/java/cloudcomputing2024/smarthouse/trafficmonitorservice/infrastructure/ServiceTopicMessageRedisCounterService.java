package cloudcomputing2024.smarthouse.trafficmonitorservice.infrastructure;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.redis.core.ReactiveStringRedisTemplate;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class ServiceTopicMessageRedisCounterService implements ServiceTopicMessageCounterService {
    private final Logger logger = LoggerFactory.getLogger(ServiceTopicMessageRedisCounterService.class);

    private static final String CounterKeyPrefix = "counter:";
    private static final String CounterKeysPattern = CounterKeyPrefix + "*";
    private static final String CounterKeyFormat = CounterKeyPrefix + "%s";

    private final ReactiveStringRedisTemplate cache;

    public ServiceTopicMessageRedisCounterService(ReactiveStringRedisTemplate cache) {
        this.cache = cache;
    }

    public Mono<Long> incrementCounter(String serviceName) {
        logger.info("Incrementing counter for service '{}'", serviceName);

        return cache
                .opsForValue()
                .increment(getCounterKey(serviceName));
    }

    @Override
    public Mono<Long> getCounter(String serviceName) {
        logger.info("Getting counter for service '{}'", serviceName);

        return this.cache
                .opsForValue()
                .get(getCounterKey(serviceName))
                .map(counter -> counter != null ? Long.parseLong(counter) : 0);
    }

    @Override
    public Flux<Long> resetCounters() {
        logger.info("Resetting all counters");

        return this.cache
                .keys(CounterKeysPattern)
                .flatMap(this.cache::delete);
    }

    private static String getCounterKey(String serviceName) {
        return String.format(CounterKeyFormat, serviceName);
    }
}
