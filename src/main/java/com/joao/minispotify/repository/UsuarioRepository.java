package com.joao.minispotify.repository;

import com.joao.minispotify.entidades.Usuario;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public class UsuarioRepository {
    private Map<Long, Usuario> banco = new HashMap<>();
    private Long contador = 1L;

    public Usuario salvar(Usuario usuario) {
        usuario.setId(contador);
        banco.put(contador, usuario);
        contador++;
        return usuario;
    }

    public List<Usuario> listar() {
        return new ArrayList<>(banco.values());
    }

    public Optional<Usuario> buscarPorId(Long id) {
        return Optional.ofNullable(banco.get(id));
    }

    public void deletar(Long id) {
        Usuario usuario = banco.get(id);
        if (usuario != null) {
            usuario.setAtivo(false);
        }
    }
}
