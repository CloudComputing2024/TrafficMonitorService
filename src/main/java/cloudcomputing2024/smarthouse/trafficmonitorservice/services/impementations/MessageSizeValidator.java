package cloudcomputing2024.smarthouse.trafficmonitorservice.services.impementations;

import cloudcomputing2024.smarthouse.trafficmonitorservice.domin.entities.ServiceTopicDefinitionEntity;
import cloudcomputing2024.smarthouse.trafficmonitorservice.presentation.boundaries.MessageBoundary;
import cloudcomputing2024.smarthouse.trafficmonitorservice.services.abstractions.IMessageSizeValidator;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class MessageSizeValidator implements IMessageSizeValidator {
    private final Log logger = LogFactory.getLog(MessageSizeValidator.class);
    private final ObjectMapper objectMapper;

    public MessageSizeValidator(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    @Override
    public boolean isSizeValid(MessageBoundary messageBoundary, ServiceTopicDefinitionEntity serviceTopicDefinition) {
        var messageSize = calculateSize(messageBoundary);
        return messageSize <= serviceTopicDefinition.maxRequestSizeIntBytes();
    }

    public int calculateSize(MessageBoundary message) {
        try {
            return objectMapper.writeValueAsBytes(message).length;
        } catch (IOException e) {
            logger.error("Error while calculating message size", e);
            return -1;
        }
    }
}
