package com.joao.minispotify.repository;

import com.joao.minispotify.entidades.Musica;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MusicaRepository extends JpaRepository<Musica, Long> {
    List<Musica> findByAtivoTrue();
}