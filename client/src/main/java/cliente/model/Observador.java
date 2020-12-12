package cliente.model;

public interface Observador<T> {

    void sucesso(T t);

    void erro(String mensagem);
}
