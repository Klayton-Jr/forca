package servidor.servico;

import org.json.JSONObject;
import servidor.CacheObjetos;
import servidor.model.Sala;
import servidor.model.Situacao;

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
        Sala salaRequisicao = new Sala();
        salaRequisicao.setId(json.getString("salaID"));
        salaRequisicao.setNome(json.getString("nomeSala"));

        List<Sala> salas = CacheObjetos.getInstance().getSalas();
        Sala sala = salas.get(salas.indexOf(salaRequisicao));

        sala.setSituacao(Situacao.JOGANDO);

        return enviar(new JSONObject().put("resultado", true));
    }
}
