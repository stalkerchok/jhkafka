package com.reunico.kafka;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.messaging.Source;
import org.springframework.integration.support.MessageBuilder;
import org.springframework.messaging.MessageChannel;
import org.springframework.stereotype.Service;

@Service
@EnableBinding(Source.class)
public class KafkaSender {

    private final MessageChannel output;
    private final ObjectMapper objectMapper;

    public KafkaSender(MessageChannel output, ObjectMapper objectMapper) {
        this.output = output;
        this.objectMapper = objectMapper;
    }

    public void send(Message<?> message) {
        try {
            String jsonMessage = objectMapper.writeValueAsString(message);
            // wrap into a proper message for the transport (Kafka) and send it
            output.send(
                MessageBuilder.withPayload(jsonMessage).setHeader("type", message.getType()).build());
        } catch (Exception e) {
            throw new RuntimeException("Could not transform and send message due to: " + e.getMessage(), e);
        }
    }

}
