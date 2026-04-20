package com.joao.minispotify.controller;

import com.joao.minispotify.entidades.Playlist;
import com.joao.minispotify.service.PlaylistService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/playlists")
public class PlaylistController {

    private final PlaylistService service;

    public PlaylistController(PlaylistService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<Playlist> criar(@RequestBody Playlist playlist) {
        return ResponseEntity.status(201).body(service.criar(playlist));
    }

    @GetMapping
    public ResponseEntity<List<Playlist>> listar() {
        return ResponseEntity.ok(service.listar());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Playlist> buscarPorId(@PathVariable Long id) {
        return ResponseEntity.ok(service.buscarPorId(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Playlist> atualizar(@PathVariable Long id, @RequestBody Playlist playlist) {
        return ResponseEntity.ok(service.atualizar(id, playlist));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        service.deletar(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/{playlistId}/musicas/{musicaId}")
    public ResponseEntity<Void> adicionarMusica(
            @PathVariable Long playlistId,
            @PathVariable Long musicaId,
            @RequestHeader("X-USER-ID") Long usuarioId
    ) {
        service.adicionarMusica(playlistId, musicaId, usuarioId);
        return ResponseEntity.noContent().build();
    }
}