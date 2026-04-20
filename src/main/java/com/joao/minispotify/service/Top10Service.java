package com.joao.minispotify.service;

import com.joao.minispotify.entidades.Musica;
import com.joao.minispotify.dto.Top10;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;

@Service
public class Top10Service {

    private final MusicaService musicaService;

    public Top10Service(MusicaService musicaService) {
        this.musicaService = musicaService;
    }

    public List<Top10> top10() {
        return musicaService.listar().stream()
                .sorted(Comparator.comparingLong((Musica m) ->
                        m.getTotalReproducoes() == null ? 0L : m.getTotalReproducoes()
                ).reversed())
                .limit(10)
                .map(m -> new Top10(
                        m.getTitulo(),
                        m.getArtista().getNome(),
                        m.getTotalReproducoes() == null ? 0L : m.getTotalReproducoes()
                ))
                .toList();
    }
}