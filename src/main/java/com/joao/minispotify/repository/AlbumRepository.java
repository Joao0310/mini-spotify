package com.joao.minispotify.repository;

import com.joao.minispotify.entidades.Album;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AlbumRepository extends JpaRepository<Album, Long> {
    List<Album> findByAtivoTrue();
}