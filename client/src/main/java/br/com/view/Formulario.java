package br.com.view;

import br.com.model.ParametrosTelas;
import br.com.model.Sala;
import br.com.model.Usuario;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class Formulario extends Application {

    private final ParametrosTelas parametros = new ParametrosTelas();

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

        painelProcurarSala.atualizar();

        stage.setScene(procurarSalaScene);
        stage.setTitle("Salas");
        stage.show();
    }

    public void mudarParaPainelSala() {
        if (salaScene == null)
            salaScene = new Scene(getPainelSala());

        painelSala.iniciarServico();

        stage.setScene(salaScene);
        stage.setTitle("Sala");
        stage.show();
    }

    public ParametrosTelas getParametrosTelas() {
        return parametros;
    }

    public void setParametrosTelas(ParametrosTelas parametrosTelas) {
        this.parametros.setSala(parametrosTelas.getSala());
        this.parametros.setUsuario(parametrosTelas.getUsuario());
    }

    public Usuario getUsuario() {
        return parametros.getUsuario();
    }

    public void setUsuario(Usuario usuario) {
        this.parametros.setUsuario(usuario);
    }

    public Sala getSala() {
        return parametros.getSala();
    }

    public void setSala(Sala sala) {
        this.parametros.setSala(sala);
    }

    public final void exibirErro(String mensagem) {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Atenção");
        alert.setHeaderText(mensagem);
        alert.show();
    }

    public static void main(String[] args) {
        launch(args);
    }

}
