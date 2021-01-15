package br.com.alura.forum.repository;

import br.com.alura.forum.models.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * @author Adriano Rabello
 * @created 15 / 01 / 2021 - 14:18
 */

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

    Optional<Usuario> findByEmail(String email);
}
