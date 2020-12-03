package br.com.view;

import br.com.model.Sala;
import br.com.model.Usuario;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class Formulario extends Application {

    private Usuario usuario;
    private Sala sala;

    private Stage stage;
    private Scene menuScene;
    private Scene novaSalaScene;
    private Scene procurarSalaScene;
    private Scene salaScene;
    private PainelMenu painelMenu;
    private PainelNovaSala painelNovaSala;
    private PainelProcurarSala painelProcurarSala;
    private PainelSala painelSala;

    @Override
    public void start(Stage stage) {
        this.stage = stage;
        stage.setWidth(800);
        stage.setHeight(600);
        stage.setResizable(false);
        mudarParaPainelMenu();
    }

    private AnchorPane getPainelMenu() {
        painelMenu = new PainelMenu(this);
        return painelMenu;
    }

    private AnchorPane getPainelNovaSala() {
        painelNovaSala = new PainelNovaSala(this);
        return painelNovaSala;
    }

    private AnchorPane getPainelProcurarSala() {
        painelProcurarSala = new PainelProcurarSala(this);
        return painelProcurarSala;
    }

    private AnchorPane getPainelSala() {
        painelSala = new PainelSala(this);
        return painelSala;
    }

    void mudarParaPainelMenu() {
        if (menuScene == null)
            menuScene = new Scene(getPainelMenu());

        stage.setScene(menuScene);
        stage.setTitle("Jogo da Forca");
        stage.show();
    }

    void mudarParaPainelCriarSala() {
        if (novaSalaScene == null)
            novaSalaScene = new Scene(getPainelNovaSala());
        else
            painelNovaSala.clear();

        stage.setScene(novaSalaScene);
        stage.setTitle("Criar Sala");
        stage.show();
    }

    void mudarParaPainelProcurarSala() {
        if (procurarSalaScene == null)
            procurarSalaScene = new Scene(getPainelProcurarSala());
        else
            painelProcurarSala.atualizar();

        stage.setScene(procurarSalaScene);
        stage.setTitle("Salas");
        stage.show();
    }

    public void mudarParaPainelSala() {
        if (salaScene == null)
            salaScene = new Scene(getPainelSala());

        painelSala.atualizar(this.sala);

        stage.setScene(salaScene);
        stage.setTitle("Sala");
        stage.show();
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Sala getSala() {
        return sala;
    }

    public void setSala(Sala sala) {
        this.sala = sala;
    }

    public static void main(String[] args) {
        launch(args);
    }
}
