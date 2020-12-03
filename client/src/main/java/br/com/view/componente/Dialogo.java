package br.com.view.componente;

import javafx.scene.control.Alert;

public class Dialogo {

    public static void atencao(String mensagem) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("");
        alert.setHeaderText("Atenção");
        alert.setContentText(mensagem);
        alert.showAndWait();
    }
}
