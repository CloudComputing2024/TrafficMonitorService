package cloudcomputing2024.smarthouse.trafficmonitorservice.presentation.controllers;

import cloudcomputing2024.smarthouse.trafficmonitorservice.domin.datamodel.NotificationType;
import cloudcomputing2024.smarthouse.trafficmonitorservice.presentation.boundaries.AlertDefinitionBoundary;
import cloudcomputing2024.smarthouse.trafficmonitorservice.presentation.boundaries.ServiceTopicDefinitionBoundary;
import cloudcomputing2024.smarthouse.trafficmonitorservice.services.impementations.RegistrationService;
import cloudcomputing2024.smarthouse.trafficmonitorservice.presentation.boundaries.MessageBoundary;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(path = {"/registration"})
public class RegistrationController {
    private final RegistrationService registrationService;

    public RegistrationController(RegistrationService registrationService) {
        this.registrationService = registrationService;
    }


    @PostMapping(
            consumes = {MediaType.APPLICATION_JSON_VALUE},
            produces = {MediaType.APPLICATION_JSON_VALUE})
    public Flux<ServiceTopicDefinitionBoundary> registerService(
            @RequestBody MessageBoundary message) {
        return this.registrationService
                .registerServices(message, retrieveAlertDefinitions(message))
                .log();
    }

    @GetMapping(
            produces = {MediaType.APPLICATION_JSON_VALUE})
    public Flux<ServiceTopicDefinitionBoundary> getAllRegistrations(
            @RequestBody MessageBoundary message) {
        return this.registrationService
                .getAllRegistrations(message)
                .log();
    }

    @DeleteMapping
    public Mono<Void> deleteAllRegistrations() {
        return this.registrationService
                .deleteAll()
                .log();
    }

    private List<AlertDefinitionBoundary> retrieveAlertDefinitions(MessageBoundary messageBoundary) {
        List<Map<String, Object>> alertDefinitions =
                (List<Map<String, Object>>) messageBoundary.getMessageDetails().get("alertDefinitions");

        List<AlertDefinitionBoundary> alertDefinitionBoundary = new ArrayList<>();
        for (Map<String, Object> alertDefinition : alertDefinitions) {
            var notificationType = alertDefinition.get("notificationType").toString();
            var parameters = (Map<String, Object>) alertDefinition.get("details");

            AlertDefinitionBoundary boundary = new AlertDefinitionBoundary(NotificationType.valueOf(notificationType), parameters);
            alertDefinitionBoundary.add(boundary);
        }
        return alertDefinitionBoundary;
    }

}
