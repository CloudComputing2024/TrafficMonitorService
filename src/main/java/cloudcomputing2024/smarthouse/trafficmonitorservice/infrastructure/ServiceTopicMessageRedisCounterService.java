package cloudcomputing2024.smarthouse.trafficmonitorservice.infrastructure;

import org.springframework.data.redis.core.RedisOperations;
import org.springframework.stereotype.Service;

@Service
public class ServiceTopicMessageRedisCounterService implements ServiceTopicMessageCounterService {
    private final RedisOperations<String, Long> cache;

    public ServiceTopicMessageRedisCounterService(RedisOperations<String, Long> cache) {
        this.cache = cache;
    }

    public Long incrementCounter(String key, String topic) {
        return cache.opsForValue().increment(getCounterKey(key, topic));
    }

    @Override
    public Long getCounter(String service, String topic) {
        var counterValue = this.cache.opsForValue().get(getCounterKey(service, topic));
        return counterValue != null && counterValue > 0 ? counterValue : 0;
    }

    @Override
    public void resetCounters() {
        var keys = this.cache.keys("counter:*");


        if (keys != null) {
            this.cache.delete(keys);
        }
    }

    private static String getCounterKey(String serviceName, String topicName) {
        return "counter:" + serviceName + ":" + topicName;
    }
}
