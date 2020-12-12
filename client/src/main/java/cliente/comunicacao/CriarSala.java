package cliente.comunicacao;

import cliente.factory.FabricaObjetos;
import cliente.model.Observador;
import cliente.model.Sala;
import cliente.model.Usuario;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.List;

public class CriarSala extends ComunicacaoBase<Sala> {

    private final Sala sala;

    public CriarSala(Observador<Sala> observador, Sala sala) {
        super(observador);
        this.sala = sala;
    }

    @Override
    protected void executar() {
        enviarRequisicao(new JSONObject()
                .put("requisicao", "CriarSala")
                .put("dados", getSalaJSON())
                .toString());
    }

    private JSONObject getSalaJSON() {
        return new JSONObject().put("nome", sala.getNome())
                .put("usuarioDonoID", sala.getUsuarioDonoID())
                .put("numeroMaximoUsuario", sala.getNumeroMaximoUsuario())
                .put("numeroTotalRodadas", sala.getNumeroTotalRodadas())
                .put("tempoRespostaLetra", sala.getTempoRespostaLetra())
                .put("usuarios", getUsuarios(sala.getUsuarios()));
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

    @Override
    public void callback(String resposta) {
        JSONObject json = new JSONObject(resposta);

        if (json.getBoolean("resultado"))
            sucesso(carregarSala(json.getJSONObject("sala")));
        else
            erro(json.getString("mensagem"));
        parar();
    }

    private Sala carregarSala(JSONObject jsonSala) {
        return FabricaObjetos.criarSalaDeJSON(jsonSala);
    }
}
