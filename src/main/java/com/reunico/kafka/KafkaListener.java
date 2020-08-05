package com.reunico.kafka;

import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.cloud.stream.messaging.Sink;
import org.springframework.stereotype.Service;

@Service
@EnableBinding(Sink.class)
public class KafkaListener {

    @StreamListener(target = Sink.INPUT,
        condition = "(headers['type']?:'')=='test'")
    public void getMessage(Message<String> message) {

    }
}
