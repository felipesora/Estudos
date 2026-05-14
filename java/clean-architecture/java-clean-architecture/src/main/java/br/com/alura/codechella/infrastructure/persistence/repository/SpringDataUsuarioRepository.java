package br.com.alura.codechella.infrastructure.persistence.repository;

import br.com.alura.codechella.infrastructure.persistence.entity.UsuarioEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SpringDataUsuarioRepository extends JpaRepository<UsuarioEntity, Long> {
}
