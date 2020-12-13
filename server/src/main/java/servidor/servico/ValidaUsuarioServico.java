package servidor.servico;

import org.json.JSONObject;
import servidor.CacheObjetos;
import servidor.FabricaObjetos;
import servidor.model.Usuario;

import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.List;
import java.util.UUID;

public class ValidaUsuarioServico extends Servico {

    public ValidaUsuarioServico(Socket socket, DataOutputStream writer) {
        super(socket, writer);
    }

    @Override
    public boolean executar(JSONObject json) throws IOException {
        Usuario usuarioLogin = new Usuario(json.optString("id", UUID.randomUUID().toString()), json.getString("nome"));
        CacheObjetos cacheObjetos = CacheObjetos.getInstance();

        List<Usuario> usuarios = cacheObjetos.getUsuarios();

        if (usuarios.contains(usuarioLogin)) {
            usuarios.remove(usuarioLogin);
            usuarios.add(usuarioLogin);

            return enviar(new JSONObject().put("resultado", true)
                    .put("usuario", new JSONObject().put("id", usuarioLogin.getId())
                    .put("nome", usuarioLogin.getNome())));
        }

        for (Usuario usuario : usuarios) {
            if (usuario.getNome().equals(usuarioLogin.getNome()))
                return enviar(new JSONObject().put("resultado", false).put("mensagem", "Já existe um usuário com este nome"));
        }


        usuarios.add(usuarioLogin);

        return enviar(new JSONObject().put("resultado", true)
                .put("usuario", FabricaObjetos.getUsuarioJSON(usuarioLogin)));
    }
}
