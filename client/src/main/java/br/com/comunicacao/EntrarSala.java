package br.com.comunicacao;

import br.com.factory.FabricaObjetos;
import br.com.model.ParametrosTelas;
import br.com.model.Observador;
import br.com.model.Sala;
import org.json.JSONObject;

public class EntrarSala extends ComunicacaoBase<Sala> {

    private final ParametrosTelas parametrosTelas;

    public EntrarSala(Observador<Sala> observador, ParametrosTelas parametrosTelas) {
        super(observador);
        this.parametrosTelas = parametrosTelas;
    }

    @Override
    protected void executar() {
        enviarRequisicao(new JSONObject()
                .put("requisicao", "EntrarSala")
                .put("dados", getJsonDados())
                .toString());
    }

    private JSONObject getJsonDados() {
        return new JSONObject()
                .put("nomeUsuario", parametrosTelas.getUsuario().getNome())
                .put("usuarioID", parametrosTelas.getUsuario().getId())
                .put("nomeSala", parametrosTelas.getSala().getNome())
                .put("salaID", parametrosTelas.getSala().getId());
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
