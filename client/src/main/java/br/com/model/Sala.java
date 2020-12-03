package br.com.model;

public class Sala {

    private long id;
    private String nome;
    private int numeroMaximoUsuario;
    private int numeroAtualUsuario;
    private int numeroTotalRodadas;
    private int numeroAtualRodada;
    private int tempoRespostaLetra;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
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
}
