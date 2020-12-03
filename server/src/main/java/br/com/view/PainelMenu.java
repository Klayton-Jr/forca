package br.com.view;

import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

public class PainelMenu extends Painel {

    private Button btnCriarSala;
    private Button btnProcurarSala;
    private TextField edtNome;

    public PainelMenu() {
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
        btnCriarSala.requestFocus();
        vBox.getChildren().add(btnCriarSala);

        btnProcurarSala = new Button("Procurar sala");
        btnProcurarSala.setMaxWidth(Double.MAX_VALUE);
        vBox.getChildren().add(btnProcurarSala);
    }

    public TextField getEdtNome() {
        return edtNome;
    }

    public Button getBtnCriarSala() {
        return btnCriarSala;
    }

    public Button getBtnProcurarSala() {
        return btnProcurarSala;
    }

    @Override
    public void clear() {

    }
}
