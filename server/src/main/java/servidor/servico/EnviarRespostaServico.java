package servidor.servico;

import org.json.JSONObject;
import servidor.CacheObjetos;
import servidor.FabricaObjetos;
import servidor.model.*;

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

        Usuario usuarioAtual = usuarios.get(usuarios.indexOf(usuarioRequisicao));
        usuarioAtual.setSituacao(SituacaoUsuario.AGUARDANDO);
        usuarioAtual.setPontuacao(usuarioRequisicao.getPontuacao());

        for (Usuario usuario : usuarios) {
            if (usuario.getId().equals(sala.getUsuarioVezID())) {
                usuario.setPontuacao(usuario.getPontuacao() + getPontuacao(json.getInt("quantidadeErroUsuario"), usuarios.size()));
                break;
            }
        }

        if (usuarios.stream().filter(user -> SituacaoUsuario.JOGANDO == user.getSituacao()).collect(Collectors.toList()).size() == 0) {
            sala.setSituacaoJogo(SituacaoJogo.ESCOLHENDO_PALAVRA);
                sala.setUsuarioVezID(getNovoUsuarioVez(sala));

            if (sala.getNumeroAtualRodada() >= sala.getNumeroTotalRodadas()) {
                sala.setSituacao(Situacao.FINALIZADA);
                sala.setNumeroAtualRodada(0);
            } else {
                sala.setUsuarioVezID(getNovoUsuarioVez(sala));
                sala.setNumeroAtualRodada(sala.getNumeroAtualRodada() + 1);
                sala.setSituacao(sala.getNumeroAtualRodada() >= sala.getNumeroTotalRodadas() ? Situacao.JOGANDO : Situacao.FINALIZADA);
            }
        }


        return enviar(new JSONObject().put("resultado", true));
    }

    private int getPontuacao(int quantidadeErrosUsuario, int quantidadeUsuarios) {
        return quantidadeErrosUsuario * (100 / ((quantidadeUsuarios - 1) * 6));
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
