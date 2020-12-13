package servidor.servico;

import org.json.JSONArray;
import org.json.JSONObject;
import servidor.CacheObjetos;
import servidor.FabricaObjetos;
import servidor.model.Sala;
import servidor.model.Situacao;
import servidor.model.Usuario;

import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class CriarSalaServico extends Servico {

    public CriarSalaServico(Socket socket, DataOutputStream writer) {
        super(socket, writer);
    }

    @Override
    public boolean executar(JSONObject json) throws IOException {

        CacheObjetos cacheObjetos = CacheObjetos.getInstance();

        Sala sala = new Sala();
        sala.setNome(json.getString("nome"));

        if (cacheObjetos.getSalas().contains(sala))
            return enviar(new JSONObject().put("resultado", false).put("mensagem", "Já existe uma sala com este nome"));


        sala.setId(UUID.randomUUID().toString());
        sala.setUsuarioDonoID(json.getString("usuarioDonoID"));
        sala.setNumeroMaximoUsuario(json.getInt("numeroMaximoUsuario"));
        sala.setNumeroTotalRodadas(json.getInt("numeroTotalRodadas"));
        sala.setNumeroAtualUsuario(1);
        sala.setTempoRespostaLetra(json.getInt("tempoRespostaLetra"));
        sala.setSituacao(Situacao.EM_ESPERA);
        sala.setUsuarios(getUsuarios(json.getJSONArray("usuarios")));

        cacheObjetos.getSalas().add(sala);

        return enviar(new JSONObject().put("resultado", true)
                .put("sala", FabricaObjetos.getSalaJSON(sala)));
    }

    private List<Usuario> getUsuarios(JSONArray jsonUsuarios) {
        List<Usuario> usuarios = new ArrayList<>();

        for (int i = 0; i < jsonUsuarios.length(); i++) {
            JSONObject jsonUsuario = jsonUsuarios.getJSONObject(i);
            usuarios.add(new Usuario(jsonUsuario.getString("id"), jsonUsuario.getString("nome")));
        }

        return usuarios;
    }
}
