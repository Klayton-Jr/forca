package servidor.servico;

import org.json.JSONObject;
import servidor.CacheObjetos;
import servidor.model.Usuario;

import java.util.List;
import java.util.UUID;

public class ValidaUsuarioServico implements Servico {

    @Override
    public String executar(JSONObject json) {
        Usuario usuarioLogin = new Usuario(json.optString("id", UUID.randomUUID().toString()), json.getString("nome"));
        CacheObjetos cacheObjetos = CacheObjetos.getInstance();

        List<Usuario> usuarios = cacheObjetos.getUsuarios();

        if (usuarios.contains(usuarioLogin)) {
            usuarios.remove(usuarioLogin);
            usuarios.add(usuarioLogin);

            return new JSONObject().put("resultado", true)
                    .put("usuario", new JSONObject().put("id", usuarioLogin.getId())
                    .put("nome", usuarioLogin.getNome()))
                    .toString();
        }

        for (Usuario usuario : usuarios) {
            if (usuario.getNome().equals(usuarioLogin.getNome()))
                return new JSONObject().put("resultado", false).put("mensagem", "Já existe um usuário com este nome").toString();
        }


        usuarios.add(usuarioLogin);

        return new JSONObject().put("resultado", true)
                .put("usuario", new JSONObject().put("id", usuarioLogin.getId())
                        .put("nome", usuarioLogin.getNome()))
                .toString();
    }
}
