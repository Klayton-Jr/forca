package br.com.view;

import br.com.comunicacao.CarregarSala;
import br.com.model.*;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;
import javafx.scene.layout.AnchorPane;

public class PainelSala extends AnchorPane {

    private final Formulario form;
    private TreeItem<Usuario> noRaiz;
    private Button btnIniciarJogo;

    public PainelSala(Formulario form) {
        this.form = form;
        incializar();
    }

    private void incializar() {
        Button btnVoltar = new Button("Sair");
        btnVoltar.setOnAction(this::clicarSair);
        AnchorPane.setTopAnchor(btnVoltar, 10.0);
        AnchorPane.setLeftAnchor(btnVoltar, 10.0);
        getChildren().add(btnVoltar);

        btnIniciarJogo = new Button("Iniciar");
        btnIniciarJogo.setOnAction(this::inicarJogo);
        AnchorPane.setTopAnchor(btnIniciarJogo, 10.0);
        AnchorPane.setRightAnchor(btnIniciarJogo, 10.0);
        getChildren().add(btnIniciarJogo);

        noRaiz = new TreeItem<>();

        TreeView<Usuario> treeViewUsuarios = new TreeView<>();
        treeViewUsuarios.setPrefWidth(150.0);
        treeViewUsuarios.setRoot(noRaiz);
        treeViewUsuarios.setShowRoot(false);
        AnchorPane.setLeftAnchor(treeViewUsuarios, 0.0);
        AnchorPane.setTopAnchor(treeViewUsuarios, 50.0);
        AnchorPane.setBottomAnchor(treeViewUsuarios, 0.0);
        getChildren().add(treeViewUsuarios);

        AnchorPane painelJogo = new AnchorPane();
        AnchorPane.setLeftAnchor(painelJogo, 150.0);
        AnchorPane.setTopAnchor(painelJogo, 50.0);
        AnchorPane.setRightAnchor(painelJogo, 0.0);
        AnchorPane.setBottomAnchor(painelJogo, 0.0);
        getChildren().add(painelJogo);

    }

    private void clicarSair(ActionEvent actionEvent) {

    }

    private void inicarJogo(ActionEvent actionEvent) {

    }

    private void atualizar() {
        ParametrosTelas parametros = form.getParametrosTelas();
        Sala sala = parametros.getSala();
        Usuario usuario = parametros.getUsuario();

        if (!sala.getUsuarioDonoID().equals(usuario.getId()) || Situacao.EM_ESPERA != sala.getSituacao())
            btnIniciarJogo.setVisible(false);

        noRaiz.getChildren().clear();

        for (Usuario usuarioSala : sala.getUsuarios()) {
            noRaiz.getChildren().add(new TreeItem<>(usuarioSala));
        }
    }

    public void iniciarServico() {
        atualizar();
        new Thread(new CarregarSala(new ObservadorCarregarSala(), form.getParametrosTelas())).start();
    }

    private class ObservadorCarregarSala implements Observador<ParametrosTelas> {

        @Override
        public void sucesso(ParametrosTelas parametros) {
            form.setParametrosTelas(parametros);
            atualizar();
        }

        @Override
        public void erro(String mensagem) {
            form.exibirErro(mensagem);
        }
    }
}
