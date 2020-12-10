package br.com.forca.controller;

import br.com.forca.data.RoomStorage;
import br.com.forca.exceptions.InvalidUserToSetTheWord;
import br.com.forca.model.ChatRoom;
import br.com.forca.exceptions.SizeRoomException;
import br.com.forca.model.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
public class RoomManagment {

    @Getter @Setter
    private List<ChatRoom> chatRooms;

    public void addUser(User user, String idRoom) throws SizeRoomException {
        ChatRoom chatRoom;
        boolean chatRoomIdExists;

        if (this.chatRooms.stream().anyMatch(chatRoom1 -> idRoom.equals(chatRoom1.getId()))) {
            chatRoom = this.chatRooms.stream().filter(chatRoom1 -> idRoom.equals(chatRoom1.getId())).collect(Collectors.toList()).get(0);
            chatRoomIdExists = true;
        }
        else {
            chatRoom = new ChatRoom(idRoom);
            chatRoomIdExists = false;
        }

        if (chatRoom.getUsers().isEmpty())
            user.setUserType(User.UserType.ROOM_MASTER);
        else if (chatRoom.getUsers().size() > 4)
            throw new SizeRoomException("Foi excedido o tamanho máximo de jogadores na sala");
        else
            user.setUserType(User.UserType.PLAYER);

        chatRoom.getUsers().add(user);

        if (!chatRoomIdExists)
            RoomStorage.getInstance().getChatRooms().add(chatRoom);
    }

    public ChatRoom getchatRoom(String idRoom) {
        for (ChatRoom chat: this.chatRooms) {
            if (idRoom.equals(chat.getId())) {
                return chat;
            }
        }
        return null;
    }

    public void chooseTheWord(String idRoom, String word, User user) throws InvalidUserToSetTheWord {
        if (!user.getUserType().equals(User.UserType.ROOM_MASTER))
            throw new InvalidUserToSetTheWord("Usuário não é o mestre da rodada e não pode selecionar a palavra!");

        ChatRoom chatRoom = getchatRoom(idRoom);
        chatRoom.setWordToGuess(word);
    }
}
