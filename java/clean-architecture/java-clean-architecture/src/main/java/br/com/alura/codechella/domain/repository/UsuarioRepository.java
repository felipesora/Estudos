package br.com.alura.codechella.domain.repository;

import br.com.alura.codechella.domain.entity.usuario.Usuario;

import java.util.List;

public interface UsuarioRepository {

    Usuario cadastrarUsuario(Usuario usuario);

    List<Usuario> listarTodos();
}
