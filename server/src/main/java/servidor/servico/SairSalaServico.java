package servidor.servico;

import org.json.JSONObject;
import servidor.CacheObjetos;
import servidor.FabricaObjetos;
import servidor.model.Sala;
import servidor.model.SituacaoJogo;
import servidor.model.SituacaoUsuario;
import servidor.model.Usuario;

import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.List;
import java.util.stream.Collectors;

public class SairSalaServico extends Servico {


    public SairSalaServico(Socket socket, DataOutputStream writer) {
        super(socket, writer);
    }

    @Override
    public boolean executar(JSONObject json) throws IOException {
        Sala salaRequisicao = FabricaObjetos.getSalaFromJSON(json);
        Usuario usuarioRequisicao = FabricaObjetos.getUsuarioFromJSON(json);

        List<Sala> salas = CacheObjetos.getInstance().getSalas();

        int index = salas.indexOf(salaRequisicao);

        Sala sala = salas.get(index);
        List<Usuario> usuarios = sala.getUsuarios();
        Usuario usuarioSaindo = usuarios.get(usuarios.indexOf(usuarioRequisicao));

        fecharSocket(usuarioSaindo.getSocket());
        usuarioSaindo.setSocket(null);

        usuarios.remove(usuarioSaindo);
        sala.setNumeroAtualUsuario(usuarios.size());

        if (usuarios.stream().filter(user -> SituacaoUsuario.JOGANDO == user.getSituacao()).collect(Collectors.toList()).size() == 0 && usuarioSaindo.getSituacao() == SituacaoUsuario.JOGANDO) {
            sala.setSituacaoJogo(SituacaoJogo.ESCOLHENDO_PALAVRA);
            sala.setUsuarioVezID(getNovoUsuarioVez(sala));
        }

        atualizarSala(sala);

        enviar(new JSONObject().put("resultado", true));

        return true;
    }

    private String getNovoUsuarioVez(Sala sala) {
        String usuarioVezID = sala.getUsuarioVezID();
        Usuario usuario = new Usuario(usuarioVezID, "");
        List<Usuario> usuarios = sala.getUsuarios();

        int index = usuarios.indexOf(usuario);
        index = index >= usuarios.size() - 1 ? 0 : index + 1;

        return usuarios.get(index).getId();
    }
}
