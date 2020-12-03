package br.com.comunicacao;

import br.com.factory.FabricaObjetos;
import br.com.model.Observador;
import br.com.model.Sala;
import org.json.JSONObject;

public class CriarSala extends ComunicacaoBase {

    private final Observador<Sala> observador;
    private final Sala sala;

    public CriarSala(Observador<Sala> observador, Sala sala) {
        this.observador = observador;
        this.sala = sala;
    }

    @Override
    public void executar() {
        enviarRequisicao(new JSONObject()
                .put("requisicao", "criarSala")
                .put("sala", getSalaJSON())
                .toString());
    }

    private JSONObject getSalaJSON() {
        return new JSONObject().put("nome", sala.getNome())
                .put("numeroMaximoUsuario", sala.getNumeroMaximoUsuario())
                .put("numeroTotalRodadas", sala.getNumeroTotalRodadas())
                .put("tempoRespostaLetra", sala.getTempoRespostaLetra());
    }

    @Override
    public void callback(String resposta) {
        JSONObject json = new JSONObject(resposta);

        if (json.getBoolean("resultado"))
            observador.atualizar(true, carregarSala(json.getJSONObject("sala")));
        else
            observador.atualizar(false, null);
    }

    private Sala carregarSala(JSONObject jsonSala) {
        return FabricaObjetos.criarSalaDeJson(jsonSala);
    }
}
