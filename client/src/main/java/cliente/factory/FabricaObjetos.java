package cliente.factory;

import cliente.model.*;
import org.json.JSONArray;
import org.json.JSONObject;

public class FabricaObjetos {

    public static Sala criarSalaDeJSON(JSONObject jsonObject) {
        Sala sala = new Sala();
        sala.setId(jsonObject.getString("id"));
        sala.setUsuarioDonoID(jsonObject.getString("usuarioDonoID"));
        sala.setUsuarioVezID(jsonObject.optString("usuarioVezID"));
        sala.setNome(jsonObject.getString("nome"));
        sala.setNumeroMaximoUsuario(jsonObject.getInt("numeroMaximoUsuario"));
        sala.setNumeroAtualUsuario(jsonObject.getInt("numeroAtualUsuario"));
        sala.setNumeroTotalRodadas(jsonObject.getInt("numeroTotalRodadas"));
        sala.setNumeroAtualRodada(jsonObject.getInt("numeroAtualRodada"));
        sala.setTempoRespostaLetra(jsonObject.getInt("tempoRespostaLetra"));
        sala.setPalavraAtual(jsonObject.optString("palavraAtual"));
        sala.setSituacao(Situacao.valueOf(jsonObject.getString("situacao")));
        sala.setSituacaoJogo(SituacaoJogo.valueOf(jsonObject.getString("situacaoJogo")));

        JSONArray usuariosJSON = jsonObject.optJSONArray("usuarios");

        if (usuariosJSON != null) {
            for (int i = 0; i < usuariosJSON.length(); i++) {
                sala.getUsuarios().add(criarUsuarioDeJSON(usuariosJSON.getJSONObject(i)));
            }
        }
        return sala;
    }

    public static Usuario criarUsuarioDeJSON(JSONObject jsonObject) {
        Usuario usuario = new Usuario(jsonObject.getString("id"), jsonObject.getString("nome"));
        usuario.setPontuacao(jsonObject.optInt("pontuacao"));
        return usuario;
    }

    public static ParametrosTelas criarParametrosDeJSON(JSONObject json) {
        ParametrosTelas parametros = new ParametrosTelas();
        parametros.setSala(criarSalaDeJSON(json.getJSONObject("sala")));
        parametros.setUsuario(criarUsuarioDeJSON(json.getJSONObject("usuario")));
        return parametros;
    }
}
