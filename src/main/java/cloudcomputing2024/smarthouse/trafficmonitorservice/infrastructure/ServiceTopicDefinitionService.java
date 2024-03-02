package cloudcomputing2024.smarthouse.trafficmonitorservice.infrastructure;

import cloudcomputing2024.smarthouse.trafficmonitorservice.messages.ServiceTopicDefinition;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ServiceTopicDefinitionService  {
    private ServiceTopicDefinitionRepository serviceTopicDefinitionRepository;

    public ServiceTopicDefinitionService(ServiceTopicDefinitionRepository serviceTopicDefinitionRepository) {
        this.serviceTopicDefinitionRepository = serviceTopicDefinitionRepository;
    }
    @CachePut(value = "topicDefinitions", key = "#serviceTopicDefinition.serviceName" + ":" +"serviceTopicDefinition.topic")
    public ServiceTopicDefinition AddServiceTopicDefinition(ServiceTopicDefinition serviceTopicDefinition){
        return serviceTopicDefinitionRepository.save(serviceTopicDefinition);
    }

    @CacheEvict(cacheNames = "topicDefinitions", key = "#serviceTopicDefinition.serviceName" + ":" +"serviceTopicDefinition.topic" )
    public void DeleteServiceTopicDefinition(ServiceTopicDefinition serviceTopicDefinition){
        serviceTopicDefinitionRepository.delete(serviceTopicDefinition);
    }
    @CacheEvict(cacheNames = "topicDefinitions", key = "#serviceTopicDefinition.serviceName" + ":" +"serviceTopicDefinition.topic" )
    public void DeleteServiceTopicDefinitionByServiceNameAndTopic(ServiceTopicDefinition serviceTopicDefinition){
        serviceTopicDefinitionRepository.deleteByServiceNameAndTopic(serviceTopicDefinition);
    }

    @Cacheable(cacheNames = "topicDefinitions", key = "#serviceName")
    List<ServiceTopicDefinition> GetAllTopicDefinitionForService(String serviceName){
        return serviceTopicDefinitionRepository.findByServiceName(serviceName);
    }
}
