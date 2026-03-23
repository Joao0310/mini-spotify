package com.joao.minispotify.entidades;

public class Top10 {

    private String titulo;
    private String artista;
    private Long totalReproducoes;

    public Top10(String titulo, String artista, Long totalReproducoes) {
        this.titulo = titulo;
        this.artista = artista;
        this.totalReproducoes = totalReproducoes;
    }

    public String getTitulo() {
        return titulo;
    }

    public String getArtista() {
        return artista;
    }

    public Long getTotalReproducoes() {
        return totalReproducoes;
    }
}
