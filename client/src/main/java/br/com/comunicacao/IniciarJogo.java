package br.com.comunicacao;

import br.com.factory.FabricaObjetos;
import br.com.model.Observador;
import br.com.model.ParametrosTelas;
import br.com.model.Sala;
import org.json.JSONObject;

public class IniciarJogo extends ComunicacaoBase<Boolean> {

    private final ParametrosTelas parametros;

    public IniciarJogo(Observador<Boolean> observador, ParametrosTelas parametros) {
        super(observador);
        this.parametros = parametros;
    }

    @Override
    protected void executar() {
        enviarRequisicao(new JSONObject()
                .put("requisicao", "IniciarJogo")
                .put("dados", new JSONObject()
                        .put("salaID", parametros.getSala().getId())
                        .put("nomeSala", parametros.getSala().getNome())
                        .put("usuarioID", parametros.getUsuario().getId())
                        .put("nomeUsuario", parametros.getUsuario().getNome()))
                .toString());
    }

    @Override
    public void callback(String resposta) {
        JSONObject json = new JSONObject(resposta);

        if (json.getBoolean("resultado")) {
            sucesso(json.getBoolean("resultado"));
        } else {
            erro(json.getString("mensagem"));
        }
    }
}
