package com.joao.minispotify.service;

import com.joao.minispotify.entidades.Album;
import com.joao.minispotify.entidades.Artista;
import com.joao.minispotify.repository.AlbumRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AlbumService {

    private final AlbumRepository repository;
    private final ArtistaService artistaService;

    public AlbumService(AlbumRepository repository, ArtistaService artistaService) {
        this.repository = repository;
        this.artistaService = artistaService;
    }

    public Album criarAlbum(Album album) {
        if (album.getTitulo() == null || album.getTitulo().isBlank()) {
            throw new RuntimeException("Título é obrigatório");
        }

        if (album.getArtista() == null || album.getArtista().getId() == null) {
            throw new RuntimeException("Artista é obrigatório");
        }

        Artista artista = artistaService.buscarPorId(album.getArtista().getId());
        album.setArtista(artista);
        album.setAtivo(true);

        return repository.save(album);
    }

    public Album buscarPorId(Long id) {
        Album album = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Álbum não encontrado"));

        if (!album.isAtivo()) {
            throw new RuntimeException("Álbum inativo");
        }

        return album;
    }

    public List<Album> listar() {
        return repository.findByAtivoTrue();
    }

    public void deletar(Long id) {
        Album album = buscarPorId(id);
        album.setAtivo(false);
        repository.save(album);
    }

    public Album atualizar(Long id, Album atualizado) {
        Album album = buscarPorId(id);

        album.setTitulo(atualizado.getTitulo());
        album.setDataLancamento(atualizado.getDataLancamento());

        if (atualizado.getArtista() != null && atualizado.getArtista().getId() != null) {
            Artista artista = artistaService.buscarPorId(atualizado.getArtista().getId());
            album.setArtista(artista);
        }

        return repository.save(album);
    }
}