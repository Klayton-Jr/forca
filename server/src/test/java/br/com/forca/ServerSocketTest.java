package br.com.forca;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class ServerSocketTest {

    public static void main(String[] args) {

        try (ServerSocket server = new ServerSocket(7070)) {
            System.out.println("Servidor iniciado na porta 7070");

            Socket client = server.accept();
            System.out.println("cliente aceito");

            try (DataOutputStream writer = new DataOutputStream(client.getOutputStream());
                 DataInputStream reader = new DataInputStream(client.getInputStream())) {

                while (true) {
                    String recebido = reader.readUTF();
                    System.out.println("Mensagem enviada pelo cliente: " + recebido);

                    writer.writeUTF("Teste realizado com sucesso!");
                    System.out.println("Enviada a resposta");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
