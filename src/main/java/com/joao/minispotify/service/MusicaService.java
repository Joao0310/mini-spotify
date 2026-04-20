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

    private final MusicaRepository repository;
    private final AlbumService albumService;
    private final ArtistaService artistaService;
    private final UsuarioService usuarioService;

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
        if (musica.getTitulo() == null || musica.getTitulo().isBlank()) {
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

        musica.setAlbum(album);
        musica.setArtista(artista);
        musica.setAtivo(true);
        if (musica.getTotalReproducoes() == null) {
            musica.setTotalReproducoes(0L);
        }

        return repository.save(musica);
    }

    public Musica buscarPorId(Long id) {
        Musica musica = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Música não encontrada"));

        if (!musica.isAtivo()) {
            throw new RuntimeException("Música inativa");
        }

        return musica;
    }

    public List<Musica> listar() {
        return repository.findByAtivoTrue();
    }

    public void deletar(Long id) {
        Musica musica = buscarPorId(id);
        musica.setAtivo(false);
        repository.save(musica);
    }

    public Musica atualizar(Long id, Musica atualizada) {
        Musica musica = buscarPorId(id);

        musica.setTitulo(atualizada.getTitulo());
        musica.setDuracaoSegundos(atualizada.getDuracaoSegundos());
        musica.setNumeroFaixa(atualizada.getNumeroFaixa());

        if (atualizada.getAlbum() != null && atualizada.getAlbum().getId() != null) {
            Album album = albumService.buscarPorId(atualizada.getAlbum().getId());
            musica.setAlbum(album);
        }

        if (atualizada.getArtista() != null && atualizada.getArtista().getId() != null) {
            Artista artista = artistaService.buscarPorId(atualizada.getArtista().getId());
            musica.setArtista(artista);
        }

        return repository.save(musica);
    }

    public void reproduzir(Long musicaId, Long usuarioId) {
        Musica musica = buscarPorId(musicaId);
        Usuario usuario = usuarioService.buscarPorId(usuarioId);

        if (!usuario.isAtivo()) {
            throw new RuntimeException("Usuário inativo");
        }

        musica.incrementarReproducoes();
        repository.save(musica);
    }
}