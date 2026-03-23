package com.joao.minispotify.controller;

import com.joao.minispotify.entidades.Playlist;
import com.joao.minispotify.service.PlaylistService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/playlists")
public class PlaylistController {

    private PlaylistService service;

    public PlaylistController(PlaylistService service) {
        this.service = service;
    }

    @PostMapping
    public Playlist criar(@RequestBody Playlist playlist) {
        return service.criar(playlist);
    }

    @GetMapping
    public List<Playlist> listar() {
        return service.listar();
    }

    @GetMapping("/{id}")
    public Playlist buscarPorId(@PathVariable Long id) {
        return service.buscarPorId(id);
    }

    @PutMapping("/{id}")
    public Playlist atualizar(@PathVariable Long id, @RequestBody Playlist playlist) {
        return service.atualizar(id, playlist);
    }

    @DeleteMapping("/{id}")
    public void deletar(@PathVariable Long id) {
        service.deletar(id);
    }

    @PostMapping("/{playlistId}/musicas/{musicaId}")
    public void adicionarMusica(
            @PathVariable Long playlistId,
            @PathVariable Long musicaId,
            @RequestHeader("X-USER-ID") Long usuarioId
    ) {
        service.adicionarMusica(playlistId, musicaId, usuarioId);
    }
}