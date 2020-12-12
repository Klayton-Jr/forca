package cliente.view;

import cliente.comunicacao.CarregarSala;
import cliente.comunicacao.IniciarJogo;
import cliente.model.*;
import javafx.event.ActionEvent;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;

public class PainelSala extends AnchorPane {

    private final Formulario form;
    private TreeItem<Usuario> noRaiz;
    private Button btnIniciarJogo;
    private Label lblMensagem;
    private TextField edtResposta;
    private Button btnEnviar;

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

        ImageView imageView = new ImageView(new Image(getClass().getResourceAsStream("/img/aguardando.png")));
        AnchorPane.setTopAnchor(imageView, 50.0);
        AnchorPane.setLeftAnchor(imageView, 165.0);
        getChildren().add(imageView);

        lblMensagem = new Label("Aguardando outros jogadores");
        lblMensagem.setStyle("-fx-alignment: center; -fx-text-fill: blue; -fx-font-size: 2em;");
        AnchorPane.setLeftAnchor(lblMensagem, 165.0);
        AnchorPane.setRightAnchor(lblMensagem, 15.0);
        AnchorPane.setBottomAnchor(lblMensagem, 67.0);
        getChildren().add(lblMensagem);

        edtResposta = new TextField();
        edtResposta.setEditable(false);
        AnchorPane.setLeftAnchor(edtResposta, 160.0);
        AnchorPane.setRightAnchor(edtResposta, 65.0);
        AnchorPane.setBottomAnchor(edtResposta, 10.0);
        getChildren().add(edtResposta);

        btnEnviar = new Button("Enviar");
        AnchorPane.setRightAnchor(btnEnviar, 10.0);
        AnchorPane.setBottomAnchor(btnEnviar, 10.0);
        getChildren().add(btnEnviar);

    }

    private void clicarSair(ActionEvent actionEvent) {

    }

    private void inicarJogo(ActionEvent actionEvent) {
        new Thread(new IniciarJogo(new ObservadorIniciarJogo(), form.getParametrosTelas())).start();
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

    private class ObservadorIniciarJogo implements Observador<Boolean> {

        @Override
        public void sucesso(Boolean resultado) {

        }

        @Override
        public void erro(String mensagem) {
            form.exibirErro(mensagem);
        }
    }
}
