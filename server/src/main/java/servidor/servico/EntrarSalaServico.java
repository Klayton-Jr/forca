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

public class EntrarSalaServico extends Servico {


    public EntrarSalaServico(Socket socket, DataOutputStream writer) {
        super(socket, writer);
    }

    @Override
    public boolean executar(JSONObject json) throws IOException {

        Sala sala = new Sala();
        sala.setId(json.getString("salaID"));
        sala.setNome(json.getString("nomeSala"));

        Usuario usuario = new Usuario(json.getString("usuarioID"), json.getString("nomeUsuario"));

        List<Sala> salas = CacheObjetos.getInstance().getSalas();

        int index = salas.indexOf(sala);

        if (index == -1)
            return enviar(new JSONObject().put("resultado", false).put("mensagem", "Não foi possível localizar a sala selecionada"));

        Sala salaDesejada = salas.get(index);
        salaDesejada.setNumeroAtualUsuario(salaDesejada.getNumeroAtualUsuario() + 1);
        salaDesejada.getUsuarios().add(usuario);

        return enviar(new JSONObject().put("resultado", true).put("sala", FabricaJSON.getSala(salaDesejada)));
    }
}
