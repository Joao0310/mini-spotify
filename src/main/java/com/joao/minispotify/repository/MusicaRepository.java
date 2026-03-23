package com.joao.minispotify.repository;

import com.joao.minispotify.entidades.Musica;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public class MusicaRepository {

    private Map<Long, Musica> banco = new HashMap<>();
    private Long contador = 1L;

    public Musica salvar(Musica musica) {
        musica.setId(contador);
        banco.put(contador, musica);
        contador++;
        return musica;
    }

    public List<Musica> listar() {
        return new ArrayList<>(banco.values());
    }

    public Optional<Musica> buscarPorId(Long id) {
        return Optional.ofNullable(banco.get(id));
    }

    public void deletar(Long id) {
        Musica musica = banco.get(id);
        if (musica != null) {
            musica.setAtivo(false);
        }
    }
}