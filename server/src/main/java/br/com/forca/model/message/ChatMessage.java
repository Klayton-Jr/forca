package br.com.forca.model.message;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
public class ChatMessage {

    @Getter @Setter
    private String content;

    @Getter @Setter
    private String sender;

    @Getter @Setter
    private MessageType type;

    @Getter @Setter
    private String time;

    public enum MessageType {
        CHAT, CONNECT, DISCONNECT
    }



}