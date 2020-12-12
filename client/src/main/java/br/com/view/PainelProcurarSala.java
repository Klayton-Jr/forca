package br.com.view;

import br.com.comunicacao.EntrarSala;
import br.com.comunicacao.ListarSalas;
import br.com.model.LoginSala;
import br.com.model.Observador;
import br.com.model.Sala;
import br.com.view.componente.ColumnGrid;
import br.com.view.componente.Tabela;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.AnchorPane;

import java.util.List;

public class PainelProcurarSala extends AnchorPane {

    private Tabela<Sala> tabelaSalas;
    private final Formulario form;
    private ListarSalas listarSalas;

    public PainelProcurarSala(Formulario form) {
        this.form = form;
        inicializar();
    }

    private void inicializar() {
        Button btnVoltar = new Button("Voltar");
        btnVoltar.setOnAction(this::clicarVoltar);
        AnchorPane.setTopAnchor(btnVoltar, 10.0);
        AnchorPane.setLeftAnchor(btnVoltar, 10.0);
        getChildren().add(btnVoltar);

        Button btnEntrar = new Button("Entrar");
        btnEntrar.setOnAction(this::clicarEntrarSala);
        AnchorPane.setTopAnchor(btnEntrar, 10.0);
        AnchorPane.setRightAnchor(btnEntrar, 10.0);
        getChildren().add(btnEntrar);

        tabelaSalas = new Tabela<>();
        tabelaSalas.addColumn(new ColumnGrid<>("ID", "id"), 0.1);
        tabelaSalas.addColumn(new ColumnGrid<>("Nome", "nome"), 0.4);
        tabelaSalas.addColumn(new ColumnGrid<>("Usuários na Sala", "numeroAtualUsuario"), 0.23);
        tabelaSalas.addColumn(new ColumnGrid<>("Número Máximo de Usuário", "numeroMaximoUsuario"), 0.23);

        AnchorPane.setTopAnchor(tabelaSalas, 50.0);
        AnchorPane.setLeftAnchor(tabelaSalas, 10.0);
        AnchorPane.setRightAnchor(tabelaSalas, 10.0);
        AnchorPane.setBottomAnchor(tabelaSalas, 10.0);
        getChildren().add(tabelaSalas);
    }

    public void atualizar() {
        listarSalas = new ListarSalas(new ObservadorCriarSalas());
        new Thread(listarSalas).start();
    }

    private void clicarVoltar(ActionEvent event) {
        listarSalas.parar();
        form.mudarParaPainelMenu();
        event.consume();
    }

    private void clicarEntrarSala(ActionEvent event) {
        new Thread(new EntrarSala(new ObservadorEntrarSala(), getLoginSala())).start();
        event.consume();
    }

    private LoginSala getLoginSala() {
        LoginSala loginSala = new LoginSala();
        loginSala.setSalaID(form.getSala().getId());
        loginSala.setUsuarioID(form.getUsuario().getId());
        return loginSala;
    }

    private class ObservadorCriarSalas implements Observador<List<Sala>> {

        @Override
        public void sucesso(List<Sala> salas) {
            tabelaSalas.getItems().clear();
            tabelaSalas.getItems().addAll(salas);
        }

        @Override
        public void erro(String mensagem) {
            form.exibirErro(mensagem);
        }
    }

    private class ObservadorEntrarSala implements Observador<Sala> {
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
