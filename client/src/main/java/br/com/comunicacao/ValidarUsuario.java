package br.com.comunicacao;

import br.com.factory.FabricaObjetos;
import br.com.model.Observador;
import br.com.model.Sala;
import br.com.model.Usuario;
import org.json.JSONObject;

public class ValidarUsuario extends ComunicacaoBase {

    private final Observador<Usuario> observador;
    private String nome;

    public ValidarUsuario(Observador<Usuario> observador, String nome) {
        this.observador = observador;
        this.nome = nome;
    }

    @Override
    public void executar() {
        enviarRequisicao(new JSONObject()
                .put("requisicao", "validarUsuario")
                .put("nome", nome)
                .toString());
    }

    @Override
    public void callback(String resposta) {
        JSONObject json = new JSONObject(resposta);

        if (json.getBoolean("resultado"))
            observador.atualizar(true, carregarUsuario(json.getJSONObject("sala")));
        else
            observador.atualizar(false, null);
    }

    private Usuario carregarUsuario(JSONObject json) {
        return FabricaObjetos.criarUsuarioDeJson(json);
    }
}
