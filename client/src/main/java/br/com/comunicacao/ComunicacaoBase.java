package br.com.comunicacao;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public abstract class ComunicacaoBase {

    public static final String HOST = "localhost";
    public static final int PORT = 7070;
    private DataOutputStream writer;
    private DataInputStream reader;
    private Socket client;

    public ComunicacaoBase() {
        inicializar();
    }

    private void inicializar() {
        try {
            client = new Socket(HOST, PORT);
            writer = new DataOutputStream(client.getOutputStream());
            reader = new DataInputStream(client.getInputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public abstract void executar();

    public abstract void callback(String resposta);

    public final void enviarRequisicao(String json) {
        try {
            writer.writeUTF(json);
            callback(reader.readUTF());
        } catch (IOException e) {
            e.printStackTrace();
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
            e.printStackTrace();
        }
    }

}
