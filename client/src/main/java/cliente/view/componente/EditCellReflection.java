package cliente.view.componente;

import javafx.event.EventHandler;
import javafx.scene.control.TableColumn.CellEditEvent;

public class EditCellReflection<S, T> implements EventHandler<CellEditEvent<S, T>> {

	private ObservadorAlteracaoCelula<S, T> observador;
	private String nomeAtributo;

	public EditCellReflection(String nomeAtributo) {
		this.nomeAtributo = nomeAtributo;
		this.observador = ObservadorAlteracaoCelula.criarObservadorPadrao();
	}

	@Override
	public void handle(CellEditEvent<S, T> event) {
		int index = event.getTablePosition().getRow();
		S s = event.getTableView().getItems().get(index);
		observador.atualizar(nomeAtributo, s, event.getNewValue());		
	}

	public void setNomeAtributo(String nomeAtributo) {
		this.nomeAtributo = nomeAtributo;
	}

	public void setObservador(ObservadorAlteracaoCelula<S, T> observador) {
		this.observador = observador;
	}
}
