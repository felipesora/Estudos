package br.com.alura.codechella.infrastructure.config;

import br.com.alura.codechella.application.usecase.CriarUsuarioUseCase;
import br.com.alura.codechella.application.usecase.ListarUsuariosUseCase;
import br.com.alura.codechella.domain.repository.UsuarioRepository;
import br.com.alura.codechella.presentation.mapper.UsuarioControllerMapper;
import br.com.alura.codechella.infrastructure.mapper.UsuarioPersistenceMapper;
import br.com.alura.codechella.infrastructure.persistence.adapter.JpaUsuarioRepository;
import br.com.alura.codechella.infrastructure.persistence.repository.SpringDataUsuarioRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class UsuarioConfig {

    @Bean
    CriarUsuarioUseCase criarUsuarioUseCase(UsuarioRepository usuarioRepository) {
        return new CriarUsuarioUseCase(usuarioRepository);
    }

    @Bean
    ListarUsuariosUseCase listarUsuariosUseCase(UsuarioRepository usuarioRepository) {
        return new ListarUsuariosUseCase(usuarioRepository);
    }

    @Bean
    JpaUsuarioRepository criarRepositoryJpa(SpringDataUsuarioRepository repository, UsuarioPersistenceMapper mapper) {
        return new JpaUsuarioRepository(repository, mapper);
    }

    @Bean
    UsuarioPersistenceMapper criarMapperPersistence() {
        return new UsuarioPersistenceMapper();
    }

    @Bean
    UsuarioControllerMapper criarMapperController() {
        return new UsuarioControllerMapper();
    }
}
