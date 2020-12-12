package servidor.servico;

import org.json.JSONArray;
import org.json.JSONObject;
import servidor.CacheObjetos;
import servidor.model.Sala;
import servidor.model.Situacao;
import servidor.model.Usuario;

import java.util.List;
import java.util.stream.Collectors;

public class ListarSalasServico implements Servico {

    @Override
    public String executar(JSONObject json) {
        return new JSONObject().put("resultado", true).put("salas", getSalas()).toString();
    }

    private JSONArray getSalas() {
        List<Sala> salas = CacheObjetos.getInstance().getSalas().stream().filter(sala -> Situacao.EM_ESPERA == sala.getSituacao()).collect(Collectors.toList());
        JSONArray salasJSON = new JSONArray();

        for (Sala sala : salas) {
            salasJSON.put(new JSONObject()
                    .put("id", sala.getId())
                    .put("usuarioDonoID", sala.getUsuarioDonoID())
                    .put("nome", sala.getNome())
                    .put("numeroMaximoUsuario", sala.getNumeroMaximoUsuario())
                    .put("numeroAtualUsuario", sala.getNumeroAtualUsuario())
                    .put("numeroTotalRodadas", sala.getNumeroTotalRodadas())
                    .put("numeroAtualRodada", sala.getNumeroAtualRodada())
                    .put("tempoRespostaLetra", sala.getTempoRespostaLetra())
                    .put("situacao", sala.getSituacao().toString())
                    .put("usuarios", getUsuarios(sala.getUsuarios())));
        }
        return salasJSON;
    }

    private JSONArray getUsuarios(List<Usuario> usuarios) {
        JSONArray jsonUsuarios = new JSONArray();

        for (Usuario usuario : usuarios) {
            jsonUsuarios.put(new JSONObject()
                    .put("id", usuario.getId())
                    .put("nome", usuario.getNome()));
        }

        return jsonUsuarios;
    }
}
