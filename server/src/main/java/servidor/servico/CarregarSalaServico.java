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

public class CarregarSalaServico extends Servico {

    public CarregarSalaServico(Socket socket, DataOutputStream writer) {
        super(socket, writer);
    }

    @Override
    public boolean executar(JSONObject json) throws IOException {
        Sala salaRequisicao = FabricaObjetos.getSalaFromJSON(json);
        Usuario usuarioRequisicao = FabricaObjetos.getUsuarioFromJSON(json);

        while (isConnected()) {
            enviar(new JSONObject()
                    .put("resultado", true)
                    .put("parametros", getParametros(salaRequisicao, usuarioRequisicao)));
            sleep(2000);
        }

        return true;
    }

    private JSONObject getParametros(Sala salaRequisicao, Usuario usuarioRequisicao) {
        CacheObjetos cacheObjetos = CacheObjetos.getInstance();

        List<Sala> salas = cacheObjetos.getSalas();
        Sala sala = salas.get(salas.indexOf(salaRequisicao));
        Usuario usuario = sala.getUsuarios().get(sala.getUsuarios().indexOf(usuarioRequisicao));

        return new JSONObject()
                .put("usuario", FabricaObjetos.getUsuarioJSON(usuario))
                .put("sala", FabricaObjetos.getSalaJSON(sala));
    }

}
