package com.joao.minispotify.repository;

import com.joao.minispotify.entidades.Artista;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public class ArtistaRepository {
    private Map<Long, Artista> banco = new HashMap<>();
    private Long contador = 1L;

    public Artista salvar(Artista artista) {
        artista.setId(contador);
        banco.put(contador, artista);
        contador++;
        return artista;
    }

    public List<Artista> listar() {
        return new ArrayList<>(banco.values());
    }
    public Optional<Artista> buscarPorId(Long id) {
        return Optional.ofNullable(banco.get(id));
    }

    public void deletar(Long id) {
        Artista artista = banco.get(id);
        if (artista != null) {
            artista.setAtivo(false);
        }
    }
}
