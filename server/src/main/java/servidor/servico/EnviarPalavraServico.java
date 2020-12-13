package servidor.servico;

import org.json.JSONObject;
import servidor.CacheObjetos;
import servidor.FabricaObjetos;
import servidor.model.Sala;
import servidor.model.Situacao;
import servidor.model.SituacaoJogo;

import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.List;

public class EnviarPalavraServico extends Servico {

    public EnviarPalavraServico(Socket socket, DataOutputStream writer) {
        super(socket, writer);
    }

    @Override
    public boolean executar(JSONObject json) throws IOException {
        Sala salaRequisicao = FabricaObjetos.getSalaFromJSON(json);

        List<Sala> salas = CacheObjetos.getInstance().getSalas();
        Sala sala = salas.get(salas.indexOf(salaRequisicao));

        sala.setSituacaoJogo(SituacaoJogo.DESCOBRINDO_PALAVRA);
        sala.setPalavraAtual(json.getString("palavraAtual"));

        return enviar(new JSONObject().put("resultado", true));
    }
}
