package cliente.comunicacao;

import cliente.model.Observador;
import cliente.model.ParametrosTelas;
import org.json.JSONObject;

public class EnviarResposta extends ComunicacaoBase<Boolean> {

    private final ParametrosTelas parametros;
    private final int quantidadeErro;

    public EnviarResposta(Observador<Boolean> observador, ParametrosTelas parametros, int quantidadeErro) {
        super(observador);
        this.parametros = parametros;
        this.quantidadeErro = quantidadeErro;
    }

    @Override
    protected void executar() {
        enviarRequisicao(new JSONObject()
                .put("requisicao", "EnviarResposta")
                .put("dados", new JSONObject()
                        .put("salaID", parametros.getSala().getId())
                        .put("nomeSala", parametros.getSala().getNome())
                        .put("usuarioID", parametros.getUsuario().getId())
                        .put("nomeUsuario", parametros.getUsuario().getNome())
                        .put("pontuacaoUsuario", parametros.getUsuario().getPontuacao())
                        .put("quantidadeErroUsuario", quantidadeErro))
                .toString());
    }

    @Override
    public void callback(String resposta) {
        JSONObject json = new JSONObject(resposta);

        if (json.getBoolean("resultado"))
            sucesso(true);
        else
            erro(json.getString("mensagem"));

        parar();
    }
}
