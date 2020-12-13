package servidor.servico;

import org.json.JSONObject;
import servidor.CacheObjetos;
import servidor.FabricaObjetos;
import servidor.model.*;

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
        Usuario usuarioRequisicao = FabricaObjetos.getUsuarioFromJSON(json);

        List<Sala> salas = CacheObjetos.getInstance().getSalas();
        Sala sala = salas.get(salas.indexOf(salaRequisicao));

        sala.setSituacaoJogo(SituacaoJogo.DESCOBRINDO_PALAVRA);
        sala.setPalavraAtual(json.getString("palavraAtual"));

        for (Usuario usuario : sala.getUsuarios()) {
            if (!usuario.getId().equals(usuarioRequisicao.getId()))
                usuario.setSituacao(SituacaoUsuario.JOGANDO);
            else
                usuario.setSituacao(SituacaoUsuario.AGUARDANDO);
        }

        return enviar(new JSONObject().put("resultado", true));
    }
}
