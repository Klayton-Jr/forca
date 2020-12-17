package servidor;

import java.net.ServerSocket;
import java.net.Socket;

public class Servidor {

    public static final int PORT = 7070;

    public void start() {
        try (ServerSocket server = new ServerSocket(PORT)) {
            System.out.println("Servidor iniciado na porta: " + PORT);

            while (true) {
                Socket accept = server.accept();
                System.out.println("Novo Cliente conectado: " + accept.getRemoteSocketAddress());
                new Thread(new Cliente(accept)).start();
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            reiniciarServidor();
        }
    }

    private void reiniciarServidor() {
        try {
            Thread.sleep(10000);
        } catch (InterruptedException interruptedException) {
            interruptedException.printStackTrace();
        } finally {
            start();
        }
    }
}
