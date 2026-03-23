package com.joao.minispotify.service;

import com.joao.minispotify.entidades.Musica;
import com.joao.minispotify.entidades.Playlist;
import com.joao.minispotify.entidades.Usuario;
import com.joao.minispotify.repository.PlaylistRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PlaylistService {

    private PlaylistRepository repository;
    private UsuarioService usuarioService;
    private MusicaService musicaService;

    public PlaylistService(PlaylistRepository repository,
                           UsuarioService usuarioService,
                           MusicaService musicaService) {
        this.repository = repository;
        this.usuarioService = usuarioService;
        this.musicaService = musicaService;
    }

    public Playlist criar(Playlist playlist) {

        if (playlist.getNome() == null || playlist.getNome().isEmpty()) {
            throw new RuntimeException("Nome é obrigatório");
        }

        if (playlist.getUsuario() == null || playlist.getUsuario().getId() == null) {
            throw new RuntimeException("Usuário é obrigatório");
        }

        Usuario usuario = usuarioService.buscarPorId(playlist.getUsuario().getId());

        if (!usuario.isAtivo()) {
            throw new RuntimeException("Usuário inativo");
        }

        playlist.setUsuario(usuario);

        return repository.salvar(playlist);
    }

    public Playlist buscarPorId(Long id) {
        Playlist playlist = repository.buscarPorId(id)
                .orElseThrow(() -> new RuntimeException("Playlist não encontrada"));

        if (!playlist.isAtivo()) {
            throw new RuntimeException("Playlist inativa");
        }

        return playlist;
    }

    public List<Playlist> listar() {
        return repository.listar()
                .stream()
                .filter(Playlist::isAtivo)
                .toList();
    }

    public void deletar(Long id) {
        buscarPorId(id);
        repository.deletar(id);
    }

    public Playlist atualizar(Long id, Playlist atualizada) {

        Playlist playlist = buscarPorId(id);

        playlist.setNome(atualizada.getNome());
        playlist.setPublica(atualizada.isPublica());

        return playlist;
    }

    public void adicionarMusica(Long playlistId, Long musicaId, Long usuarioId) {

        Playlist playlist = buscarPorId(playlistId);
        Usuario usuario = usuarioService.buscarPorId(usuarioId);
        Musica musica = musicaService.buscarPorId(musicaId);

        if (!playlist.isAtivo()) {
            throw new RuntimeException("Playlist inativa");
        }

        if (!usuario.isAtivo()) {
            throw new RuntimeException("Usuário inativo");
        }

        if (!musica.isAtivo()) {
            throw new RuntimeException("Música inativa");
        }

        if (!playlist.getUsuario().getId().equals(usuarioId)) {
            throw new RuntimeException("Usuário não é dono da playlist");
        }

        if (playlist.contemMusica(musicaId)) {
            throw new RuntimeException("Música já está na playlist");
        }

        playlist.adicionarMusica(musica);
    }
}