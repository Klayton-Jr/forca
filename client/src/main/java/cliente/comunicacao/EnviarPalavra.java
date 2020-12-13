package cliente.comunicacao;

import cliente.model.Observador;
import cliente.model.ParametrosTelas;
import org.json.JSONObject;

public class EnviarPalavra extends ComunicacaoBase<Boolean> {

    private final ParametrosTelas parametros;
    private final String palavraAtual;

    public EnviarPalavra(Observador<Boolean> observador, ParametrosTelas parametros, String palavraAtual) {
        super(observador);
        this.parametros = parametros;
        this.palavraAtual = palavraAtual;
    }

    @Override
    protected void executar() {
        enviarRequisicao(new JSONObject()
                .put("requisicao", "EnviarPalavra")
                .put("dados", new JSONObject()
                        .put("palavraAtual", palavraAtual)
                        .put("salaID", parametros.getSala().getId())
                        .put("nomeSala", parametros.getSala().getNome())
                        .put("usuarioID", parametros.getUsuario().getId())
                        .put("nomeUsuario", parametros.getUsuario().getNome()))
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
