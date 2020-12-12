package cliente.view.componente;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

public class ReflectionObservadorAlteracaoCelula<S, T> implements ObservadorAlteracaoCelula<S, T> {

	private Map<Class<?>, Method> methods = new HashMap<>();

	@Override
	public void atualizar(String nomeAtributo, S s, T t) {
		Method method = methods.get(s.getClass());
		
		try {
			
			if (method == null) {
				method = criarMetodoSet(nomeAtributo, s, t);
				methods.put(s.getClass(), method);
			}
			
			method.invoke(s, t);
		} catch (Exception e) {
			throw new RuntimeException(String.format("Não possível executar o método %s do objeto %s", method.getName(), s.getClass().getCanonicalName()));
		}
	}

	private Method criarMetodoSet(String nomeAtributo, S s, T t) {
		try {
			return s.getClass().getMethod("set" + nomeAtributo.substring(0, 1).toUpperCase() + nomeAtributo.substring(1),
					t.getClass());
		} catch (NoSuchMethodException | SecurityException e) {
			throw new RuntimeException(String.format("Método set do atributo %s não encontrado para o objeto %s", nomeAtributo, s.getClass().getCanonicalName()));
		}
	}
}
