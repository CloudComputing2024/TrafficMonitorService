package cloudcomputing2024.smarthouse.trafficmonitorservice.infrastructure;

import cloudcomputing2024.smarthouse.trafficmonitorservice.messages.ServiceTopicDefinition;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class ServiceTopicDefinitionService {
    private final ServiceTopicDefinitionRepository serviceTopicDefinitionRepository;

    public ServiceTopicDefinitionService(ServiceTopicDefinitionRepository serviceTopicDefinitionRepository) {
        this.serviceTopicDefinitionRepository = serviceTopicDefinitionRepository;
    }

    @CachePut(value = "topicDefinitions", key = "#serviceName")
    public Mono<ServiceTopicDefinition> AddServiceTopicDefinition(ServiceTopicDefinition serviceTopicDefinition) {
        return serviceTopicDefinitionRepository.save(serviceTopicDefinition);
    }

    @CacheEvict(cacheNames = "topicDefinitions", key = "#serviceName")
    public Mono<Void> DeleteServiceTopicDefinition(ServiceTopicDefinition serviceTopicDefinition) {
        return serviceTopicDefinitionRepository.delete(serviceTopicDefinition);
    }

    @CacheEvict(cacheNames = "topicDefinitions", key = "#serviceName")
    public Mono<Void> DeleteServiceTopicDefinitionByServiceNameAndTopic(ServiceTopicDefinition serviceTopicDefinition) {
        return serviceTopicDefinitionRepository.deleteByServiceNameAndTopic(serviceTopicDefinition.serviceName(), serviceTopicDefinition.topic());
    }

    @Cacheable(cacheNames = "topicDefinitions", key = "#serviceName")
    public Flux<ServiceTopicDefinition> GetAllTopicDefinitionForService(String serviceName) {
        return serviceTopicDefinitionRepository.findByServiceName(serviceName);
    }
}
