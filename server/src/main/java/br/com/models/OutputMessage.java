package br.com.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@AllArgsConstructor
public class OutputMessage {

    @Getter @Setter
    private String from;
    @Getter @Setter
    private String message;
    @Getter @Setter
    private String topic;
    @Getter @Setter
    private Date time = new Date();

    public OutputMessage(String from, String message, String topic) {
        this.from = from;
        this.message = message;
        this.topic = topic;
    }
}
