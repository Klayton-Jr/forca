package br.com.forca.data;

import br.com.forca.model.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;

@AllArgsConstructor
@NoArgsConstructor
public class ChatRoom {

    @Getter @Setter
    private ArrayList<User> users;
    @Getter @Setter
    private String wordToGuess;
    @Getter @Setter
    private String id;

}
