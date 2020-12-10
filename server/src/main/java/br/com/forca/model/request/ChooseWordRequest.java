package br.com.forca.model.request;

import br.com.forca.model.User;
import lombok.Getter;
import lombok.Setter;

public class ChooseWordRequest {
    @Getter @Setter
    private String word;
    @Getter @Setter
    private User user;
}
