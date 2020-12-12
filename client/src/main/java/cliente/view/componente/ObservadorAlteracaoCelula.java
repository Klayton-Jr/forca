package cliente.view.componente;

public interface ObservadorAlteracaoCelula<S, T> {

	void atualizar(String nomeAtributo, S s, T t);

	static <S, T> ObservadorAlteracaoCelula<S, T> criarObservadorPadrao() {
		return new ReflectionObservadorAlteracaoCelula<>();
	}
}
