package servidor.servico;

import org.json.JSONObject;

import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public abstract class Servico {

    private final Socket socket;
    private final DataOutputStream writer;

    public Servico(Socket socket, DataOutputStream writer) {
        this.socket = socket;
        this.writer = writer;
    }

    public abstract boolean executar(JSONObject json) throws IOException;

    protected final boolean enviar(JSONObject json) throws IOException {
        writer.writeUTF(json.toString());
        return true;
    }

    protected final boolean isConnected() {
        return socket.isConnected();
    }

    protected final void sleep(long timeMillis) {
        try {
            Thread.sleep(timeMillis);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}