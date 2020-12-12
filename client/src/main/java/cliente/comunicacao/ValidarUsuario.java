package cliente.comunicacao;

import cliente.factory.FabricaObjetos;
import cliente.model.Observador;
import cliente.model.Usuario;
import org.json.JSONObject;

public class ValidarUsuario extends ComunicacaoBase<Usuario> {

    private final Usuario usuario;

    public ValidarUsuario(Observador<Usuario> observador, Usuario usuario) {
        super(observador);
        this.usuario = usuario;
    }

    @Override
    protected void executar() {
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
            sucesso(carregarUsuario(json.getJSONObject("usuario")));
        else
            erro(json.getString("mensagem"));

        parar();
    }

    private Usuario carregarUsuario(JSONObject json) {
        return FabricaObjetos.criarUsuarioDeJSON(json);
    }
}
