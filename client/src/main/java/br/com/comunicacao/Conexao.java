package br.com.comunicacao;

import org.springframework.messaging.converter.MappingJackson2MessageConverter;
import org.springframework.messaging.simp.stomp.StompSession;
import org.springframework.messaging.simp.stomp.StompSessionHandler;
import org.springframework.messaging.simp.stomp.StompSessionHandlerAdapter;
import org.springframework.web.socket.client.WebSocketClient;
import org.springframework.web.socket.client.standard.StandardWebSocketClient;
import org.springframework.web.socket.messaging.WebSocketStompClient;
import org.springframework.web.socket.sockjs.client.SockJsClient;
import org.springframework.web.socket.sockjs.client.WebSocketTransport;

import java.util.Collections;
import java.util.concurrent.ExecutionException;


public class Conexao {

    private StompSession session;

    public StompSession getSession() {
        if (session == null)
            executar();
        return session;
    }

    public void executar() {
        WebSocketClient webSocketClient = new StandardWebSocketClient();
        SockJsClient sockJsClient = new SockJsClient(Collections.singletonList((new WebSocketTransport(webSocketClient))));
        WebSocketStompClient stompClient = new WebSocketStompClient(sockJsClient);
        stompClient.setMessageConverter(new MappingJackson2MessageConverter());

        StompSession session;
        try {
            session = stompClient
                    .connect(
                            "http://localhost:" + 8080 + "/forca-game",
                            new StompSessionHandlerAdapter() {})
                    .get();

        } catch (InterruptedException | ExecutionException e) {
            throw new RuntimeException(e);
        }

    }

    public void enviarRequisicao(String json) {

        this.session.send("", json);
    }

    public void parar() {
        this.session.disconnect();
    }
}
