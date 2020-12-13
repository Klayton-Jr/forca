package servidor.servico;

import org.json.JSONObject;
import servidor.CacheObjetos;
import servidor.FabricaObjetos;
import servidor.model.*;

import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.List;

public class IniciarJogoServico extends Servico {

    public IniciarJogoServico(Socket socket, DataOutputStream writer) {
        super(socket, writer);
    }

    @Override
    public boolean executar(JSONObject json) throws IOException {
        Sala salaRequisicao = FabricaObjetos.getSalaFromJSON(json);

        List<Sala> salas = CacheObjetos.getInstance().getSalas();
        Sala sala = salas.get(salas.indexOf(salaRequisicao));

        sala.setSituacao(Situacao.JOGANDO);
        sala.setSituacaoJogo(SituacaoJogo.ESCOLHENDO_PALAVRA);
        sala.setUsuarioVezID(sala.getUsuarioDonoID());

        return enviar(new JSONObject().put("resultado", true));
    }


}
