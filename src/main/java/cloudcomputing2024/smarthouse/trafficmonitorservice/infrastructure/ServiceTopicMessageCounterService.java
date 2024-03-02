package cloudcomputing2024.smarthouse.trafficmonitorservice.infrastructure;

public interface ServiceTopicMessageCounterService {
    Long incrementCounter(String service, String topic);

    Long getCounter(String serviceName, String topicName);

    void resetCounters();
}
