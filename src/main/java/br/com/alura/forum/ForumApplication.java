package br.com.alura.forum;

import br.com.alura.forum.models.Curso;
import br.com.alura.forum.models.Topico;
import br.com.alura.forum.models.Usuario;
import br.com.alura.forum.repository.CursoRepository;
import br.com.alura.forum.repository.TopicoRepository;
import br.com.alura.forum.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.data.web.config.EnableSpringDataWebSupport;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.Arrays;
import java.util.Collection;

@SpringBootApplication

/** enble request params and pass to SpringData. Is not enabled for default */
@EnableSpringDataWebSupport

/** To enble chache */
@EnableCaching

@EnableSwagger2
public class ForumApplication implements CommandLineRunner {

    @Autowired
    private TopicoRepository topicoRepository;

    @Autowired
    private CursoRepository cursoRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    public static void main(String[] args) {
        SpringApplication.run(ForumApplication.class, args);
    }




    @Override
    public void run(String... args) throws Exception {

        Curso curso = cursoRepository.save(new Curso("Curso de springboot ", "Categoria teste"));
        topicoRepository.saveAll(Arrays.asList(new Topico("Duvida sore spring boot ","Como cria o projeto?",curso)));

        usuarioRepository.save(new Usuario("Adriano Rabello", "adrianor.rabello@hotmail.com",  new BCryptPasswordEncoder().encode("123456")));



    }
}
