package br.com.view;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class Formulario extends Application {

    private Stage stage;
    private Scene menuScene;
    private Scene novaSalaScene;
    private PainelMenu painelMenu;
    private PainelNovaSala painelNovaSala;
    private PainelProcurarSala painelProcurarSala;
    private Scene procurarSalaScene;

    @Override
    public void start(Stage stage) {
        this.stage = stage;
        stage.setWidth(800);
        stage.setHeight(600);
        stage.setResizable(false);
        criarPainelMenu();
    }

    private AnchorPane getPainelMenu() {
        painelMenu = new PainelMenu();
        painelMenu.getBtnCriarSala().setOnAction(this::irParaCriarSala);
        painelMenu.getBtnProcurarSala().setOnAction(this::irParaProcurarSala);
        return painelMenu;
    }

    private AnchorPane getNovaSala() {
        painelNovaSala = new PainelNovaSala();
        painelNovaSala.getBtnVoltar().setOnAction(this::voltarParaMenu);
        return painelNovaSala;
    }

    private AnchorPane getProcurarSala() {
        painelProcurarSala = new PainelProcurarSala();
        painelProcurarSala.getBtnVoltar().setOnAction(this::voltarParaMenu);
        return painelProcurarSala;
    }

    private void voltarParaMenu(ActionEvent actionEvent) {
        criarPainelMenu();
    }

    private void criarPainelMenu() {
        if (menuScene == null)
            menuScene = new Scene(getPainelMenu());

        stage.setScene(menuScene);
        stage.setTitle("Jogo da Forca");
        stage.show();
    }

    private void irParaCriarSala(ActionEvent actionEvent) {
        if (novaSalaScene == null)
            novaSalaScene = new Scene(getNovaSala());
        else
            painelNovaSala.clear();

        stage.setScene(novaSalaScene);
        stage.setTitle("Criar Sala");
        stage.show();
    }

    private void irParaProcurarSala(ActionEvent actionEvent) {
        if (procurarSalaScene == null)
            procurarSalaScene = new Scene(getProcurarSala());
        else
            painelProcurarSala.atualizar();

        stage.setScene(procurarSalaScene);
        stage.setTitle("Salas");
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
