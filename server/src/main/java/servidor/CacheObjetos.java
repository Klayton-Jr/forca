package servidor;

import servidor.model.Usuario;

import java.util.ArrayList;
import java.util.List;

public final class CacheObjetos {

    private static final CacheObjetos cacheObjetos = new CacheObjetos();
    private final List<Usuario> usuarios;

    private CacheObjetos() {
        this.usuarios = new ArrayList<>();
    }

    public static synchronized CacheObjetos getInstance() {
        return cacheObjetos;
    }

    public List<Usuario> getUsuarios() {
        return usuarios;
    }
}
