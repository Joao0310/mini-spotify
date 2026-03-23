package com.joao.minispotify.repository;

import com.joao.minispotify.entidades.Playlist;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public class PlaylistRepository {

    private Map<Long, Playlist> banco = new HashMap<>();
    private Long contador = 1L;

    public Playlist salvar(Playlist playlist) {
        playlist.setId(contador);
        banco.put(contador, playlist);
        contador++;
        return playlist;
    }

    public List<Playlist> listar() {
        return new ArrayList<>(banco.values());
    }

    public Optional<Playlist> buscarPorId(Long id) {
        return Optional.ofNullable(banco.get(id));
    }

    public void deletar(Long id) {
        Playlist playlist = banco.get(id);
        if (playlist != null) {
            playlist.setAtivo(false);
        }
    }
}