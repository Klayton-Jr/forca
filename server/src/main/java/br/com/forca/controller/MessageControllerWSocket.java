package br.com.forca.controller;

import br.com.forca.data.RoomStorage;
import br.com.forca.exceptions.InvalidUserToSetTheWord;
import br.com.forca.exceptions.SizeRoomException;
import br.com.forca.model.*;
import br.com.forca.model.message.ChatMessage;
import br.com.forca.model.message.ChatRoom;
import br.com.forca.model.request.ChooseWordRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.*;
import java.util.stream.Collectors;

@Controller
public class MessageControllerWSocket {

    //@Autowired
    //private SimpMessagingTemplate simpMessagingTemplate;

    //register global chat
    @MessageMapping("/chat.newUser")
    @SendTo("/topic/public")
    public ChatMessage register(@Payload ChatMessage chatMessage, SimpMessageHeaderAccessor headerAccessor) {
        RoomManagment roomManagment = new RoomManagment(RoomStorage.getInstance().getChatRooms());

        User user = new User();
        user.setNick(chatMessage.getSender());

        try {
            roomManagment.addUser(user, "1");
        } catch (SizeRoomException e) {
            e.printStackTrace();
        }

        headerAccessor.getSessionAttributes().put("username", chatMessage.getSender());
        return chatMessage;
    }

    //global chat
    //fazer testes aqui para ver se é possível tratar as msgs enviadas e talvez disponibilizalas
    @MessageMapping("/chat.send")
    @SendTo("/topic/public")
    public ChatMessage sendMessage(@Payload ChatMessage chatMessage) {
        return chatMessage;
    }

//    @MessageMapping("/chat.send")
//    @SendTo("/topic/")
    public ChatMessage sendMessageToEspecifiedUser(@Payload ChatMessage chatMessage) {
        return chatMessage;
    }

    //lista de salas
    @MessageMapping("chat/list-rooms")
    @SendTo("/topic/rooms")
    public List<String> listChatRoomsId(@Payload User user) {
        return RoomStorage.getInstance().getChatRooms().stream().map(ChatRoom::getId).collect(Collectors.toList());
    }

    @MessageMapping("/user-all")
    @SendTo("/topic/user")
    public HelloMessage send(@Payload HelloMessage message) {
        return message;
    }

}
