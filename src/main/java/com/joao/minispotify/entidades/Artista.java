package com.joao.minispotify.entidades;

import java.time.LocalDateTime;

public class Artista {
    private Long id;
    private String nome;
    private String generoMusical;
    private String paisOrigem;
    private boolean ativo;

    public Artista(String nome, String generoMusical, String paisOrigem) {
        this.nome = nome;
        this.generoMusical = generoMusical;
        this.paisOrigem = paisOrigem;
        this.ativo = true;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getGeneroMusical() {
        return generoMusical;
    }

    public void setGeneroMusical(String generoMusical) {
        this.generoMusical = generoMusical;
    }

    public String getPaisOrigem() {
        return paisOrigem;
    }

    public void setPaisOrigem(String paisOrigem) {
        this.paisOrigem = paisOrigem;
    }

    public boolean isAtivo() {
        return ativo;
    }

    public void setAtivo(boolean ativo) {
        this.ativo = ativo;
    }
}
