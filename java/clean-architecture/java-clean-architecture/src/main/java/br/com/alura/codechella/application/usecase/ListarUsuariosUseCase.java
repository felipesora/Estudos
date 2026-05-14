package br.com.alura.codechella.application.usecase;

import br.com.alura.codechella.domain.entity.usuario.Usuario;
import br.com.alura.codechella.domain.repository.UsuarioRepository;

import java.util.List;

public class ListarUsuariosUseCase {

    private final UsuarioRepository repository;

    public ListarUsuariosUseCase(UsuarioRepository repository) {
        this.repository = repository;
    }

    public List<Usuario> obterTodosUsuarios() {
        return repository.listarTodos();
    }
}
