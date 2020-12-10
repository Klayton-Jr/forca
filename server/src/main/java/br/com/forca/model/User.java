package br.com.forca.model;

import lombok.Getter;
import lombok.Setter;

public class User {

    @Getter @Setter
    private String nick;
    @Getter @Setter
    private String id;
    @Getter @Setter
    private boolean allowSendMessage;

    @Getter @Setter
    private UserType userType;
    @Getter @Setter
    private Integer count;


    public enum UserType {
        ROOM_MASTER, PLAYER;
    }
}
