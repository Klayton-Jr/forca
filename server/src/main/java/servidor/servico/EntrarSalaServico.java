package servidor.servico;

import org.json.JSONObject;
import servidor.CacheObjetos;
import servidor.FabricaObjetos;
import servidor.model.Sala;
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

        List<Sala> salas = CacheObjetos.getInstance().getSalas();

        int index = salas.indexOf(salaRequisicao);

        if (index == -1)
            return enviar(new JSONObject().put("resultado", false).put("mensagem", "Não foi possível localizar a sala selecionada"));

        Sala salaDesejada = salas.get(index);
        salaDesejada.setNumeroAtualUsuario(salaDesejada.getNumeroAtualUsuario() + 1);
        salaDesejada.getUsuarios().add(usuarioRequisicao);

        return enviar(new JSONObject().put("resultado", true).put("sala", FabricaObjetos.getSalaJSON(salaDesejada)));
    }
}
