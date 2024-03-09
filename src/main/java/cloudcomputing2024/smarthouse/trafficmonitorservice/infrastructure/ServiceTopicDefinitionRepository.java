package cloudcomputing2024.smarthouse.trafficmonitorservice.infrastructure;

import cloudcomputing2024.smarthouse.trafficmonitorservice.messages.ServiceTopicDefinition;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

//Make reactive
@Repository
public interface ServiceTopicDefinitionRepository extends MongoRepository<ServiceTopicDefinition, String> {
    List<ServiceTopicDefinition> findByServiceName(String firstName);

    void deleteByServiceNameAndTopic(ServiceTopicDefinition serviceTopicDefinition);
}

