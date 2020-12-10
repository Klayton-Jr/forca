package br.com.forca.model.message;

import br.com.forca.model.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
public class ChatRoom {

    @Getter @Setter
    private Set<User> users;
    @Getter @Setter
    private String wordToGuess;
    @Getter @Setter
    private String id;

    public ChatRoom(String id) {
        this.id = id;
        this.users = new HashSet<>();
    }
}
