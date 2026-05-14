package br.com.alura.codechella.infrastructure.mapper;

import br.com.alura.codechella.domain.entity.usuario.Usuario;
import br.com.alura.codechella.infrastructure.persistence.entity.UsuarioEntity;

public class UsuarioPersistenceMapper {

    public UsuarioEntity toEntity(Usuario usuario) {
        return new UsuarioEntity(
                usuario.getId(),
                usuario.getCpf(),
                usuario.getNome(),
                usuario.getNascimento(),
                usuario.getEmail());
    }

    public Usuario toDomain(UsuarioEntity entity) {
        return new Usuario(
                entity.getId(),
                entity.getCpf(),
                entity.getNome(),
                entity.getNascimento(),
                entity.getEmail());
    }
}
