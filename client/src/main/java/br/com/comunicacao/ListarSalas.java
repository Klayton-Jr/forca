package br.com.comunicacao;

import br.com.model.Observador;
import br.com.model.Sala;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class ListarSalas extends ComunicacaoBase {

    private Observador<List<Sala>> observador;

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
            observador.atualizar(carregarSalas(json.getJSONArray("dados")));
    }

    private List<Sala> carregarSalas(JSONArray jsonArray) {
        List<Sala> salas = new ArrayList<>();

        for (int i = 0; i < jsonArray.length(); i++) {
            JSONObject jsonObject = jsonArray.getJSONObject(i);

            Sala sala = new Sala();
            sala.setId(jsonObject.getInt("id"));
            sala.setNome(jsonObject.getString("nome"));
            sala.setNumeroMaximoUsuario(jsonObject.getInt("numeroMaximoUsuario"));
            sala.setNumeroAtualUsuario(jsonObject.getInt("numeroAtualUsuario"));
            sala.setNumeroTotalRodadas(jsonObject.getInt("numeroTotalRodadas"));
            sala.setNumeroAtualRodada(jsonObject.getInt("numeroAtualRodada"));
            sala.setTempoRespostaLetra(jsonObject.getInt("tempoRespostaLetra"));
            salas.add(sala);
        }

        return salas;
    }
}
