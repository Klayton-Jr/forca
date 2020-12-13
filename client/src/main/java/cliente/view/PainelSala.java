package cliente.view;

import cliente.comunicacao.*;
import cliente.model.*;
import javafx.event.ActionEvent;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;

import java.text.Normalizer;
import java.util.*;
import java.util.stream.Collectors;

public class PainelSala extends AnchorPane {

    private final Formulario form;
    private TreeItem<Usuario> noRaiz;
    private Button btnIniciarJogo;
    private Label lblMensagem;
    private TextField edtResposta;
    private Button btnEnviar;
    private ImageView imageView;
    private String palavraAtual;
    private String palavraAtualSemAcento;
    private List<String> tentativas;
    private int quantidadeErros;
    private int quantidadeAcertosSequidos;
    private CarregarSala carregarSala;
    private Image imageAguardando;
    private Image imageSuaVez;
    private Image imageAguardandoPalavra;
    private Image imageForca0;
    private Image imageForca1;
    private Image imageForca2;
    private Image imageForca3;
    private Image imageForca4;
    private Image imageForca5;
    private Image imageForca6;
    private Image imageForcaFalhou;
    private Image imageAcerto1;
    private Image imageAcerto2;
    private Image imageAcerto3;
    private Image imageAcerto4;
    private Image image1Lugar;
    private Image image2Lugar;
    private Image image3Lugar;
    private Image image4Lugar;
    private Label lblNumeroRodadas;
    private Label lblNomeSala;
    private Label lblNomeUsuario;

