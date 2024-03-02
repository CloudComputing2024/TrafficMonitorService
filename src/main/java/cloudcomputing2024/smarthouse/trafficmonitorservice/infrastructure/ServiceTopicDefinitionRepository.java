package cloudcomputing2024.smarthouse.trafficmonitorservice.infrastructure;

import cloudcomputing2024.smarthouse.trafficmonitorservice.messages.ServiceTopicDefinition;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface ServiceTopicDefinitionRepository extends MongoRepository<ServiceTopicDefinition, String> {
    public List<ServiceTopicDefinition> findByServiceName(String firstName);
    void deleteByServiceNameAndTopic(ServiceTopicDefinition serviceTopicDefinition);
}
