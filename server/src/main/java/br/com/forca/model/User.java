package br.com.forca.model;

import lombok.Getter;
import lombok.Setter;

public class User {

    @Getter @Setter
    private String nick;
    @Getter @Setter
    private String id;
    @Getter @Setter
    private UserType userType;

    public enum UserType {
        ROOM_MASTER, PLAYER;
    }
}
