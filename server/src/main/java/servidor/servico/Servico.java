package servidor.servico;

import org.json.JSONObject;

public interface Servico {

    String executar(JSONObject json);
}
