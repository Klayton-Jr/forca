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

        Sala sala = salas.get(index);
        sala.getUsuarios().add(usuarioRequisicao);
        sala.setNumeroAtualUsuario(sala.getUsuarios().size());

        return enviar(new JSONObject().put("resultado", true).put("sala", FabricaObjetos.getSalaJSON(sala)));
    }
}
