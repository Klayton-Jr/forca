package servidor;

import servidor.model.Sala;
import servidor.model.Usuario;

import java.net.Socket;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public final class CacheObjetos {

    private static final CacheObjetos cacheObjetos = new CacheObjetos();
    private final List<Usuario> usuarios;
    private final List<Sala> salas;

    private CacheObjetos() {
        this.usuarios = new ArrayList<>();
        this.salas = new ArrayList<>();
    }

    public static CacheObjetos getInstance() {
        return cacheObjetos;
    }

    public List<Usuario> getUsuarios() {
        return usuarios;
    }

    public List<Sala> getSalas() {
        return salas;
    }
}
