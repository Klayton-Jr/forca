package br.com.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Sala {

    private String id;
    private String usuarioDonoID;
    private String nome;
    private int numeroMaximoUsuario;
    private int numeroAtualUsuario;
    private int numeroTotalRodadas;
    private int numeroAtualRodada;
    private int tempoRespostaLetra;
    private Situacao situacao;
    private final List<Usuario> usuarios = new ArrayList<>();

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUsuarioDonoID() {
        return usuarioDonoID;
    }

    public void setUsuarioDonoID(String usuarioDonoID) {
        this.usuarioDonoID = usuarioDonoID;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getNumeroMaximoUsuario() {
        return numeroMaximoUsuario;
    }

    public void setNumeroMaximoUsuario(int numeroMaximoUsuario) {
        this.numeroMaximoUsuario = numeroMaximoUsuario;
    }

    public int getNumeroAtualUsuario() {
        return numeroAtualUsuario;
    }

    public void setNumeroAtualUsuario(int numeroAtualUsuario) {
        this.numeroAtualUsuario = numeroAtualUsuario;
    }

    public int getNumeroTotalRodadas() {
        return numeroTotalRodadas;
    }

    public void setNumeroTotalRodadas(int numeroTotalRodadas) {
        this.numeroTotalRodadas = numeroTotalRodadas;
    }

    public int getNumeroAtualRodada() {
        return numeroAtualRodada;
    }

    public void setNumeroAtualRodada(int numeroAtualRodada) {
        this.numeroAtualRodada = numeroAtualRodada;
    }

    public int getTempoRespostaLetra() {
        return tempoRespostaLetra;
    }

    public void setTempoRespostaLetra(int tempoRespostaLetra) {
        this.tempoRespostaLetra = tempoRespostaLetra;
    }

    public Situacao getSituacao() {
        return situacao;
    }

    public void setSituacao(Situacao situacao) {
        this.situacao = situacao;
    }

    public List<Usuario> getUsuarios() {
        return usuarios;
    }

    public void setUsuarios(List<Usuario> usuarios) {
        this.usuarios.clear();
        this.usuarios.addAll(usuarios);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Sala sala = (Sala) o;
        return Objects.equals(nome, sala.nome);
    }

    @Override
    public int hashCode() {
        return Objects.hash(nome);
    }
}
