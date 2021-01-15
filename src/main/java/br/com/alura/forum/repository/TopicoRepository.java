package br.com.alura.forum.repository;

import br.com.alura.forum.models.Topico;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * @autor Adriano Rabello 13/01/2021  9:27 PM
 */

public interface TopicoRepository extends JpaRepository<Topico, Long> {

   Page<Topico> findByCursoNome(String nome, Pageable pageable);
}
