package com.joao.minispotify.entidades;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "musicas")
public class Musica {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String titulo;

    private Integer duracaoSegundos;

    private Integer numeroFaixa;

    @ManyToOne
    @JoinColumn(name = "album_id")
    private Album album;

    @ManyToOne
    @JoinColumn(name = "artista_id")
    private Artista artista;

    private Long totalReproducoes;

    private boolean ativo;

    public Musica() {
        this.totalReproducoes = 0L;
        this.ativo = true;
    }

    public Musica(String titulo, Integer duracaoSegundos, Integer numeroFaixa, Album album, Artista artista) {
        this.titulo = titulo;
        this.duracaoSegundos = duracaoSegundos;
        this.numeroFaixa = numeroFaixa;
        this.album = album;
        this.artista = artista;
        this.totalReproducoes = 0L;
        this.ativo = true;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getTitulo() { return titulo; }
    public void setTitulo(String titulo) { this.titulo = titulo; }

    public Integer getDuracaoSegundos() { return duracaoSegundos; }
    public void setDuracaoSegundos(Integer duracaoSegundos) { this.duracaoSegundos = duracaoSegundos; }

    public Integer getNumeroFaixa() { return numeroFaixa; }
    public void setNumeroFaixa(Integer numeroFaixa) { this.numeroFaixa = numeroFaixa; }

    public Album getAlbum() { return album; }
    public void setAlbum(Album album) { this.album = album; }

    public Artista getArtista() { return artista; }
    public void setArtista(Artista artista) { this.artista = artista; }

    public Long getTotalReproducoes() { return totalReproducoes; }
    public void setTotalReproducoes(Long totalReproducoes) { this.totalReproducoes = totalReproducoes; }

    public boolean isAtivo() { return ativo; }
    public void setAtivo(boolean ativo) { this.ativo = ativo; }

    public void incrementarReproducoes() {
        this.totalReproducoes++;
    }
}