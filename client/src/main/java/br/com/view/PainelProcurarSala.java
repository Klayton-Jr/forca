package br.com.view;

import br.com.model.Sala;
import br.com.view.componente.ColumnGrid;
import br.com.view.componente.Tabela;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.AnchorPane;

public class PainelProcurarSala extends AnchorPane {

    private Button btnVoltar;
    private Button btnEntrar;
    private Tabela<Sala> tabelaSalas;

    public PainelProcurarSala() {
        inicializar();
    }

    private void inicializar() {
        btnVoltar = new Button("Voltar");
        AnchorPane.setTopAnchor(btnVoltar, 10.0);
        AnchorPane.setLeftAnchor(btnVoltar, 10.0);
        getChildren().add(btnVoltar);

        btnEntrar = new Button("Entrar");
        AnchorPane.setTopAnchor(btnEntrar, 10.0);
        AnchorPane.setRightAnchor(btnEntrar, 10.0);
        getChildren().add(btnEntrar);

        tabelaSalas = new Tabela<>();
        tabelaSalas.addColumn(new ColumnGrid<>("ID", "id"), 0.1);
        tabelaSalas.addColumn(new ColumnGrid<>("Nome", "nome"),0.4);
        tabelaSalas.addColumn(new ColumnGrid<>("Usuários na Sala", "numeroAtualUsuario"), 0.23);
        tabelaSalas.addColumn(new ColumnGrid<>("Número Máximo de Usuário", "numeroMaximoUsuario"), 0.23);

        AnchorPane.setTopAnchor(tabelaSalas, 50.0);
        AnchorPane.setLeftAnchor(tabelaSalas, 10.0);
        AnchorPane.setRightAnchor(tabelaSalas, 10.0);
        AnchorPane.setBottomAnchor(tabelaSalas, 10.0);
        getChildren().add(tabelaSalas);
    }

    public Button getBtnVoltar() {
        return btnVoltar;
    }

    public void atualizar() {

    }
}
