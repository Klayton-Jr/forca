package br.com.comunicacao;

import br.com.factory.FabricaObjetos;
import br.com.model.Observador;
import br.com.model.Sala;
import org.json.JSONObject;

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
                .put("tempoRespostaLetra", sala.getTempoRespostaLetra());
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
        return FabricaObjetos.criarSalaDeJson(jsonSala);
    }
}
