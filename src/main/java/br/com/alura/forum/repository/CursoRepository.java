package br.com.alura.forum.repository;

import br.com.alura.forum.models.Curso;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @autor Adriano Rabello 13/01/2021  9:27 PM
 */

@Repository
public interface CursoRepository extends JpaRepository<Curso, Long> {

    Curso findByNome(String nome);
}
