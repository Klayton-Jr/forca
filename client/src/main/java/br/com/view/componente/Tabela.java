package br.com.view.componente;

import javafx.scene.control.TableColumn;

public class Tabela<S> extends javafx.scene.control.TableView<S> {

    public void addColumn(ColumnGrid<S, ?> column, double weigth) {
        TableColumn<S, ?> columnFX = (TableColumn<S, ?>) column;
        getColumns().add(columnFX);
        columnFX.prefWidthProperty().bind(widthProperty().multiply(weigth));
    }
}
