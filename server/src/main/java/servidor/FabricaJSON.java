package servidor;

import org.json.JSONArray;
import org.json.JSONObject;
import servidor.model.Sala;
import servidor.model.Usuario;

import java.util.List;

public class FabricaJSON {

    private FabricaJSON() {}

    public static JSONArray getSalas(List<Sala> salas) {
        JSONArray salasJSON = new JSONArray();

        for (Sala sala : salas) {
            salasJSON.put(FabricaJSON.getSala(sala));
        }

        return salasJSON;
    }

    public static JSONObject getSala(Sala sala) {
        return new JSONObject()
                .put("id", sala.getId())
                .put("usuarioDonoID", sala.getUsuarioDonoID())
                .put("nome", sala.getNome())
                .put("numeroMaximoUsuario", sala.getNumeroMaximoUsuario())
                .put("numeroAtualUsuario", sala.getNumeroAtualUsuario())
                .put("numeroTotalRodadas", sala.getNumeroTotalRodadas())
                .put("numeroAtualRodada", sala.getNumeroAtualRodada())
                .put("tempoRespostaLetra", sala.getTempoRespostaLetra())
                .put("situacao", sala.getSituacao().toString())
                .put("usuarios", getUsuarios(sala.getUsuarios()));
    }

    private static JSONArray getUsuarios(List<Usuario> usuarios) {
        JSONArray jsonUsuarios = new JSONArray();

        for (Usuario usuario : usuarios) {
            jsonUsuarios.put(getUsuario(usuario));
        }

        return jsonUsuarios;
    }

    public static JSONObject getUsuario(Usuario usuario) {
        return new JSONObject()
                .put("id", usuario.getId())
                .put("nome", usuario.getNome());
    }
}
