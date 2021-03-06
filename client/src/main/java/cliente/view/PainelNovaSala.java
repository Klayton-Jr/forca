package cliente.view;

import cliente.comunicacao.CriarSala;
import cliente.model.Observador;
import cliente.model.Sala;
import cliente.view.componente.IntTextField;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

import java.util.Collections;

public class PainelNovaSala extends AnchorPane {

    private TextField edtNomeSala;
    private IntTextField edtQuantidadeMaximaJogadores;
    private IntTextField edtNumeroRodadas;
    private IntTextField edtTempoResponderCadaLetra;
    private final Formulario form;

    public PainelNovaSala(Formulario form) {
        this.form = form;
        inicializar();
    }

    private void inicializar() {
        Button btnVoltar = new Button("Voltar");
        btnVoltar.setOnAction(this::clicarVoltar);
        AnchorPane.setTopAnchor(btnVoltar, 10.0);
        AnchorPane.setLeftAnchor(btnVoltar, 10.0);
        getChildren().add(btnVoltar);

        Button btnCriarSala = new Button("Criar");
        btnCriarSala.setOnAction(this::clicarCriarSala);
        AnchorPane.setTopAnchor(btnCriarSala, 10.0);
        AnchorPane.setRightAnchor(btnCriarSala, 10.0);
        getChildren().add(btnCriarSala);

        AnchorPane painelConfiguracoes = new AnchorPane();
        painelConfiguracoes.setStyle("-fx-background-color: #FFFFFF");
        AnchorPane.setTopAnchor(painelConfiguracoes, 50.0);
        AnchorPane.setLeftAnchor(painelConfiguracoes, 10.0);
        AnchorPane.setRightAnchor(painelConfiguracoes, 10.0);
        AnchorPane.setBottomAnchor(painelConfiguracoes, 10.0);
        getChildren().add(painelConfiguracoes);

        Label rtlNomeSala = new Label("Nome da Sala:");
        rtlNomeSala.setPrefWidth(200);
        rtlNomeSala.setStyle("-fx-text-alignment: right; -fx-alignment: center-right");
        AnchorPane.setTopAnchor(rtlNomeSala, 43.0);
        AnchorPane.setLeftAnchor(rtlNomeSala, 10.0);
        painelConfiguracoes.getChildren().add(rtlNomeSala);

        edtNomeSala = new TextField();
        AnchorPane.setTopAnchor(edtNomeSala, 40.0);
        AnchorPane.setLeftAnchor(edtNomeSala, 215.0);
        AnchorPane.setRightAnchor(edtNomeSala, 10.0);
        painelConfiguracoes.getChildren().add(edtNomeSala);

        Label rtlQuantidadeMaximaJogadores = new Label("Quantidade Máxima Jogadores:");
        rtlQuantidadeMaximaJogadores.setPrefWidth(200);
        rtlQuantidadeMaximaJogadores.setStyle("-fx-text-alignment: right; -fx-alignment: center-right");
        AnchorPane.setTopAnchor(rtlQuantidadeMaximaJogadores, 73.0);
        AnchorPane.setLeftAnchor(rtlQuantidadeMaximaJogadores, 10.0);
        painelConfiguracoes.getChildren().add(rtlQuantidadeMaximaJogadores);

        edtQuantidadeMaximaJogadores = new IntTextField();
        edtQuantidadeMaximaJogadores.setValue(4);
        AnchorPane.setTopAnchor(edtQuantidadeMaximaJogadores, 70.0);
        AnchorPane.setLeftAnchor(edtQuantidadeMaximaJogadores, 215.0);
        AnchorPane.setRightAnchor(edtQuantidadeMaximaJogadores, 10.0);
        painelConfiguracoes.getChildren().add(edtQuantidadeMaximaJogadores);

        Label rtlNumeroRodadas = new Label("Número de Rodadas:");
        rtlNumeroRodadas.setPrefWidth(200);
        rtlNumeroRodadas.setStyle("-fx-text-alignment: right; -fx-alignment: center-right");
        AnchorPane.setTopAnchor(rtlNumeroRodadas, 103.0);
        AnchorPane.setLeftAnchor(rtlNumeroRodadas, 10.0);
        painelConfiguracoes.getChildren().add(rtlNumeroRodadas);

        edtNumeroRodadas = new IntTextField();
        edtNumeroRodadas.setValue(10);
        AnchorPane.setTopAnchor(edtNumeroRodadas, 100.0);
        AnchorPane.setLeftAnchor(edtNumeroRodadas, 215.0);
        AnchorPane.setRightAnchor(edtNumeroRodadas, 10.0);
        painelConfiguracoes.getChildren().add(edtNumeroRodadas);

        Label rtlTempoResponderCadaLetra = new Label("Tempo para responder cada letra (s):");
        rtlTempoResponderCadaLetra.setPrefWidth(200);
        rtlTempoResponderCadaLetra.setStyle("-fx-text-alignment: right; -fx-alignment: center-right");
        AnchorPane.setTopAnchor(rtlTempoResponderCadaLetra, 133.0);
        AnchorPane.setLeftAnchor(rtlTempoResponderCadaLetra, 10.0);
        painelConfiguracoes.getChildren().add(rtlTempoResponderCadaLetra);

        edtTempoResponderCadaLetra = new IntTextField();
        edtTempoResponderCadaLetra.setValue(15);
        AnchorPane.setTopAnchor(edtTempoResponderCadaLetra, 130.0);
        AnchorPane.setLeftAnchor(edtTempoResponderCadaLetra, 215.0);
        AnchorPane.setRightAnchor(edtTempoResponderCadaLetra, 10.0);
        painelConfiguracoes.getChildren().add(edtTempoResponderCadaLetra);
    }

    public void clear() {
        edtNomeSala.clear();
        edtQuantidadeMaximaJogadores.setValue(4);
        edtNumeroRodadas.setValue(10);
        edtTempoResponderCadaLetra.setValue(15);
    }

    private void clicarVoltar(ActionEvent event) {
        form.mudarParaPainelMenu();
        event.consume();
    }

    private void clicarCriarSala(ActionEvent event) {
        form.setSala(getSala());
        new Thread(new CriarSala(new ObservadorNovaSala(), form.getSala())).start();
        event.consume();
    }

    private Sala getSala() {
        Sala sala = new Sala();
        sala.setUsuarioDonoID(form.getUsuario().getId());
        sala.setNome(edtNomeSala.getText());
        sala.setNumeroMaximoUsuario(edtQuantidadeMaximaJogadores.getInt());
        sala.setNumeroTotalRodadas(edtNumeroRodadas.getInt());
        sala.setTempoRespostaLetra(edtTempoResponderCadaLetra.getInt());
        sala.setUsuarios(Collections.singletonList(form.getUsuario()));
        return sala;
    }

    private class ObservadorNovaSala implements Observador<Sala> {
        @Override
        public void sucesso(Sala sala) {
            form.setSala(sala);
            form.mudarParaPainelSala();
        }

        @Override
        public void erro(String mensagem) {
            form.exibirErro(mensagem);
        }
    }
}
