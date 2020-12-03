package br.com.view;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

public class PainelNovaSala extends Painel {

    private Button btnVoltar;
    private TextField edtNomeSala;

    public PainelNovaSala() {
        inicializar();
    }

    private void inicializar() {
        btnVoltar = new Button("Voltar");
        AnchorPane.setTopAnchor(btnVoltar, 10.0);
        AnchorPane.setLeftAnchor(btnVoltar, 10.0);
        getChildren().add(btnVoltar);

        Label rtlNomeSala = new Label("Nome da Sala:");
        rtlNomeSala.setPrefWidth(100);
        rtlNomeSala.setStyle("-fx-text-alignment: right; -fx-alignment: center-right");
        AnchorPane.setTopAnchor(rtlNomeSala, 43.0);
        AnchorPane.setLeftAnchor(rtlNomeSala, 10.0);
        getChildren().add(rtlNomeSala);

        edtNomeSala = new TextField();
        AnchorPane.setTopAnchor(edtNomeSala, 40.0);
        AnchorPane.setLeftAnchor(edtNomeSala, 115.0);
        AnchorPane.setRightAnchor(edtNomeSala, 10.0);
        getChildren().add(edtNomeSala);
    }

    public Button getBtnVoltar() {
        return btnVoltar;
    }

    @Override
    public void clear() {
        edtNomeSala.clear();
    }
}
