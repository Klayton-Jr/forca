package br.com.comunicacao;

import org.springframework.messaging.converter.MappingJackson2MessageConverter;
import org.springframework.messaging.simp.stomp.StompSession;
import org.springframework.messaging.simp.stomp.StompSessionHandlerAdapter;
import org.springframework.web.socket.client.WebSocketClient;
import org.springframework.web.socket.client.standard.StandardWebSocketClient;
import org.springframework.web.socket.messaging.WebSocketStompClient;
import org.springframework.web.socket.sockjs.client.SockJsClient;
import org.springframework.web.socket.sockjs.client.WebSocketTransport;

import java.util.Collections;
import java.util.concurrent.ExecutionException;

public abstract class ComunicacaoBase {

    private StompSession session;

    public ComunicacaoBase() {
        inicializar();
    }

    private void inicializar() {
        WebSocketClient webSocketClient = new StandardWebSocketClient();
        SockJsClient sockJsClient = new SockJsClient(Collections.singletonList((new WebSocketTransport(webSocketClient))));
        WebSocketStompClient stompClient = new WebSocketStompClient(sockJsClient);
        stompClient.setMessageConverter(new MappingJackson2MessageConverter());

        try {
            this.session = stompClient.connect("http://localhost:" + 8080 + "/forca-game",
                            new StompSessionHandlerAdapter() {})
                    .get();

        } catch (InterruptedException | ExecutionException e) {
            throw new RuntimeException(e);
        }
    }

    public abstract void executar();

    public abstract void callback(String resposta);

    public final void enviarRequisicao(String json) {
        this.session.send("", json);
    }

    public final void parar() {
        this.session.disconnect();
    }

}
