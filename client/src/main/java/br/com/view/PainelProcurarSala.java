package br.com.view;

import javafx.scene.control.Button;
import javafx.scene.control.TableView;
import javafx.scene.layout.AnchorPane;

public class PainelProcurarSala extends AnchorPane {

    private Button btnVoltar;
    private Button btnEntrar;

    public PainelProcurarSala() {
        inicializar();
    }

    private void inicializar() {
        btnVoltar = new Button("Voltar");
        AnchorPane.setTopAnchor(btnVoltar, 10.0);
        AnchorPane.setLeftAnchor(btnVoltar, 10.0);
        getChildren().add(btnVoltar);

        btnEntrar = new Button("Entrar");
        AnchorPane.setTopAnchor(btnEntrar, 10.0);
        AnchorPane.setRightAnchor(btnEntrar, 10.0);
        getChildren().add(btnEntrar);

        TableView tabelaSalas = new TableView<>();
        AnchorPane.setTopAnchor(tabelaSalas, 50.0);
        AnchorPane.setLeftAnchor(tabelaSalas, 10.0);
        AnchorPane.setRightAnchor(tabelaSalas, 10.0);
        AnchorPane.setBottomAnchor(tabelaSalas, 10.0);
        getChildren().add(tabelaSalas);
    }

    public Button getBtnVoltar() {
        return btnVoltar;
    }

    public void atualizar() {

    }
}
