package cliente.comunicacao;

import cliente.factory.FabricaObjetos;
import cliente.model.ParametrosTelas;
import cliente.model.Observador;
import org.json.JSONObject;

public class InscreverBroadcast extends ComunicacaoBase<ParametrosTelas> {

    private final ParametrosTelas parametros;

    public InscreverBroadcast(Observador<ParametrosTelas> observador, ParametrosTelas parametros) {
        super(observador);
        this.parametros = parametros;
    }

    @Override
    protected void executar() {
        enviarRequisicao(new JSONObject()
                .put("requisicao", "InscreverBroadcast")
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
            sucesso(getParametros(json.getJSONObject("parametros")));
        } else {
            erro(json.getString("mensagem"));
        }
    }

    private ParametrosTelas getParametros(JSONObject parametros) {
        return FabricaObjetos.criarParametrosDeJSON(parametros);
    }
}
