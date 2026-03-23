package com.joao.minispotify.entidades;

import java.time.LocalDate;

public class Album {

    private Long id;
    private String titulo;
    private LocalDate dataLancamento;
    private Artista artista;
    private boolean ativo;

    public Album(String titulo, LocalDate dataLancamento, Artista artista) {
        this.titulo = titulo;
        this.dataLancamento = dataLancamento;
        this.artista = artista;
        this.ativo = true;
    }

    public Album() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public LocalDate getDataLancamento() {
        return dataLancamento;
    }

    public void setDataLancamento(LocalDate dataLancamento) {
        this.dataLancamento = dataLancamento;
    }

    public Artista getArtista() {
        return artista;
    }

    public void setArtista(Artista artista) {
        this.artista = artista;
    }

    public boolean isAtivo() {
        return ativo;
    }

    public void setAtivo(boolean ativo) {
        this.ativo = ativo;
    }
}
