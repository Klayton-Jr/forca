package servidor;

import org.json.JSONObject;
import servidor.servico.CriarSalaServico;
import servidor.servico.ListarSalasServico;
import servidor.servico.Servico;
import servidor.servico.ValidaUsuarioServico;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.Socket;

public class Cliente implements Runnable {

    private final Socket socket;

    public Cliente(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {
        try (DataOutputStream writer = new DataOutputStream(socket.getOutputStream());
             DataInputStream reader = new DataInputStream(socket.getInputStream())) {

            while(socket.isConnected()) {
                JSONObject json = new JSONObject(reader.readUTF());
                String requisicao = json.getString("requisicao");

                System.out.println("Requisição solicitada: " + requisicao);

                Servico servico = null;

                if ("ValidarUsuario".equalsIgnoreCase(requisicao)) {
                    servico = new ValidaUsuarioServico();
                } else if ("CriarSala".equalsIgnoreCase(requisicao)) {
                    servico = new CriarSalaServico();
                } else if ("ListarSalas".equalsIgnoreCase(requisicao)) {
                    servico = new ListarSalasServico();
                }

                if (servico != null) {
                    writer.writeUTF(servico.executar(json.getJSONObject("dados")));
                } else {
                    writer.writeUTF(new JSONObject().put("resultado", false).put("erro", "Não foi possível localizar requisição solicitada").toString());
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
