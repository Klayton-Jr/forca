package servidor.servico;

import org.json.JSONArray;
import org.json.JSONObject;
import servidor.CacheObjetos;
import servidor.FabricaObjetos;
import servidor.model.Sala;
import servidor.model.Situacao;

import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.List;
import java.util.stream.Collectors;

public class ListarSalasServico extends Servico {

    public ListarSalasServico(Socket socket, DataOutputStream writer) {
        super(socket, writer);
    }

    @Override
    public boolean executar(JSONObject json) throws IOException {
        while(isConnected()) {
            enviar(new JSONObject()
                    .put("resultado", true)
                    .put("salas", getSalas()));
            sleep(5000);
        }

        return true;
    }

    private JSONArray getSalas() {
        List<Sala> salas = CacheObjetos.getInstance().getSalas().stream().filter(sala -> Situacao.EM_ESPERA == sala.getSituacao()).collect(Collectors.toList());
        return FabricaObjetos.getSalasJSON(salas);
    }

}
