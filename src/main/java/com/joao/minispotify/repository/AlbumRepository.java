package com.joao.minispotify.repository;

import com.joao.minispotify.entidades.Album;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public class AlbumRepository {

    private Map<Long, Album> banco = new HashMap<>();
    private Long contador = 1L;

    public Album salvar(Album album) {
        album.setId(contador);
        banco.put(contador, album);
        contador++;
        return album;
    }

    public List<Album> listar() {
        return new ArrayList<>(banco.values());
    }

    public Optional<Album> buscarPorId(Long id) {
        return Optional.ofNullable(banco.get(id));
    }

    public void deletar(Long id) {
        Album album = banco.get(id);
        if (album != null) {
            album.setAtivo(false);
        }
    }
}