package com.joao.minispotify.repository;

import com.joao.minispotify.entidades.Artista;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ArtistaRepository extends JpaRepository<Artista, Long> {
    List<Artista> findByAtivoTrue();
}