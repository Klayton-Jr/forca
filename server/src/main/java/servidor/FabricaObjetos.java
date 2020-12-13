package servidor;

import org.json.JSONArray;
import org.json.JSONObject;
import servidor.model.Sala;
import servidor.model.Usuario;

import java.util.List;

public class FabricaObjetos {

    private FabricaObjetos() {}

    public static JSONArray getSalasJSON(List<Sala> salas) {
        JSONArray salasJSON = new JSONArray();

        for (Sala sala : salas) {
            salasJSON.put(FabricaObjetos.getSalaJSON(sala));
        }

        return salasJSON;
    }

    public static JSONObject getSalaJSON(Sala sala) {
        return new JSONObject()
                .put("id", sala.getId())
                .put("usuarioDonoID", sala.getUsuarioDonoID())
                .put("usuarioVezID", sala.getUsuarioVezID())
                .put("nome", sala.getNome())
                .put("numeroMaximoUsuario", sala.getNumeroMaximoUsuario())
                .put("numeroAtualUsuario", sala.getNumeroAtualUsuario())
                .put("numeroTotalRodadas", sala.getNumeroTotalRodadas())
                .put("numeroAtualRodada", sala.getNumeroAtualRodada())
                .put("tempoRespostaLetra", sala.getTempoRespostaLetra())
                .put("palavraAtual", sala.getPalavraAtual())
                .put("situacao", sala.getSituacao().toString())
                .put("situacaoJogo", sala.getSituacaoJogo().toString())
                .put("usuarios", getUsuariosJSON(sala.getUsuarios()));
    }

    private static JSONArray getUsuariosJSON(List<Usuario> usuarios) {
        JSONArray jsonUsuarios = new JSONArray();

        for (Usuario usuario : usuarios) {
            jsonUsuarios.put(getUsuarioJSON(usuario));
        }

        return jsonUsuarios;
    }

    public static JSONObject getUsuarioJSON(Usuario usuario) {
        return new JSONObject()
                .put("id", usuario.getId())
                .put("nome", usuario.getNome())
                .put("pontuacao", usuario.getPontuacao());
    }

    public static Sala getSalaFromJSON(JSONObject json) {
        Sala sala = new Sala();
        sala.setId(json.getString("salaID"));
        sala.setNome(json.getString("nomeSala"));
        return sala;
    }

    public static Usuario getUsuarioFromJSON(JSONObject json) {
        return new Usuario(json.getString("usuarioID"), json.getString("nomeUsuario"));
    }
}
