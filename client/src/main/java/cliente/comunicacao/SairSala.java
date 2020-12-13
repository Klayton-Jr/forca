package cliente.comunicacao;

import cliente.factory.FabricaObjetos;
import cliente.model.Observador;
import cliente.model.ParametrosTelas;
import cliente.model.Sala;
import org.json.JSONObject;

public class SairSala extends ComunicacaoBase<Boolean> {

    private final ParametrosTelas parametrosTelas;

    public SairSala(Observador<Boolean> observador, ParametrosTelas parametrosTelas) {
        super(observador);
        this.parametrosTelas = parametrosTelas;
    }

    @Override
    protected void executar() {
        enviarRequisicao(new JSONObject()
                .put("requisicao", "SairSala")
                .put("dados", getJsonDados())
                .toString());
    }

    private JSONObject getJsonDados() {
        return new JSONObject()
                .put("nomeUsuario", parametrosTelas.getUsuario().getNome())
                .put("usuarioID", parametrosTelas.getUsuario().getId())
                .put("nomeSala", parametrosTelas.getSala().getNome())
                .put("salaID", parametrosTelas.getSala().getId());
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
