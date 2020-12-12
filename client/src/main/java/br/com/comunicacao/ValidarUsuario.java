package br.com.comunicacao;

import br.com.factory.FabricaObjetos;
import br.com.model.Observador;
import br.com.model.Usuario;
import org.json.JSONObject;

public class ValidarUsuario extends ComunicacaoBase {

    private final Observador<Usuario> observador;
    private final Usuario usuario;

    public ValidarUsuario(Observador<Usuario> observador, Usuario usuario) {
        this.observador = observador;
        this.usuario = usuario;
    }

    @Override
    public void executar() {
        enviarRequisicao(new JSONObject()
                .put("requisicao", "ValidarUsuario")
                .put("dados", new JSONObject().
                        put("nome", usuario.getNome())
                        .put("id", usuario.getId()))
                .toString());
    }

    @Override
    public void callback(String resposta) {
        JSONObject json = new JSONObject(resposta);

        if (json.getBoolean("resultado"))
            observador.sucesso(carregarUsuario(json.getJSONObject("usuario")));
        else
            observador.erro(json.getString("mensagem"));

        parar();
    }

    private Usuario carregarUsuario(JSONObject json) {
        return FabricaObjetos.criarUsuarioDeJson(json);
    }
}
