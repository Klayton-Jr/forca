package br.com.comunicacao;

import br.com.model.Observador;
import javafx.application.Platform;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public abstract class ComunicacaoBase<T> implements Runnable {

    public static final String HOST = "localhost";
    public static final int PORT = 7070;
    private final Observador<T> observador;
    private DataOutputStream writer;
    private DataInputStream reader;
    private Socket client;

    public ComunicacaoBase(Observador<T> observador) {
        this.observador = observador;
        inicializar();
    }

    private void inicializar() {
        try {
            client = new Socket(HOST, PORT);
            writer = new DataOutputStream(client.getOutputStream());
            reader = new DataInputStream(client.getInputStream());
        } catch (IOException e) {
            erro(e.getMessage());
        }
    }

    @Override
    public void run() {
        executar();
    }

    protected abstract void executar();

    public abstract void callback(String resposta);

    public final void enviarRequisicao(String json) {
        try {
            writer.writeUTF(json);
            callback(reader.readUTF());
        } catch (IOException e) {
            erro(e.getMessage());
        }
    }

    public final void parar() {
        try {
            if (writer != null)
                writer.close();

            if (reader != null)
                reader.close();

            if (client != null && !client.isClosed())
                client.close();
        } catch (IOException e) {
            erro(e.getMessage());
        }
    }

    protected final void sucesso(T t) {
        Platform.runLater(()-> observador.sucesso(t));
    }

    protected final void erro(String mensagem) {
        observador.erro(mensagem);
    }

}
