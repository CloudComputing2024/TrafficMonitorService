package cloudcomputing2024.smarthouse.trafficmonitorservice.services.impementations;

import cloudcomputing2024.smarthouse.trafficmonitorservice.presentation.boundaries.MessageBoundary;
import cloudcomputing2024.smarthouse.trafficmonitorservice.services.abstractions.IMessageSizeValidator;
import org.springframework.stereotype.Component;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

@Component
public class MessageSizeValidator implements IMessageSizeValidator {
    @Override
    public boolean IsSizeValid(MessageBoundary messageBoundary) {
        return validateSize(calculateSize(messageBoundary));
    }

    private final int MAX_SIZE_MESSAGES = 100;

    public int calculateSize(MessageBoundary message) {
        try {
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(byteArrayOutputStream);
            objectOutputStream.writeObject(message);
            objectOutputStream.flush();

            byte[] bytes = byteArrayOutputStream.toByteArray(); // Get the byte array and calculate its length
            return bytes.length;
        } catch (IOException e) {
            e.printStackTrace();
            return -1;
        }
    }
    public boolean validateSize(int currentSize) {
        return currentSize < MAX_SIZE_MESSAGES;
    }
}
