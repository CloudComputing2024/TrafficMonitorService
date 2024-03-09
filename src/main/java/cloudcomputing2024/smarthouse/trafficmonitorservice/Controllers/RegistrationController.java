package cloudcomputing2024.smarthouse.trafficmonitorservice.Controllers;

import cloudcomputing2024.smarthouse.trafficmonitorservice.infrastructure.RegistrationService;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping(path = {"/register"})
public class RegistrationController {
    private RegistrationService registrationService;

    public RegistrationController(RegistrationService registrationService) {
        this.registrationService = registrationService;
    }


    @PostMapping(
            consumes = {MediaType.APPLICATION_JSON_VALUE},
            produces = {MediaType.APPLICATION_JSON_VALUE})
    public Mono<MessageBoundary> registerService (
            @RequestBody MessageBoundary message) {
        return this.messageService
                .createMessage(message)
                .log();
    }

    @GetMapping(
            produces = {MediaType.TEXT_EVENT_STREAM_VALUE})
    public Mono<MessageBoundary> checkIfRegistered (){
        return this.messageService
                .getAll()
                .log();
    }


}
