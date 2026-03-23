package com.joao.minispotify.service;

import com.joao.minispotify.entidades.Album;
import com.joao.minispotify.entidades.Artista;
import com.joao.minispotify.repository.AlbumRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AlbumService {

    private AlbumRepository repository;
    private ArtistaService artistaService;

    public AlbumService(AlbumRepository repository, ArtistaService artistaService) {
        this.repository = repository;
        this.artistaService = artistaService;
    }

    public Album criarAlbum(Album album) {

        if (album.getTitulo() == null || album.getTitulo().isEmpty()) {
            throw new RuntimeException("Título é obrigatório");
        }
        Long artistaId = album.getArtista().getId();

        Artista artista = artistaService.buscarPorId(artistaId);

        if (!artista.isAtivo()) {
            throw new RuntimeException("Artista inativo");
        }

        album.setArtista(artista);
        return repository.salvar(album);
    }

    public Album buscarPorId(Long id) {
        Album album = repository.buscarPorId(id)
                .orElseThrow(() -> new RuntimeException("Álbum não encontrado"));

        if (!album.isAtivo()) {
            throw new RuntimeException("Álbum inativo");
        }

        return album;
    }

    public List<Album> listar() {
        return repository.listar()
                .stream()
                .filter(Album::isAtivo)
                .toList();
    }

    public void deletar(Long id) {
        buscarPorId(id); // valida
        repository.deletar(id);
    }

    public Album atualizar(Long id, Album atualizado) {
        Album album = buscarPorId(id);

        album.setTitulo(atualizado.getTitulo());
        album.setDataLancamento(atualizado.getDataLancamento());

        return album;
    }
}
