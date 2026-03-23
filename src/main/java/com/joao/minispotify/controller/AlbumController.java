package com.joao.minispotify.controller;

import com.joao.minispotify.entidades.Album;
import com.joao.minispotify.service.AlbumService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/albuns")
public class AlbumController {

    private AlbumService service;

    public AlbumController(AlbumService service) {
        this.service = service;
    }

    @PostMapping
    public Album criar(@RequestBody Album album) {
        return service.criarAlbum(album);
    }

    @GetMapping
    public List<Album> listar() {
        return service.listar();
    }

    @GetMapping("/{id}")
    public Album buscarPorId(@PathVariable Long id) {
        return service.buscarPorId(id);
    }

    @PutMapping("/{id}")
    public Album atualizar(@PathVariable Long id, @RequestBody Album album) {
        return service.atualizar(id, album);
    }

    @DeleteMapping("/{id}")
    public void deletar(@PathVariable Long id) {
        service.deletar(id);
    }
}