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

        if (sala.getNumeroAtualUsuario() <= 1)
            return enviar(new JSONObject().put("resultado", false).put("mensagem", "Número minimo de jogadores é 2."));

        sala.setSituacao(Situacao.JOGANDO);
        sala.setSituacaoJogo(SituacaoJogo.ESCOLHENDO_PALAVRA);
        sala.setUsuarioVezID(sala.getUsuarioDonoID());
        sala.setNumeroAtualRodada(1);

        for (Usuario usuario : sala.getUsuarios()) {
            usuario.setPontuacao(0);
            usuario.setSituacao(SituacaoUsuario.AGUARDANDO);
        }

        atualizarSala(sala);

        enviar(new JSONObject().put("resultado", true));
        return true;
    }


}
