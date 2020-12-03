package br.com.forca;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.SplitPane;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class GraficoTeste extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {

        AnchorPane painelPrincipal = new AnchorPane();
        stage.setScene(new Scene(painelPrincipal));
        stage.setTitle("Jogo da Forca");
        stage.setWidth(800);
        stage.setHeight(600);
        stage.show();

        AnchorPane painel1 = new AnchorPane();
        AnchorPane painel2 = new AnchorPane();

        SplitPane splitPane = new SplitPane(painel1, painel2);
        AnchorPane.setLeftAnchor(splitPane, 0.0);
        AnchorPane.setRightAnchor(splitPane, 0.0);
        AnchorPane.setTopAnchor(splitPane, 0.0);
        AnchorPane.setBottomAnchor(splitPane, 0.0);
        painelPrincipal.getChildren().add(splitPane);
    }
}
