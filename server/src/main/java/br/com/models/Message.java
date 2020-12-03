package br.com.models;

import lombok.Getter;
import lombok.Setter;

public class Message {
    
    @Getter @Setter
    private String from;
    @Getter @Setter
    private String text;
}