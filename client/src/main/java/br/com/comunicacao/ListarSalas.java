package br.com.comunicacao;

import br.com.factory.FabricaObjetos;
import br.com.model.Observador;
import br.com.model.Sala;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class ListarSalas extends ComunicacaoBase {

    private final Observador<List<Sala>> observador;

    public ListarSalas(Observador<List<Sala>> observador) {
        this.observador = observador;
    }

    @Override
    public void executar() {
        enviarRequisicao(new JSONObject()
                .put("requisicao", "listarSalas")
                .toString());
    }

    @Override
    public void callback(String resposta) {
        JSONObject json = new JSONObject(resposta);

        if (json.getBoolean("resultado"))
            observador.sucesso(carregarSalas(json.getJSONArray("salas")));
        else
            observador.sucesso(null);
    }

    private List<Sala> carregarSalas(JSONArray jsonArray) {
        List<Sala> salas = new ArrayList<>();

        for (int i = 0; i < jsonArray.length(); i++) {
            salas.add(FabricaObjetos.criarSalaDeJson(jsonArray.getJSONObject(i)));
        }

        return salas;
    }
}
