package servidor.servico;

import org.json.JSONObject;
import servidor.CacheObjetos;
import servidor.FabricaJSON;
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

        Usuario usuario = new Usuario(json.getString("usuarioID"), json.getString("nomeUsuario"));

        Sala sala = new Sala();
        sala.setId(json.getString("salaID"));
        sala.setNome(json.getString("nomeSala"));

        while (isConnected()) {
            enviar(new JSONObject()
                    .put("resultado", true)
                    .put("parametros", getParametros(sala, usuario)));
            sleep(5000);
        }

        return true;
    }

    private JSONObject getParametros(Sala salaRequisicao, Usuario usuarioRequisicao) {
        CacheObjetos cacheObjetos = CacheObjetos.getInstance();

        List<Sala> salas = cacheObjetos.getSalas();
        Sala sala = salas.get(salas.indexOf(salaRequisicao));
        Usuario usuario = sala.getUsuarios().get(sala.getUsuarios().indexOf(usuarioRequisicao));

        return new JSONObject()
                .put("usuario", FabricaJSON.getUsuario(usuario))
                .put("sala", FabricaJSON.getSala(sala));
    }

}
