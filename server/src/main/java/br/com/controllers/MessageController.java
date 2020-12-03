package br.com.controllers;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;

import br.com.models.Message;
import br.com.models.OutputMessage;

public class MessageController {

    @MessageMapping("/chat")
    @SendTo("topic/message")
    public OutputMessage send(Message message) {
        String time = new SimpleDateFormat("HH:mm:ss").format(new Date());
        return new OutputMessage(message.getFrom(), message.getText(), time);
    }
}