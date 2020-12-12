package cliente.comunicacao;

import cliente.model.Observador;
import cliente.model.ParametrosTelas;
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
