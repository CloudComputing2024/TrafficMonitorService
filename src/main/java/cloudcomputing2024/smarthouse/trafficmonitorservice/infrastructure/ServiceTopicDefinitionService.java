package cloudcomputing2024.smarthouse.trafficmonitorservice.infrastructure;

import cloudcomputing2024.smarthouse.trafficmonitorservice.presentation.boundaries.ServiceTopicDefinitionBoundary;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ServiceTopicDefinitionService  {
    private final ServiceTopicDefinitionRepository serviceTopicDefinitionRepository;

    public ServiceTopicDefinitionService(ServiceTopicDefinitionRepository serviceTopicDefinitionRepository) {
        this.serviceTopicDefinitionRepository = serviceTopicDefinitionRepository;
    }
    @CachePut(value = "topicDefinitions", key = "#serviceTopicDefinition.serviceName" + ":" +"serviceTopicDefinition.topic")
    public ServiceTopicDefinitionBoundary AddServiceTopicDefinition(ServiceTopicDefinitionBoundary serviceTopicDefinitionBoundary){
        return serviceTopicDefinitionRepository.save(serviceTopicDefinitionBoundary);
    }

    @CacheEvict(cacheNames = "topicDefinitions", key = "#serviceTopicDefinition.serviceName" + ":" +"serviceTopicDefinition.topic" )
    public void DeleteServiceTopicDefinition(ServiceTopicDefinitionBoundary serviceTopicDefinitionBoundary){
        serviceTopicDefinitionRepository.delete(serviceTopicDefinitionBoundary);
    }
    @CacheEvict(cacheNames = "topicDefinitions", key = "#serviceTopicDefinition.serviceName" + ":" +"serviceTopicDefinition.topic" )
    public void DeleteServiceTopicDefinitionByServiceNameAndTopic(ServiceTopicDefinitionBoundary serviceTopicDefinitionBoundary){
        serviceTopicDefinitionRepository.deleteByServiceNameAndTopic(serviceTopicDefinitionBoundary);
    }

    @Cacheable(cacheNames = "topicDefinitions", key = "#serviceName")
    public List<ServiceTopicDefinitionBoundary> GetAllTopicDefinitionForService(String serviceName){
        return serviceTopicDefinitionRepository.findByServiceName(serviceName);
    }
}
