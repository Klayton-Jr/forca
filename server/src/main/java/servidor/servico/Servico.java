package servidor.servico;

import org.json.JSONObject;
import servidor.FabricaObjetos;
import servidor.model.Sala;
import servidor.model.Usuario;

import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public abstract class Servico {

    private final static ExecutorService EXECUTOR = Executors.newSingleThreadExecutor();
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

    public final Socket getSocket() {
        return socket;
    }

    public final DataOutputStream getWriter() {
        return writer;
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

    protected final void fecharSocket() {
        fecharSocket(getSocket());
    }

    protected final void fecharSocket(Socket socket) {
        try {
            if (!socket.isClosed())
                socket.close();
        } catch (IOException e) {

        }
    }

    protected final void atualizarSala(Sala sala) {
        EXECUTOR.execute(() -> {
            JSONObject jsonParametro = new JSONObject();
            jsonParametro.put("sala", FabricaObjetos.getSalaJSON(sala));

            JSONObject atualizacao = new JSONObject()
                    .put("resultado", true)
                    .put("parametros", jsonParametro);

            List<Usuario> usuarios = sala.getUsuarios();
            ArrayList<Usuario> usuariosRemovidos = new ArrayList<>();

            for (Usuario usuario : usuarios) {
                if (usuario.getSocket() != null && usuario.getSocket().isClosed()) {
                    System.out.println("Usuário " + usuario.getNome() + " removido!");
                    usuariosRemovidos.add(usuario);
                }
            }

            usuarios.removeAll(usuariosRemovidos);

            for (Usuario usuario : usuarios) {
                jsonParametro.put("usuario", FabricaObjetos.getUsuarioJSON(usuario));

                System.out.println("Enviando Atualização para o Usuário " + usuario.getNome());
                try {
                    usuario.getWriter().writeUTF(atualizacao.toString());
                } catch (IOException e) {

                }
            }
        });
    }

}