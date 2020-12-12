package cliente.comunicacao;

import cliente.factory.FabricaObjetos;
import cliente.model.Observador;
import cliente.model.Sala;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class ListarSalas extends ComunicacaoBase<List<Sala>> {

    public ListarSalas(Observador<List<Sala>> observador) {
        super(observador);
    }

    @Override
    protected void executar() {
        enviarRequisicao(new JSONObject()
                .put("requisicao", "ListarSalas")
                .put("dados", new JSONObject())
                .toString());
    }

    @Override
    public void callback(String resposta) {
        JSONObject json = new JSONObject(resposta);

        if (json.getBoolean("resultado"))
            sucesso(carregarSalas(json.getJSONArray("salas")));
        else
            erro(json.getString("mensagem"));

        reexecutar();
    }

    private void reexecutar() {
        try {
            Thread.sleep(5000);
            executar();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private List<Sala> carregarSalas(JSONArray jsonArray) {
        List<Sala> salas = new ArrayList<>();

        for (int i = 0; i < jsonArray.length(); i++) {
            salas.add(FabricaObjetos.criarSalaDeJSON(jsonArray.getJSONObject(i)));
        }

        return salas;
    }
}
