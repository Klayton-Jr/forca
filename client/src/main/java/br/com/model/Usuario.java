package br.com.model;

public final class Usuario {

    private final String id;
    private final String nome;

    public Usuario(String id, String nome) {
        this.id = id;
        this.nome = nome;
    }

    public Usuario(String nome) {
        this(null, nome);
    }

    public String getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }
}
