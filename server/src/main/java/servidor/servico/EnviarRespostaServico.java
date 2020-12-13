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

public class EnviarRespostaServico extends Servico {

    public EnviarRespostaServico(Socket socket, DataOutputStream writer) {
        super(socket, writer);
    }

    @Override
    public boolean executar(JSONObject json) throws IOException {
        Sala salaRequisicao = FabricaObjetos.getSalaFromJSON(json);
        Usuario usuarioRequisicao = FabricaObjetos.getUsuarioFromJSON(json);

        List<Sala> salas = CacheObjetos.getInstance().getSalas();
        Sala sala = salas.get(salas.indexOf(salaRequisicao));
        List<Usuario> usuarios = sala.getUsuarios();

        Usuario usuario = usuarios.get(usuarios.indexOf(usuarioRequisicao));
        usuario.setSituacao(SituacaoUsuario.AGUARDANDO);
        usuario.setPontuacao(usuarioRequisicao.getPontuacao());

        if (usuarios.stream().filter(user -> SituacaoUsuario.JOGANDO == user.getSituacao()).collect(Collectors.toList()).size() == 0) {
            sala.setSituacaoJogo(SituacaoJogo.ESCOLHENDO_PALAVRA);
            sala.setUsuarioVezID(getNovoUsuarioVez(sala));
        }

        return enviar(new JSONObject().put("resultado", true));
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
