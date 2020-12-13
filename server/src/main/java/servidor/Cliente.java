package servidor;

import org.json.JSONObject;
import servidor.servico.*;

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

            JSONObject json = new JSONObject(reader.readUTF());
            String requisicao = json.getString("requisicao");

            System.out.println("Requisição solicitada: " + requisicao);

            Servico servico = null;

            if ("ValidarUsuario".equalsIgnoreCase(requisicao))
                servico = new ValidaUsuarioServico(socket, writer);
            else if ("CriarSala".equalsIgnoreCase(requisicao))
                servico = new CriarSalaServico(socket, writer);
            else if ("ListarSalas".equalsIgnoreCase(requisicao))
                servico = new ListarSalasServico(socket, writer);
            else if ("EntrarSala".equalsIgnoreCase(requisicao))
                servico = new EntrarSalaServico(socket, writer);
            else if ("CarregarSala".equalsIgnoreCase(requisicao))
                servico = new CarregarSalaServico(socket, writer);
            else if ("IniciarJogo".equalsIgnoreCase(requisicao))
                servico = new IniciarJogoServico(socket, writer);
            else if ("EnviarPalavra".equalsIgnoreCase(requisicao))
                servico = new EnviarPalavraServico(socket, writer);
            else if ("EnviarResposta".equalsIgnoreCase(requisicao))
                servico = new EnviarRespostaServico(socket, writer);
            else if ("SairSala".equalsIgnoreCase(requisicao))
                servico = new SairSalaServico(socket, writer);

            if (servico != null)
                servico.executar(json.getJSONObject("dados"));
            else
                writer.writeUTF(new JSONObject().put("resultado", false).put("erro", "Não foi possível localizar requisição solicitada").toString());

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