    public PainelSala(Formulario form) {
        this.form = form;
        this.tentativas = new ArrayList<>();
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

        lblNomeSala = new Label();
        lblNomeSala.setStyle("-fx-alignment: center; -fx-text-fill: blue; -fx-font-size: 1.5em;");
        AnchorPane.setLeftAnchor(lblNomeSala, 60.0);
        AnchorPane.setRightAnchor(lblNomeSala, 60.0);
        AnchorPane.setTopAnchor(lblNomeSala, 4.0);
        getChildren().add(lblNomeSala);

        lblNomeUsuario = new Label();
        lblNomeUsuario.setStyle("-fx-alignment: center; -fx-text-fill: blue; -fx-font-size: 1em;");
        AnchorPane.setLeftAnchor(lblNomeUsuario, 60.0);
        AnchorPane.setRightAnchor(lblNomeUsuario, 60.0);
        AnchorPane.setTopAnchor(lblNomeUsuario, 28.0);
        getChildren().add(lblNomeUsuario);

        noRaiz = new TreeItem<>();

        TreeView<Usuario> treeViewUsuarios = new TreeView<>();
        treeViewUsuarios.setPrefWidth(150.0);
        treeViewUsuarios.setRoot(noRaiz);
        treeViewUsuarios.setShowRoot(false);
        AnchorPane.setLeftAnchor(treeViewUsuarios, 0.0);
        AnchorPane.setTopAnchor(treeViewUsuarios, 50.0);
        AnchorPane.setBottomAnchor(treeViewUsuarios, 0.0);
        getChildren().add(treeViewUsuarios);

        carregarImagens();

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

        lblNumeroRodadas = new Label();
        lblNumeroRodadas.setStyle("-fx-alignment: center; -fx-text-fill: darkgreen; -fx-font-size: 1em;");
        AnchorPane.setLeftAnchor(lblNumeroRodadas, 165.0);
        AnchorPane.setRightAnchor(lblNumeroRodadas, 15.0);
        AnchorPane.setBottomAnchor(lblNumeroRodadas, 40.0);
        getChildren().add(lblNumeroRodadas);

        edtResposta = new TextField();
        edtResposta.setEditable(false);
        edtResposta.setOnKeyPressed(this::eventoEnter);
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

    private void carregarImagens() {
        imageAguardando = new Image(getClass().getResourceAsStream("/img/aguardando.png"));
        imageAguardandoPalavra = new Image(getClass().getResourceAsStream("/img/aguardando_palavra.png"));
        imageSuaVez = new Image(getClass().getResourceAsStream("/img/sua_vez.png"));
        imageForca0 = new Image(getClass().getResourceAsStream("/img/forca0.png"));
        imageForca1 = new Image(getClass().getResourceAsStream("/img/forca1.png"));
        imageForca2 = new Image(getClass().getResourceAsStream("/img/forca2.png"));
        imageForca3 = new Image(getClass().getResourceAsStream("/img/forca3.png"));
        imageForca4 = new Image(getClass().getResourceAsStream("/img/forca4.png"));
        imageForca5 = new Image(getClass().getResourceAsStream("/img/forca5.png"));
        imageForca6 = new Image(getClass().getResourceAsStream("/img/forca6.png"));
        imageForcaFalhou = new Image(getClass().getResourceAsStream("/img/falhou.png"));
        imageAcerto1 = new Image(getClass().getResourceAsStream("/img/acertou01.png"));
        imageAcerto2 = new Image(getClass().getResourceAsStream("/img/acertou02.png"));
        imageAcerto3 = new Image(getClass().getResourceAsStream("/img/acertou03.png"));
        imageAcerto4 = new Image(getClass().getResourceAsStream("/img/acertou04.png"));
        image1Lugar = new Image(getClass().getResourceAsStream("/img/1lugar.png"));
        image2Lugar = new Image(getClass().getResourceAsStream("/img/2lugar.png"));
        image3Lugar = new Image(getClass().getResourceAsStream("/img/3lugar.png"));
        image4Lugar = new Image(getClass().getResourceAsStream("/img/4lugar.png"));
    }

    private void eventoEnter(KeyEvent event) {
        if (KeyCode.ENTER == event.getCode()) {
            event.consume();
            enviar();
        }
    }

    private void clicarSair(ActionEvent e) {
        new Thread(new SairSala(new ObservadorSairSala(), form.getParametrosTelas())).start();
        e.consume();
    }

    private void clicarEnviar(ActionEvent e) {
        e.consume();
        enviar();
    }

    private void enviar() {
        ParametrosTelas parametros = form.getParametrosTelas();
        Sala sala = parametros.getSala();
        Usuario usuario = parametros.getUsuario();

        final String resposta = edtResposta.getText().trim();

        if (SituacaoJogo.ESCOLHENDO_PALAVRA == sala.getSituacaoJogo()) {
            if (resposta.length() < 4)
                form.exibirErro("Tamanho minimo da palavra é 4.");
            else if (sala.getUsuarioVezID().equals(usuario.getId()))
                new Thread(new EnviarPalavra(new ObservadorBasico(), form.getParametrosTelas(), resposta)).start();
        } else {
            if (resposta.length() == 1) {
                String caractere = normalizaTexto(resposta);
                if (palavraAtualSemAcento.contains(caractere)) {
                    if (!tentativas.contains(caractere)) {
                        tentativas.add(caractere);
                        atualizarPalavraAtual();
                    }
                } else {
                    quantidadeErros++;
                    atualizarImagemForca();
                }
            } else if (resposta.length() > 1) {
                if (palavraAtualSemAcento.equalsIgnoreCase(resposta)) {
                    enviarResposta();
                } else {
                    quantidadeErros++;
                    atualizarImagemForca();
                }
            }

            if (quantidadeErros == 6)
                enviarResposta();
        }
        edtResposta.clear();
    }

    private void enviarResposta() {
        edtResposta.setEditable(false);
        btnEnviar.setDisable(true);

        if (quantidadeErros >= 6) {
            imageView.setImage(imageForcaFalhou);
            quantidadeAcertosSequidos = 0;
            lblMensagem.setText("Que pena!!!");
        } else {
            atualizarImagemAcertos();
            lblMensagem.setText("Acertou!!!");
        }

        ParametrosTelas parametros = form.getParametrosTelas();
        parametros.getUsuario().setPontuacao(parametros.getUsuario().getPontuacao() + getPontuacaoRodada());

        new Thread(new EnviarResposta(new ObservadorBasico(), parametros, quantidadeErros)).start();
    }

    private void atualizarImagemAcertos() {
        switch (quantidadeAcertosSequidos) {
            case 0 -> imageView.setImage(imageAcerto1);
            case 1 -> imageView.setImage(imageAcerto2);
            case 2 -> imageView.setImage(imageAcerto3);
            default -> imageView.setImage(imageAcerto4);
        }
    }

    private int getPontuacaoRodada() {
        return quantidadeErros == 6 ? 0 : 100 - (quantidadeErros * 15);
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
        palavraAtualSemAcento = normalizaTexto(palavraAtual);

        if (sala.getUsuarioDonoID().equals(usuario.getId()))
            btnIniciarJogo.setVisible(Situacao.JOGANDO != sala.getSituacao());
        else
            btnIniciarJogo.setVisible(false);

        noRaiz.getChildren().clear();

        List<Usuario> usuarioOrdenado = sala.getUsuarios().stream().sorted((f1, f2) -> Long.compare(f2.getPontuacao(), f1.getPontuacao())).collect(Collectors.toList());

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

                quantidadeErros = 0;
                tentativas.clear();
            } else {
                if (sala.getUsuarioVezID().equals(usuario.getId())) {
                    lblMensagem.setText("Aguardando outros usuários responderem.");
                    imageView.setImage(imageAguardandoPalavra);

                    edtResposta.setEditable(false);
                    btnEnviar.setDisable(true);
                } else if (SituacaoUsuario.JOGANDO == usuario.getSituacao()) {
                    atualizarPalavraAtual();
                    atualizarImagemForca();
                    edtResposta.setEditable(true);
                    btnEnviar.setDisable(false);
                }
            }
            lblNumeroRodadas.setText("Rodada: " + sala.getNumeroAtualRodada() + " / " + sala.getNumeroTotalRodadas());
        } else if (Situacao.FINALIZADA == sala.getSituacao()) {
            lblMensagem.setText("Jogo Encerrado.");
            lblNumeroRodadas.setText("");
            atualizarImagemResultado(usuarioOrdenado.indexOf(usuario));
            edtResposta.setEditable(false);
            btnEnviar.setDisable(true);
        }
    }

    private void atualizarImagemResultado(int posicao) {
        switch (posicao) {
            case 0 -> imageView.setImage(image1Lugar);
            case 1 -> imageView.setImage(image2Lugar);
            case 2 -> imageView.setImage(image3Lugar);
            default -> imageView.setImage(image4Lugar);
        }
    }

    private void atualizarImagemForca() {
        switch (quantidadeErros) {
            case 0 -> imageView.setImage(imageForca0);
            case 1 -> imageView.setImage(imageForca1);
            case 2 -> imageView.setImage(imageForca2);
            case 3 -> imageView.setImage(imageForca3);
            case 4 -> imageView.setImage(imageForca4);
            case 5 -> imageView.setImage(imageForca5);
            default -> imageView.setImage(imageForca6);
        }
    }

    private void atualizarPalavraAtual() {
        StringBuilder builder = new StringBuilder();

        for (char caractere : palavraAtual.toCharArray()) {
            String caractereSTR = normalizaTexto(String.valueOf(caractere));

            if (tentativas.contains(caractereSTR))
                builder.append(caractere);
            else if (" ".equalsIgnoreCase(caractereSTR)) {
                builder.append(" ");
            } else {
                builder.append("_");
            }
            builder.append(" ");
        }

        lblMensagem.setText("Palavra: " + builder.toString());
    }

    public static String normalizaTexto(String str) {
        return Normalizer.normalize(str, Normalizer.Form.NFD).replaceAll("[^\\p{ASCII}]", "").toLowerCase(Locale.ROOT);
    }

    public void iniciarServico() {
        lblNomeSala.setText(form.getSala().getNome());
        lblNomeUsuario.setText(form.getUsuario().getNome());

        atualizar();

        carregarSala = new CarregarSala(new ObservadorCarregarSala(), form.getParametrosTelas());
        new Thread(carregarSala).start();
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

    private class ObservadorSairSala implements Observador<Boolean> {

        @Override
        public void sucesso(Boolean aBoolean) {
            carregarSala.parar();
            form.mudarParaPainelMenu();
        }

        @Override
        public void erro(String mensagem) {
            form.exibirErro(mensagem);
        }
    }
}
