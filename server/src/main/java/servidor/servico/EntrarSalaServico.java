package servidor.servico;

import org.json.JSONObject;
import servidor.CacheObjetos;
import servidor.FabricaObjetos;
import servidor.model.Sala;
import servidor.model.Situacao;
import servidor.model.Usuario;

import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.List;

public class EntrarSalaServico extends Servico {


    public EntrarSalaServico(Socket socket, DataOutputStream writer) {
        super(socket, writer);
    }

    @Override
    public boolean executar(JSONObject json) throws IOException {
        Sala salaRequisicao = FabricaObjetos.getSalaFromJSON(json);
        Usuario usuarioRequisicao = FabricaObjetos.getUsuarioFromJSON(json);

        CacheObjetos cacheObjetos = CacheObjetos.getInstance();
        List<Sala> salas = cacheObjetos.getSalas();

        int index = salas.indexOf(salaRequisicao);

        Sala sala = salas.get(index);

        if (sala.getNumeroAtualUsuario() >= sala.getNumeroMaximoUsuario())
            return enviar(new JSONObject().put("resultado", false).put("mensagem", "Não é possível entrar na sala.\nSala cheia."));
        if (Situacao.EM_ESPERA != sala.getSituacao())
            return enviar(new JSONObject().put("resultado", false).put("mensagem", "Não é possível entrar na sala.\nJogo já em andamento."));

        sala.getUsuarios().add(usuarioRequisicao);
        sala.setNumeroAtualUsuario(sala.getUsuarios().size());

        enviar(new JSONObject().put("resultado", true).put("sala", FabricaObjetos.getSalaJSON(sala)));

        return true;
    }
}
