package br.com.alura.forum;

import br.com.alura.forum.models.Curso;
import br.com.alura.forum.models.Topico;
import br.com.alura.forum.repository.CursoRepository;
import br.com.alura.forum.repository.TopicoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Arrays;
import java.util.Collection;

@SpringBootApplication
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
