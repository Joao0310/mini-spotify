package com.joao.minispotify.entidades;

import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "playlists")
public class Playlist {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;

    private boolean publica;

    private LocalDateTime dataCriacao;

    @ManyToOne
    @JoinColumn(name = "usuario_id")
    private Usuario usuario;

    @ManyToMany
    @JoinTable(
            name = "playlist_musicas",
            joinColumns = @JoinColumn(name = "playlist_id"),
            inverseJoinColumns = @JoinColumn(name = "musica_id")
    )
    private List<Musica> musicas;

    private boolean ativo;

    public Playlist() {
        this.musicas = new ArrayList<>();
        this.ativo = true;
        this.dataCriacao = LocalDateTime.now();
    }

    public Playlist(String nome, boolean publica, Usuario usuario) {
        this.nome = nome;
        this.publica = publica;
        this.usuario = usuario;
        this.dataCriacao = LocalDateTime.now();
        this.musicas = new ArrayList<>();
        this.ativo = true;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }

    public boolean isPublica() { return publica; }
    public void setPublica(boolean publica) { this.publica = publica; }

    public LocalDateTime getDataCriacao() { return dataCriacao; }
    public void setDataCriacao(LocalDateTime dataCriacao) { this.dataCriacao = dataCriacao; }

    public Usuario getUsuario() { return usuario; }
    public void setUsuario(Usuario usuario) { this.usuario = usuario; }

    public List<Musica> getMusicas() { return musicas; }
    public void setMusicas(List<Musica> musicas) { this.musicas = musicas; }

    public boolean isAtivo() { return ativo; }
    public void setAtivo(boolean ativo) { this.ativo = ativo; }

    public void adicionarMusica(Musica musica) {
        this.musicas.add(musica);
    }

    public boolean contemMusica(Long musicaId) {
        return this.musicas.stream().anyMatch(m -> m.getId().equals(musicaId));
    }
}