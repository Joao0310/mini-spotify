package com.joao.minispotify.controller;

import com.joao.minispotify.entidades.Album;
import com.joao.minispotify.service.AlbumService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/albuns")
public class AlbumController {

    private final AlbumService service;

    public AlbumController(AlbumService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<Album> criar(@RequestBody Album album) {
        return ResponseEntity.status(201).body(service.criarAlbum(album));
    }

    @GetMapping
    public ResponseEntity<List<Album>> listar() {
        return ResponseEntity.ok(service.listar());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Album> buscarPorId(@PathVariable Long id) {
        return ResponseEntity.ok(service.buscarPorId(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Album> atualizar(@PathVariable Long id, @RequestBody Album album) {
        return ResponseEntity.ok(service.atualizar(id, album));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        service.deletar(id);
        return ResponseEntity.noContent().build();
    }
}