package br.com.controllers;

import java.text.SimpleDateFormat;
import java.util.Date;

import br.com.models.ChatMessage;
import br.com.models.Greeting;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;

import br.com.models.Message;
import br.com.models.OutputMessage;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.stereotype.Controller;
import org.springframework.web.util.HtmlUtils;

@Controller
public class MessageController {

//    @MessageMapping("/chat")
//    @SendTo("/topic/message")
//    public OutputMessage send(Message message) {
//        String time = new SimpleDateFormat("HH:mm:ss").format(new Date());
//        return new OutputMessage(message.getFrom(), message.getText(), time);
//    }

    @MessageMapping("/chat/{topic}")
    @SendTo("/topic/messages")
    public OutputMessage send(
            @DestinationVariable("topic") String topic, Message message)
            throws Exception {
        return new OutputMessage(message.getFrom(), message.getText(), topic);
    }

    @MessageMapping("/chat.register")
    @SendTo("/topic/public")
    public ChatMessage register(@Payload ChatMessage chatMessage, SimpMessageHeaderAccessor headerAccessor) {
        headerAccessor.getSessionAttributes().put("username", chatMessage.getSender());
        return chatMessage;
    }

    @MessageMapping("/chat.send")
    @SendTo("/topic/public")
    public ChatMessage sendMessage(@Payload ChatMessage chatMessage) {
        return chatMessage;
    }

 //   @MessageMapping("/hello")
 //   @SendTo("/topic/greetings")
 //   public Greeting greeting(Message message) throws Exception {
 //       Thread.sleep(1000); // simulated delay
 //       return new Greeting("Hello, " + HtmlUtils.htmlEscape(message.getName()) + "!");
 //   }
}