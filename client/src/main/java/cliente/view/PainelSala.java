package cliente.view;

import cliente.comunicacao.CarregarSala;
import cliente.comunicacao.EnviarPalavraVez;
import cliente.comunicacao.IniciarJogo;
import cliente.model.*;
import javafx.event.ActionEvent;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class PainelSala extends AnchorPane {

    private final Formulario form;
    private TreeItem<Usuario> noRaiz;
    private Button btnIniciarJogo;
    private Label lblMensagem;
    private TextField edtResposta;
    private Button btnEnviar;
    private ImageView imageView;
    private Image imageAguardando;
    private Image imageSuaVez;
    private Image imageAguardandoPalavra;
    private String palavraAtual;
    private Image imageForca0;

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

        imageAguardando = new Image(getClass().getResourceAsStream("/img/aguardando.png"));
        imageAguardandoPalavra = new Image(getClass().getResourceAsStream("/img/aguardando_palavra.png"));
        imageSuaVez = new Image(getClass().getResourceAsStream("/img/sua_vez.png"));
        imageForca0 = new Image(getClass().getResourceAsStream("/img/forca0.png"));

        imageView = new ImageView(imageAguardando);
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
        btnEnviar.setOnAction(this::clicarEnviar);
        btnEnviar.setDisable(true);
        AnchorPane.setRightAnchor(btnEnviar, 10.0);
        AnchorPane.setBottomAnchor(btnEnviar, 10.0);
        getChildren().add(btnEnviar);
    }

    private void clicarSair(ActionEvent e) {
        e.consume();
    }

    private void clicarEnviar(ActionEvent e) {
        e.consume();
        ParametrosTelas parametros = form.getParametrosTelas();
        Sala sala = parametros.getSala();
        Usuario usuario = parametros.getUsuario();

        final String resposta = edtResposta.getText();

        if (SituacaoJogo.ESCOLHENDO_PALAVRA == sala.getSituacaoJogo()) {
            if (sala.getUsuarioVezID().equals(usuario.getId())) {
                new Thread(new EnviarPalavraVez(new ObservadorBasico(), form.getParametrosTelas(), resposta)).start();
            }
        }
    }

    private void inicarJogo(ActionEvent e) {
        new Thread(new IniciarJogo(new ObservadorBasico(), form.getParametrosTelas())).start();
        e.consume();
    }

    private void atualizar() {
        ParametrosTelas parametros = form.getParametrosTelas();
        Sala sala = parametros.getSala();
        Usuario usuario = parametros.getUsuario();

        palavraAtual = sala.getPalavraAtual();

        if (!sala.getUsuarioDonoID().equals(usuario.getId()) || Situacao.EM_ESPERA != sala.getSituacao())
            btnIniciarJogo.setVisible(false);

        noRaiz.getChildren().clear();

        List<Usuario> usuarioOrdenado = sala.getUsuarios().stream().sorted(Comparator.comparingInt(Usuario::getPontuacao)).collect(Collectors.toList());

        for (Usuario usuarioSala : usuarioOrdenado) {
            noRaiz.getChildren().add(new TreeItem<>(usuarioSala));
        }

        if (Situacao.JOGANDO == sala.getSituacao()) {
            if (SituacaoJogo.ESCOLHENDO_PALAVRA == sala.getSituacaoJogo()) {
                if (!sala.getUsuarioVezID().equals(usuario.getId())) {
                    lblMensagem.setText("Esperando usuário escolher uma palavra.");
                    imageView.setImage(imageAguardandoPalavra);

                    edtResposta.setEditable(false);
                    btnEnviar.setDisable(true);
                } else {
                    lblMensagem.setText("Escolha uma palavra.");
                    imageView.setImage(imageSuaVez);

                    edtResposta.setEditable(true);
                    btnEnviar.setDisable(false);
                }
            } else {
                if (sala.getUsuarioVezID().equals(usuario.getId())) {
                    lblMensagem.setText("Aguardando outros usuários responderem.");
                    imageView.setImage(imageAguardandoPalavra);

                    edtResposta.setEditable(false);
                    btnEnviar.setDisable(true);
                } else {
                    lblMensagem.setText("Palavra: " + "_ ".repeat(sala.getPalavraAtual().length()));
                    imageView.setImage(imageForca0);

                    edtResposta.setEditable(true);
                    btnEnviar.setDisable(false);
                }
            }
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

    private class ObservadorBasico implements Observador<Boolean> {

        @Override
        public void sucesso(Boolean resultado) {

        }

        @Override
        public void erro(String mensagem) {
            form.exibirErro(mensagem);
        }
    }
}
