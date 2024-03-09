package cloudcomputing2024.smarthouse.trafficmonitorservice.infrastructure;

import cloudcomputing2024.smarthouse.trafficmonitorservice.domin.entities.ServiceTopicDefinitionEntity;
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
    public Mono<ServiceTopicDefinitionEntity> AddServiceTopicDefinition(ServiceTopicDefinitionEntity serviceTopicDefinition) {
        return serviceTopicDefinitionRepository.save(serviceTopicDefinition);
    }

    @CacheEvict(cacheNames = "topicDefinitions", key = "#serviceName")
    public Mono<Void> DeleteServiceTopicDefinition(ServiceTopicDefinitionEntity serviceTopicDefinition) {
        return serviceTopicDefinitionRepository.delete(serviceTopicDefinition);
    }

    @Cacheable(cacheNames = "topicDefinitions", key = "#serviceName")
    public Flux<ServiceTopicDefinitionEntity> GetAllTopicDefinitionForService(String serviceName) {
        return serviceTopicDefinitionRepository.findByServiceName(serviceName);
    }
}
