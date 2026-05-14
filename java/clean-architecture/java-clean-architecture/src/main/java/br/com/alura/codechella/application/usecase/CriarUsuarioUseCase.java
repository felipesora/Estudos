package br.com.alura.codechella.application.usecase;

import br.com.alura.codechella.domain.repository.UsuarioRepository;
import br.com.alura.codechella.domain.entity.usuario.Usuario;

public class CriarUsuarioUseCase {

    private final UsuarioRepository repository;

    public CriarUsuarioUseCase(UsuarioRepository repository) {
        this.repository = repository;
    }

    public Usuario cadastrarUsuario(Usuario usuario) {
        return repository.cadastrarUsuario(usuario);
    }
}
