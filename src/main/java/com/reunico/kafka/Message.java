package com.reunico.kafka;

import lombok.Data;

import java.util.Date;
import java.util.UUID;

@Data
public class Message<T> {

    // Cloud Events compliant
    private String type;
    private String id = UUID.randomUUID().toString(); // unique id of this message
    private Date time = new Date();
    private T data;
    private String dataContentType = "application/json";

    public Message() {
    }

    public Message(String type, T payload) {
        this.type = type;
        this.data = payload;
    }

}
