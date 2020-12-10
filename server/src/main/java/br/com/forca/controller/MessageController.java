package br.com.forca.controller;

import br.com.forca.data.RoomStorage;
import br.com.forca.exceptions.SizeRoomException;
import br.com.forca.model.ChatRoom;
import br.com.forca.model.ChatMessage;
import br.com.forca.model.HelloMessage;
import br.com.forca.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.*;
import java.util.concurrent.ExecutionException;
import java.util.stream.Collectors;

@Controller
public class MessageController {

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

    //lista de salas
    @MessageMapping("chat/list-rooms")
    @SendTo("/topic/rooms")
    public List<String> listChatRoomsId(@Payload User user) {
        return RoomStorage.getInstance().getChatRooms().stream().map(ChatRoom::getId).collect(Collectors.toList());
    }

    /**
     *
     * @return IdRoom, quantity of players
     */
    @GetMapping("/chat/list-rooms")
    public ResponseEntity<Map<String, Integer>> returnAvailableRooms() {
        return ResponseEntity.ok(RoomStorage.getInstance().getChatRooms().stream().collect(Collectors.toMap(ChatRoom::getId, p -> p.getUsers().size())));
    }

    @PostMapping("/chat/game-room{idRoom}")
    public void choosingTheWord(@PathVariable String idRoom, String word, User user) {
        RoomManagment roomManagment = new RoomManagment(RoomStorage.getInstance().getChatRooms());
        try {
            roomManagment.chooseTheWord(idRoom, word, user);
        } catch (Exception e) {
            ResponseEntity.status(HttpStatus.FORBIDDEN).body(e.getMessage());
        }
    }

    @MessageMapping("/user-all")
    @SendTo("/topic/user")
    public HelloMessage send(@Payload HelloMessage message) {
        return message;
    }

}
