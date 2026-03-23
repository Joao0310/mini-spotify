package com.joao.minispotify.service;

import com.joao.minispotify.entidades.Usuario;
import com.joao.minispotify.repository.UsuarioRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UsuarioService {
    private UsuarioRepository repository;

    public UsuarioService(UsuarioRepository repository) {
        this.repository = repository;
    }

    public Usuario criarUsuario(Usuario usuario) {

        if (usuario.getEmail() == null || usuario.getEmail().isEmpty()) {
            throw new RuntimeException("Email é obrigatório");
        }

        return repository.salvar(usuario);
    }

    public Usuario buscarPorId(Long id) {
        Usuario usuario = repository.buscarPorId(id)
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));

        if (!usuario.isAtivo()) {
            throw new RuntimeException("Usuário inativo");
        }

        return usuario;
    }

    public List<Usuario> listar() {
        return repository.listar().stream()
                .filter(Usuario::isAtivo)
                .toList();
    }

    public void deletar(Long id) {
        buscarPorId(id); // valida se existe
        repository.deletar(id);
    }

    public Usuario atualizar(Long id, Usuario usuarioAtualizado) {
        Usuario usuario = buscarPorId(id);

        usuario.setNome(usuarioAtualizado.getNome());
        usuario.setEmail(usuarioAtualizado.getEmail());
        usuario.setTipoPlano(usuarioAtualizado.getTipoPlano());

        return usuario;
    }
}
