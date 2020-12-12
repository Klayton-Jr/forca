package br.com.forca;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.Socket;

public class ClienteTeste {

    public static void main(String[] args) {

        try (Socket client = new Socket("localhost", 7070)) {

            try (DataOutputStream writer = new DataOutputStream(client.getOutputStream());
                    DataInputStream reader = new DataInputStream(client.getInputStream())) {

                while (true) {
                    writer.writeUTF("mensagem para o servidor");

                    String resposta = reader.readUTF();

                    System.out.println("Resposta Servidor: " + resposta);

                    Thread.sleep(2000);
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
