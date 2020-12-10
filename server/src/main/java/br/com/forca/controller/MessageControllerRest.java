package br.com.forca.controller;

import br.com.forca.data.RoomStorage;
import br.com.forca.exceptions.InvalidUserToSetTheWord;
import br.com.forca.model.message.ChatRoom;
import br.com.forca.model.request.ChooseWordRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.Map;
import java.util.stream.Collectors;

@Controller
public class MessageControllerRest {

    /**
     *
     * @return IdRoom, quantity of players
     */
    @GetMapping("/chat/list-rooms")
    public ResponseEntity<Map<String, Integer>> returnAvailableRooms() {
        return ResponseEntity.ok(RoomStorage.getInstance().getChatRooms().stream().collect(Collectors.toMap(ChatRoom::getId, p -> p.getUsers().size())));
    }

    @PostMapping(path = "/chat/game-room/{idRoom}", consumes = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<?> choosingTheWord(@PathVariable String idRoom, @RequestBody ChooseWordRequest chooseWordRequest) {
        RoomManagment roomManagment = new RoomManagment(RoomStorage.getInstance().getChatRooms());
        try {
            roomManagment.chooseTheWord(idRoom, chooseWordRequest.getWord(), chooseWordRequest.getUser());
        } catch (InvalidUserToSetTheWord e) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
        return (ResponseEntity<?>) ResponseEntity.ok();
    }
}
