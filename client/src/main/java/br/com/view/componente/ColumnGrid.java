package br.com.view.componente;

import javafx.scene.control.TableColumn;
import javafx.scene.control.cell.PropertyValueFactory;

public class ColumnGrid<S, T> extends TableColumn<S, T> {

	private final EditCellReflection<S, T> editCellCommit;
	private final String attributeName;

	public ColumnGrid(String name) {
		this(name, name);
	}
	
	public ColumnGrid(String titulo, String attributeName) {
		super(titulo);
		this.attributeName = attributeName;
		this.editCellCommit = new EditCellReflection<>(attributeName);		
		init();
	}

	private void init() {
		setOnEditCommit(editCellCommit);
		editCellCommit.setNomeAtributo(attributeName);
		setCellValueFactory(new PropertyValueFactory<>(attributeName));
	}
}
