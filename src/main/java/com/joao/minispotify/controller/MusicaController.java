package com.joao.minispotify.controller;

import com.joao.minispotify.entidades.Musica;
import com.joao.minispotify.service.MusicaService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/musicas")
public class MusicaController {

    private final MusicaService service;

    public MusicaController(MusicaService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<Musica> criar(@RequestBody Musica musica) {
        return ResponseEntity.status(201).body(service.criarMusica(musica));
    }

    @GetMapping
    public ResponseEntity<List<Musica>> listar() {
        return ResponseEntity.ok(service.listar());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Musica> buscarPorId(@PathVariable Long id) {
        return ResponseEntity.ok(service.buscarPorId(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Musica> atualizar(@PathVariable Long id, @RequestBody Musica musica) {
        return ResponseEntity.ok(service.atualizar(id, musica));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        service.deletar(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/{id}/reproduzir")
    public ResponseEntity<Void> reproduzir(
            @PathVariable Long id,
            @RequestHeader("X-USER-ID") Long usuarioId
    ) {
        service.reproduzir(id, usuarioId);
        return ResponseEntity.noContent().build();
    }
}