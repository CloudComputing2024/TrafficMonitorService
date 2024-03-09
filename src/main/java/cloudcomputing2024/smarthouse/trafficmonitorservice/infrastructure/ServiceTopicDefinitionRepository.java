package cloudcomputing2024.smarthouse.trafficmonitorservice.infrastructure;

import cloudcomputing2024.smarthouse.trafficmonitorservice.presentation.boundaries.ServiceTopicDefinitionBoundary;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

//Make reactive
@Repository
public interface ServiceTopicDefinitionRepository extends MongoRepository<ServiceTopicDefinitionBoundary, String> {
    List<ServiceTopicDefinitionBoundary> findByServiceName(String firstName);

    void deleteByServiceNameAndTopic(ServiceTopicDefinitionBoundary serviceTopicDefinitionBoundary);
}

