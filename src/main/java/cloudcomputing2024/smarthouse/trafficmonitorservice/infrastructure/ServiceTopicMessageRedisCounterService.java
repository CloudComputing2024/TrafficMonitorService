package cloudcomputing2024.smarthouse.trafficmonitorservice.infrastructure;

import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

@Service
public class ServiceTopicMessageRedisCounterService implements ServiceTopicMessageCounterService {
    private static final String CounterKeyPrefix = "counter:";
    private static final String CounterKeysPattern = CounterKeyPrefix + "*";
    private static final String CounterKeyFormat = CounterKeyPrefix + "%s:%s";

    private final RedisTemplate<String, String> cache;

    public ServiceTopicMessageRedisCounterService(RedisTemplate<String, String> cache) {
        this.cache = cache;
    }

    public Long incrementCounter(String key, String topic) {
        return cache.opsForValue().increment(getCounterKey(key, topic));
    }

    @Override
    public Long getCounter(String service, String topic) {
        var counterValue = this.cache.opsForValue().get(getCounterKey(service, topic));
        return counterValue != null ? Long.parseLong(counterValue) : 0;
    }

    @Override
    public void resetCounters() {
        var keys = this.cache.keys(CounterKeysPattern);

        if (keys != null) {
            this.cache.delete(keys);
        }
    }

    private static String getCounterKey(String serviceName, String topicName) {
        return String.format(CounterKeyFormat, serviceName, topicName);
    }
}
