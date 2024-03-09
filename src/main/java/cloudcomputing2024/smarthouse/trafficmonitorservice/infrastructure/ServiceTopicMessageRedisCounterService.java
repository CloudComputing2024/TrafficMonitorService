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
    private static final String CounterKeyFormat = CounterKeyPrefix + "%s:%s";

    private final ReactiveStringRedisTemplate cache;

    public ServiceTopicMessageRedisCounterService(ReactiveStringRedisTemplate cache) {
        this.cache = cache;
    }

    public Mono<Long> incrementCounter(String key, String topic) {
        return cache.opsForValue().increment(getCounterKey(key, topic));
    }

    @Override
    public Mono<Long> getCounter(String service, String topic) {
        logger.info("Getting counter for service '{}' and topic '{}'", service, topic);

        return this.cache
                .opsForValue()
                .get(getCounterKey(service, topic))
                .map(counter -> counter != null ? Long.parseLong(counter) : 0);
    }

    @Override
    public Flux<Long> resetCounters() {
        logger.info("Resetting all counters");
        return this.cache.keys(CounterKeysPattern).flatMap(this.cache::delete);
    }

    private static String getCounterKey(String serviceName, String topicName) {
        return String.format(CounterKeyFormat, serviceName, topicName);
    }
}
