package cloudcomputing2024.smarthouse.trafficmonitorservice.presentation.controllers;

import cloudcomputing2024.smarthouse.trafficmonitorservice.presentation.boundaries.ServiceTopicDefinitionBoundary;
import cloudcomputing2024.smarthouse.trafficmonitorservice.services.impementations.RegistrationService;
import cloudcomputing2024.smarthouse.trafficmonitorservice.presentation.boundaries.MessageBoundary;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping(path = {"/registration"})
public class RegistrationController {
    private RegistrationService registrationService;

    public RegistrationController(RegistrationService registrationService) {
        this.registrationService = registrationService;
    }


    @PostMapping(
            consumes = {MediaType.APPLICATION_JSON_VALUE},
            produces = {MediaType.APPLICATION_JSON_VALUE})
    public Mono<ServiceTopicDefinitionBoundary> registerService (
            @RequestBody MessageBoundary message) {
            return this.registrationService
                    .registerService(message)
                    .log();
    }

    @GetMapping(
            produces = {MediaType.APPLICATION_JSON_VALUE})
    public Flux<ServiceTopicDefinitionBoundary> getAllRegistrations (
            @RequestBody MessageBoundary message){
        return this.registrationService
                .getAllRegistrations(message)
                .log();
    }

    @DeleteMapping
    public Mono<Void> deleteAllRegistrations(){
        return this.registrationService
                .deleteAll()
                .log();
    }

}
