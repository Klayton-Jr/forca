package br.com.forca.data;

import br.com.forca.model.ChatRoom;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

public class RoomStorage {

    private static RoomStorage instance;
    @Getter @Setter
    private List<ChatRoom> chatRooms;

    private RoomStorage() {
        chatRooms = new ArrayList<>();
    }

    public static synchronized RoomStorage getInstance() {
        if (instance == null) {
            instance = new RoomStorage();
        }
        return instance;
    }
}
