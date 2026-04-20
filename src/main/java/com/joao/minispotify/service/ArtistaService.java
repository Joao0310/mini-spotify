package com.joao.minispotify.service;

import com.joao.minispotify.entidades.Artista;
import com.joao.minispotify.repository.ArtistaRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ArtistaService {

    private final ArtistaRepository repository;

    public ArtistaService(ArtistaRepository repository) {
        this.repository = repository;
    }

    public Artista criarArtista(Artista artista) {
        if (artista.getNome() == null || artista.getNome().isBlank()) {
            throw new RuntimeException("Nome é obrigatório");
        }

        return repository.save(artista);
    }

    public Artista buscarPorId(Long id) {
        Artista artista = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Artista não encontrado"));

        if (!artista.isAtivo()) {
            throw new RuntimeException("Artista inativo");
        }

        return artista;
    }

    public List<Artista> listar() {
        return repository.findByAtivoTrue();
    }

    public void deletar(Long id) {
        Artista artista = buscarPorId(id);
        artista.setAtivo(false);
        repository.save(artista);
    }

    public Artista atualizar(Long id, Artista artistaAtualizado) {
        Artista artista = buscarPorId(id);

        artista.setNome(artistaAtualizado.getNome());
        artista.setGeneroMusical(artistaAtualizado.getGeneroMusical());
        artista.setPaisOrigem(artistaAtualizado.getPaisOrigem());

        return repository.save(artista);
    }
}