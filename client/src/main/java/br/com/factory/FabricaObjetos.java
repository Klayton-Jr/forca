package br.com.factory;

import br.com.model.Sala;
import br.com.model.Situacao;
import br.com.model.Usuario;
import org.json.JSONArray;
import org.json.JSONObject;

public class FabricaObjetos {

    public static Sala criarSalaDeJson(JSONObject jsonObject) {
        Sala sala = new Sala();
        sala.setId(jsonObject.getString("id"));
        sala.setNome(jsonObject.getString("nome"));
        sala.setNumeroMaximoUsuario(jsonObject.getInt("numeroMaximoUsuario"));
        sala.setNumeroAtualUsuario(jsonObject.getInt("numeroAtualUsuario"));
        sala.setNumeroTotalRodadas(jsonObject.getInt("numeroTotalRodadas"));
        sala.setNumeroAtualRodada(jsonObject.getInt("numeroAtualRodada"));
        sala.setTempoRespostaLetra(jsonObject.getInt("tempoRespostaLetra"));
        sala.setSituacao(Situacao.valueOf(jsonObject.getString("situacao")));

        JSONArray usuariosJSON = jsonObject.optJSONArray("usuarios");

        if (usuariosJSON != null) {
            for (int i = 0; i < usuariosJSON.length(); i++) {
                sala.getUsuarios().add(criarUsuarioDeJson(usuariosJSON.getJSONObject(i)));
            }
        }
        return sala;
    }

    public static Usuario criarUsuarioDeJson(JSONObject jsonObject) {
        return new Usuario(jsonObject.getString("id"), jsonObject.getString("nome"));
    }
}
