package br.com.view;

import br.com.comunicacao.ValidarUsuario;
import br.com.model.Usuario;
import br.com.view.componente.Dialogo;
import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

import javax.swing.*;

public class PainelMenu extends AnchorPane {

    private Button btnCriarSala;
    private Button btnProcurarSala;
    private TextField edtNome;
    private Formulario form;
    private ValidarUsuario validarUsuario;

    public PainelMenu(Formulario form) {
        this.form = form;
        inicializa();
    }

    private void inicializa() {
        ImageView imageView = new ImageView(new Image(getClass().getResourceAsStream("/img/forca.jpg")));
        imageView.setFitHeight(getHeight());
        imageView.setFitWidth(getWidth());
        AnchorPane.setTopAnchor(imageView, 0.0);
        AnchorPane.setLeftAnchor(imageView, 0.0);
        AnchorPane.setRightAnchor(imageView, 0.0);
        AnchorPane.setBottomAnchor(imageView, 0.0);
        getChildren().add(imageView);

        VBox vBox = new VBox(35);
        AnchorPane.setTopAnchor(vBox, 100.0);
        AnchorPane.setLeftAnchor(vBox, 20.0);
        AnchorPane.setRightAnchor(vBox, 20.0);
        AnchorPane.setBottomAnchor(vBox, 20.0);
        getChildren().add(vBox);

        edtNome = new TextField();
        edtNome.setPromptText("Insira seu nome de usuário");
        vBox.getChildren().add(edtNome);

        btnCriarSala = new Button("Criar sala");
        btnCriarSala.setMaxWidth(Double.MAX_VALUE);
        btnCriarSala.setOnAction(this::clicarCriarSala);
        btnCriarSala.requestFocus();
        vBox.getChildren().add(btnCriarSala);

        btnProcurarSala = new Button("Procurar sala");
        btnProcurarSala.setMaxWidth(Double.MAX_VALUE);
        btnProcurarSala.setOnAction(this::clicarProcurarSala);
        vBox.getChildren().add(btnProcurarSala);
    }

    private void clicarCriarSala(ActionEvent event) {
        if (edtNome != null && !edtNome.getText().trim().equals("")) {
            validarUsuario = new ValidarUsuario(this::observadorCriarSala, edtNome.getText());
            validarUsuario.executar();
        } else {
            Dialogo.atencao("Informe um nome de usuário para continuar!");
        }

        event.consume();
    }

    private void clicarProcurarSala(ActionEvent event) {
        if (edtNome != null && !edtNome.getText().trim().equals("")) {
            validarUsuario = new ValidarUsuario(this::observadorProcurarSala, edtNome.getText());
            validarUsuario.executar();
        } else {
            Dialogo.atencao("Informe um nome de usuário para continuar!");
        }

        event.consume();
    }

    private void observadorProcurarSala(boolean resultado, Usuario usuario) {
        if (resultado) {
            validarUsuario.parar();
            form.setUsuario(usuario);
            form.mudarParaPainelProcurarSala();
        }
    }

    private void observadorCriarSala(boolean resultado, Usuario usuario) {
        if (resultado) {
            validarUsuario.parar();
            form.setUsuario(usuario);
            form.mudarParaPainelCriarSala();
        }
    }

}
