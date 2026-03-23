package com.joao.minispotify.service;

import com.joao.minispotify.entidades.Artista;
import com.joao.minispotify.repository.ArtistaRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ArtistaService {
    private ArtistaRepository repository;

    public ArtistaService(ArtistaRepository repository) {
        this.repository = repository;
    }

    public Artista criarArtista(Artista artista) {

        if (artista.getNome() == null || artista.getNome().isEmpty()) {
            throw new RuntimeException("Nome é obrigatório");
        }

        return repository.salvar(artista);
    }

    public Artista buscarPorId(Long id) {
        Artista artista = repository.buscarPorId(id)
                .orElseThrow(() -> new RuntimeException("Artista não encontrado"));

        if (!artista.isAtivo()) {
            throw new RuntimeException("Artista inativo");
        }

        return artista;
    }

    public List<Artista> listar() {
        return repository.listar().stream()
                .filter(Artista::isAtivo)
                .toList();
    }

    public void deletar(Long id) {
        buscarPorId(id); // valida se existe
        repository.deletar(id);
    }

    public Artista atualizar(Long id, Artista artistaAtualizado) {
        Artista artista = buscarPorId(id);

        artista.setNome(artistaAtualizado.getNome());
        artista.setGeneroMusical(artistaAtualizado.getGeneroMusical());
        artista.setPaisOrigem(artistaAtualizado.getPaisOrigem());

        return artista;
    }
}
