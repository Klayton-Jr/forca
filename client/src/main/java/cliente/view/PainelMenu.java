package cliente.view;

import cliente.comunicacao.ValidarUsuario;
import cliente.model.Observador;
import cliente.model.Usuario;
import cliente.view.componente.Dialogo;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

public class PainelMenu extends AnchorPane {

    private TextField edtNome;
    private final Formulario form;

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

        Button btnCriarSala = new Button("Criar sala");
        btnCriarSala.setMaxWidth(Double.MAX_VALUE);
        btnCriarSala.setOnAction(this::clicarCriarSala);
        btnCriarSala.requestFocus();
        vBox.getChildren().add(btnCriarSala);

        Button btnProcurarSala = new Button("Procurar sala");
        btnProcurarSala.setMaxWidth(Double.MAX_VALUE);
        btnProcurarSala.setOnAction(this::clicarProcurarSala);
        vBox.getChildren().add(btnProcurarSala);
    }

    private void clicarCriarSala(ActionEvent event) {
        if (edtNome != null && !edtNome.getText().trim().equals("")) {
            new Thread(new ValidarUsuario(new ObservadorCriarSala(), getUsuario())).start();
        } else {
            Dialogo.atencao("Informe um nome de usuário para continuar!");
        }

        event.consume();
    }

    private Usuario getUsuario() {
        Usuario usuario = form.getUsuario();
        if (usuario == null)
            return new Usuario(edtNome.getText());
        else
            return new Usuario(usuario.getId(), edtNome.getText());
    }

    private void clicarProcurarSala(ActionEvent event) {
        if (edtNome != null && !edtNome.getText().trim().equals("")) {
            new Thread(new ValidarUsuario(new ObservadorProcurarSala(), getUsuario())).start();
        } else {
            Dialogo.atencao("Informe um nome de usuário para continuar!");
        }

        event.consume();
    }

    private class ObservadorCriarSala implements Observador<Usuario> {

        @Override
        public void sucesso(Usuario usuario) {
            form.setUsuario(usuario);
            form.mudarParaPainelCriarSala();
        }

        @Override
        public void erro(String mensagem) {
            form.exibirErro(mensagem);
        }
    }



    private class ObservadorProcurarSala implements Observador<Usuario> {

        @Override
        public void sucesso(Usuario usuario) {
            form.setUsuario(usuario);
            form.mudarParaPainelProcurarSala();
        }

        @Override
        public void erro(String mensagem) {
            form.exibirErro(mensagem);
        }
    }
}
