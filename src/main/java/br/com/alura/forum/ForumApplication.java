package br.com.alura.forum;

import br.com.alura.forum.models.Curso;
import br.com.alura.forum.models.Topico;
import br.com.alura.forum.repository.CursoRepository;
import br.com.alura.forum.repository.TopicoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.data.web.config.EnableSpringDataWebSupport;

import java.util.Arrays;
import java.util.Collection;

@SpringBootApplication

/** enble request params and pass to SpringData. Is not enabled for default */
@EnableSpringDataWebSupport

/** To enble chache */
@EnableCaching
public class ForumApplication implements Runnable {

    @Autowired
    private TopicoRepository topicoRepository;

    @Autowired
    private CursoRepository cursoRepository;

    public static void main(String[] args) {
        SpringApplication.run(ForumApplication.class, args);
    }

    @Override
    public void run() {

        Curso curso = cursoRepository.save(new Curso("Curso de springboot ", "Categoria teste"));
        topicoRepository.saveAll(Arrays.asList(new Topico("Duvida sore spring boot ","Como cria o projeto?",curso)));
    }
}
