package cliente.model;

import java.util.Objects;
import java.util.UUID;

public class Usuario {

    private final String id;
    private final String nome;
    private int pontuacao;
    private SituacaoUsuario situacao;

    public Usuario(String nome) {
        this(UUID.randomUUID().toString(), nome);
    }

    public Usuario(String id, String nome) {
        this.id = id;
        this.nome = nome;
        this.situacao = SituacaoUsuario.AGUARDANDO;
    }

    public String getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public int getPontuacao() {
        return pontuacao;
    }

    public void setPontuacao(int pontuacao) {
        this.pontuacao = pontuacao;
    }

    public SituacaoUsuario getSituacao() {
        return situacao;
    }

    public void setSituacao(SituacaoUsuario situacao) {
        this.situacao = situacao;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Usuario usuario = (Usuario) o;
        return Objects.equals(id, usuario.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return nome + " - " + pontuacao + " pontos";
    }
}
