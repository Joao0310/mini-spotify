package com.joao.minispotify.service;

import com.joao.minispotify.entidades.Album;
import com.joao.minispotify.entidades.Artista;
import com.joao.minispotify.entidades.Musica;
import com.joao.minispotify.entidades.Usuario;
import com.joao.minispotify.repository.MusicaRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MusicaService {

    private MusicaRepository repository;
    private AlbumService albumService;
    private ArtistaService artistaService;
    private UsuarioService usuarioService;

    public MusicaService(MusicaRepository repository,
                         AlbumService albumService,
                         ArtistaService artistaService,
                         UsuarioService usuarioService) {
        this.repository = repository;
        this.albumService = albumService;
        this.artistaService = artistaService;
        this.usuarioService = usuarioService;
    }

    public Musica criarMusica(Musica musica) {

        if (musica.getTitulo() == null || musica.getTitulo().isEmpty()) {
            throw new RuntimeException("Título é obrigatório");
        }

        if (musica.getAlbum() == null || musica.getAlbum().getId() == null) {
            throw new RuntimeException("Álbum é obrigatório");
        }

        if (musica.getArtista() == null || musica.getArtista().getId() == null) {
            throw new RuntimeException("Artista é obrigatório");
        }

        Album album = albumService.buscarPorId(musica.getAlbum().getId());
        Artista artista = artistaService.buscarPorId(musica.getArtista().getId());

        if (!album.isAtivo()) {
            throw new RuntimeException("Álbum inativo");
        }

        if (!artista.isAtivo()) {
            throw new RuntimeException("Artista inativo");
        }

        musica.setAlbum(album);
        musica.setArtista(artista);

        return repository.salvar(musica);
    }

    public Musica buscarPorId(Long id) {
        Musica musica = repository.buscarPorId(id)
                .orElseThrow(() -> new RuntimeException("Música não encontrada"));

        if (!musica.isAtivo()) {
            throw new RuntimeException("Música inativa");
        }

        return musica;
    }

    public List<Musica> listar() {
        return repository.listar()
                .stream()
                .filter(Musica::isAtivo)
                .toList();
    }

    public void deletar(Long id) {
        buscarPorId(id);
        repository.deletar(id);
    }

    public Musica atualizar(Long id, Musica atualizada) {

        Musica musica = buscarPorId(id);

        musica.setTitulo(atualizada.getTitulo());
        musica.setDuracaoSegundos(atualizada.getDuracaoSegundos());
        musica.setNumeroFaixa(atualizada.getNumeroFaixa());

        // opcional: atualizar album e artista (nível mais avançado)
        if (atualizada.getAlbum() != null && atualizada.getAlbum().getId() != null) {
            Album album = albumService.buscarPorId(atualizada.getAlbum().getId());

            if (!album.isAtivo()) {
                throw new RuntimeException("Álbum inativo");
            }

            musica.setAlbum(album);
        }

        if (atualizada.getArtista() != null && atualizada.getArtista().getId() != null) {
            Artista artista = artistaService.buscarPorId(atualizada.getArtista().getId());

            if (!artista.isAtivo()) {
                throw new RuntimeException("Artista inativo");
            }

            musica.setArtista(artista);
        }

        return musica;
    }

    public void reproduzir(Long musicaId, Long usuarioId) {

        Musica musica = buscarPorId(musicaId);
        Usuario usuario = usuarioService.buscarPorId(usuarioId);

        if (!usuario.isAtivo()) {
            throw new RuntimeException("Usuário inativo");
        }

        musica.incrementarReproducoes();
    }

}