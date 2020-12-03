package br.com.model;

public interface Observador<T> {

    void atualizar(boolean resultado, T t);
}
