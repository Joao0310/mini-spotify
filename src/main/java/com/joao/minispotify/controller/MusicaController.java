package com.joao.minispotify.controller;

import com.joao.minispotify.entidades.Musica;
import com.joao.minispotify.service.MusicaService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/musicas")
public class MusicaController {

    private MusicaService service;

    public MusicaController(MusicaService service) {
        this.service = service;
    }

    @PostMapping
    public Musica criar(@RequestBody Musica musica) {
        return service.criarMusica(musica);
    }

    @GetMapping
    public List<Musica> listar() {
        return service.listar();
    }

    @GetMapping("/{id}")
    public Musica buscarPorId(@PathVariable Long id) {
        return service.buscarPorId(id);
    }

    @PutMapping("/{id}")
    public Musica atualizar(@PathVariable Long id, @RequestBody Musica musica) {
        return service.atualizar(id, musica);
    }

    @DeleteMapping("/{id}")
    public void deletar(@PathVariable Long id) {
        service.deletar(id);
    }

    @PostMapping("/{id}/reproduzir")
    public void reproduzir(
            @PathVariable Long id,
            @RequestHeader("X-USER-ID") Long usuarioId
    ) {
        service.reproduzir(id, usuarioId);
    }
}