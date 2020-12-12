package br.com.comunicacao;

import br.com.factory.FabricaObjetos;
import br.com.model.LoginSala;
import br.com.model.Observador;
import br.com.model.Sala;
import org.json.JSONObject;

public class EntrarSala extends ComunicacaoBase {

    private final Observador<Sala> observador;
    private LoginSala loginSala;

    public EntrarSala(Observador<Sala> observador, LoginSala loginSala) {
        this.observador = observador;
        this.loginSala = loginSala;
    }

    @Override
    public void executar() {
        enviarRequisicao(new JSONObject()
                .put("requisicao", "entrarSala")
                .put("dados", getJsonDados())
                .toString());
    }

    private JSONObject getJsonDados() {
        return new JSONObject()
                .put("usuarioID", loginSala.getUsuarioID())
                .put("salaID", loginSala.getSalaID());
    }

    @Override
    public void callback(String resposta) {
        JSONObject json = new JSONObject(resposta);

        if (json.getBoolean("resultado"))
            observador.sucesso(carregarSala(json.getJSONObject("sala")));
        else
            observador.sucesso(null);
    }

    private Sala carregarSala(JSONObject jsonSala) {
        return FabricaObjetos.criarSalaDeJson(jsonSala);
    }
}
