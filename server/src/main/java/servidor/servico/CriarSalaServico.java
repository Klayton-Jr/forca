package servidor.servico;

import org.json.JSONObject;
import servidor.CacheObjetos;
import servidor.model.Sala;
import servidor.model.Situacao;

import java.util.UUID;

public class CriarSalaServico implements Servico {

    @Override
    public String executar(JSONObject json) {

        CacheObjetos cacheObjetos = CacheObjetos.getInstance();

        Sala sala = new Sala();
        sala.setNome(json.getString("nome"));

        if (cacheObjetos.getSalas().contains(sala))
            return new JSONObject().put("resultado", false).put("mensagem", "Já existe uma sala com este nome").toString();


        sala.setId(UUID.randomUUID().toString());
        sala.setUsuarioDonoID(json.getString("usuarioDonoID"));
        sala.setNumeroMaximoUsuario(json.getInt("numeroMaximoUsuario"));
        sala.setNumeroTotalRodadas(json.getInt("numeroTotalRodadas"));
        sala.setNumeroAtualUsuario(1);
        sala.setTempoRespostaLetra(json.getInt("tempoRespostaLetra"));
        sala.setSituacao(Situacao.EM_ESPERA);

        cacheObjetos.getSalas().add(sala);

        return new JSONObject().put("resultado", true)
                .put("sala", json.put("id", sala.getId())
                    .put("numeroAtualUsuario", 1)
                    .put("numeroAtualRodada", 0)
                    .put("situacao", Situacao.EM_ESPERA.toString())).toString();
    }
}
