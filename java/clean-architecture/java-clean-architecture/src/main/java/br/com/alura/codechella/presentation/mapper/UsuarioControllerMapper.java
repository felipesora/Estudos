package br.com.alura.codechella.presentation.mapper;

import br.com.alura.codechella.domain.entity.usuario.Usuario;
import br.com.alura.codechella.presentation.request.CreateUsuarioRequest;
import br.com.alura.codechella.presentation.response.UsuarioResponse;

public class UsuarioControllerMapper {

    public Usuario toDomain(CreateUsuarioRequest request) {

        return new Usuario(
                null,
                request.cpf(),
                request.nome(),
                request.nascimento(),
                request.email()
        );
    }

    public UsuarioResponse toResponse(Usuario usuario) {

        return new UsuarioResponse(
                usuario.getId(),
                usuario.getCpf(),
                usuario.getNome(),
                usuario.getNascimento(),
                usuario.getEmail()
        );
    }
}
