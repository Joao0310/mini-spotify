package com.joao.minispotify.entidades;

import jakarta.persistence.*;

@Entity
@Table(name = "artistas")
public class Artista {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;

    private String generoMusical;

    private String paisOrigem;

    private boolean ativo;

    public Artista() {
        // obrigatório para o JPA
    }

    public Artista(String nome, String generoMusical, String paisOrigem) {
        this.nome = nome;
        this.generoMusical = generoMusical;
        this.paisOrigem = paisOrigem;
        this.ativo = true;
    }

    public Long getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public String getGeneroMusical() {
        return generoMusical;
    }

    public String getPaisOrigem() {
        return paisOrigem;
    }

    public boolean isAtivo() {
        return ativo;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setGeneroMusical(String generoMusical) {
        this.generoMusical = generoMusical;
    }

    public void setPaisOrigem(String paisOrigem) {
        this.paisOrigem = paisOrigem;
    }

    public void setAtivo(boolean ativo) {
        this.ativo = ativo;
    }
}