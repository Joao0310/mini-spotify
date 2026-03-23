package com.joao.minispotify.controller;

import com.joao.minispotify.entidades.Artista;
import com.joao.minispotify.service.ArtistaService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/artistas")
public class ArtistaController {
    private ArtistaService service;

    public ArtistaController(ArtistaService service) {
        this.service = service;
    }

    @PostMapping
    public Artista criar(@RequestBody Artista artista) {
        return service.criarArtista(artista);
    }

    @GetMapping
    public List<Artista> listar() {
        return service.listar();
    }

    @GetMapping("/{id}")
    public Artista buscarPorId(@PathVariable Long id) {
        return service.buscarPorId(id);
    }

    @PutMapping("/{id}")
    public Artista atualizar(@PathVariable Long id, @RequestBody Artista artista) {
        return service.atualizar(id, artista);
    }

    @DeleteMapping("/{id}")
    public void deletar(@PathVariable Long id) {
        service.deletar(id);
    }
}
