package br.com.view;

import br.com.view.componente.IntTextField;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

public class PainelNovaSala extends AnchorPane {

    private Button btnVoltar;
    private Button btnCriarSala;
    private TextField edtNomeSala;
    private IntTextField edtQuantidadeMaximaJogadores;
    private IntTextField edtNumeroRodadas;
    private IntTextField edtTempoResponderCadaLetra;

    public PainelNovaSala() {
        inicializar();
    }

    private void inicializar() {
        btnVoltar = new Button("Voltar");
        AnchorPane.setTopAnchor(btnVoltar, 10.0);
        AnchorPane.setLeftAnchor(btnVoltar, 10.0);
        getChildren().add(btnVoltar);

        btnCriarSala = new Button("Criar");
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

    public Button getBtnVoltar() {
        return btnVoltar;
    }

    public void clear() {
        edtNomeSala.clear();
        edtQuantidadeMaximaJogadores.setValue(4);
        edtNumeroRodadas.setValue(10);
        edtTempoResponderCadaLetra.setValue(15);
    }
}
