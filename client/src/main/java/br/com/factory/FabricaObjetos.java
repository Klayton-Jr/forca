package br.com.factory;

import br.com.model.Sala;
import br.com.model.Usuario;
import org.json.JSONObject;

public class FabricaObjetos {

    public static Sala criarSalaDeJson(JSONObject jsonObject) {
        Sala sala = new Sala();
        sala.setId(jsonObject.getInt("id"));
        sala.setNome(jsonObject.getString("nome"));
        sala.setNumeroMaximoUsuario(jsonObject.getInt("numeroMaximoUsuario"));
        sala.setNumeroAtualUsuario(jsonObject.getInt("numeroAtualUsuario"));
        sala.setNumeroTotalRodadas(jsonObject.getInt("numeroTotalRodadas"));
        sala.setNumeroAtualRodada(jsonObject.getInt("numeroAtualRodada"));
        sala.setTempoRespostaLetra(jsonObject.getInt("tempoRespostaLetra"));

        return sala;
    }

    public static Usuario criarUsuarioDeJson(JSONObject jsonObject) {
        return new Usuario(jsonObject.getString("id"), jsonObject.getString("nome"));
    }
}
