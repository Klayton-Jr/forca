package br.com.comunicacao;

public abstract class ComunicacaoBase {

    public abstract void executar();

    public abstract void callback(String resposta);

    public void enviarRequisicao(String json) {

    }

    public void parar() {

    }
}
