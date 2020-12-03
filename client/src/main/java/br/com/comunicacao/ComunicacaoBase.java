package br.com.comunicacao;

import org.json.JSONObject;

public abstract class ComunicacaoBase {

    public abstract void executar();

    public abstract void callback(String resposta);

    public void enviarRequisicao(String json) {

    }
}
