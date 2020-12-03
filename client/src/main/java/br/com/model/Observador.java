package br.com.model;

public interface Observador<T> {

    void atualizar(T t);
}
