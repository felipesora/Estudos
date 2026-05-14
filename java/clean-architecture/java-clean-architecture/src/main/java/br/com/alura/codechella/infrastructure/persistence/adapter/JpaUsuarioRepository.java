package br.com.alura.codechella.infrastructure.persistence.adapter;

import br.com.alura.codechella.domain.repository.UsuarioRepository;
import br.com.alura.codechella.domain.entity.usuario.Usuario;
import br.com.alura.codechella.infrastructure.mapper.UsuarioPersistenceMapper;
import br.com.alura.codechella.infrastructure.persistence.entity.UsuarioEntity;
import br.com.alura.codechella.infrastructure.persistence.repository.SpringDataUsuarioRepository;

import java.util.List;
import java.util.stream.Collectors;

public class JpaUsuarioRepository implements UsuarioRepository {

    private final SpringDataUsuarioRepository repository;
    private final UsuarioPersistenceMapper mapper;

    public JpaUsuarioRepository(SpringDataUsuarioRepository repository, UsuarioPersistenceMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    @Override
    public Usuario cadastrarUsuario(Usuario usuario) {
        UsuarioEntity entity = mapper.toEntity(usuario);
        repository.save(entity);
        return mapper.toDomain(entity);
    }

    @Override
    public List<Usuario> listarTodos() {
        return repository.findAll()
                .stream()
                .map(mapper::toDomain)
                .collect(Collectors.toList());
    }
}
