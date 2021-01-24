package br.com.alura.forum.controllers;

import br.com.alura.forum.dtos.form.CursoForm;
import br.com.alura.forum.models.Curso;
import br.com.alura.forum.models.Topico;
import br.com.alura.forum.models.Usuario;
import br.com.alura.forum.repository.UsuarioRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.SerializationFeature;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Bean;
import org.springframework.http.MediaType;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MockMvcBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.charset.Charset;
import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @autor Adriano Rabello 23/01/2021  5:18 PM
 */

/** @todo this configuration is to confgure spring no replace my configured DB for H2 in tests */

@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc /** to use monkvsv in tests */
@ActiveProfiles(value = {"dev"}) /** i can set an array */
class AuthenticationControllerTest {


    public static final MediaType APPLICATION_JSON_UTF8 = new MediaType(MediaType.APPLICATION_JSON.getType(), MediaType.APPLICATION_JSON.getSubtype(), Charset.forName("utf8"));
    private static String URL = "http://localhost:8090/cursos";

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private UsuarioRepository usuarioRepository;




    @BeforeEach
    void setUp() {
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    @Transactional
    void authenticate() throws Exception {


        usuarioRepository.save(new Usuario("Adriano Rabello", "adrianor.rabello@hotmailcom",passwordEncoder().encode("123456") ));

        URI uri = new URI("/auth");
        String json = "{\"email\":\"adrianor.rabello@hotmail.com\", \"senha\":\"123456\"}";

        mockMvc.perform(MockMvcRequestBuilders
                .post(uri).content(json) /** URI and json to post **/
                .contentType(MediaType.APPLICATION_JSON)) /** media type configuration */
                .andExpect(MockMvcResultMatchers.status() /** Expected result **/
                        .is(200)); /** status spected result */
    }

    @Test
    void saveCourse() throws Exception {


        Map<String, CursoForm> map = new HashMap<>();
        map.put("cursos", new CursoForm("Curso teste", "teste"));

        URI uri = new URI("/cursos");
        String json = "{\"nome\":\"springboot teste\", \"categoria\":\"categoria test \"}";

        System.out.println(map.values());

        System.out.printf("teste");
        mockMvc.perform(MockMvcRequestBuilders
                .post(uri).content(json) /** URI and json to post **/
                .contentType(MediaType.APPLICATION_JSON)) /** media type configuration */
                .andExpect(MockMvcResultMatchers.status() /** Expected result **/
                        .is(201)); /** status spected result */
    }


    @Test
    public void persirstCourso() throws Exception {

        Curso curso = new Curso("Nome do curso", "categoria do curso");

        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(SerializationFeature.WRAP_ROOT_VALUE, false);
        ObjectWriter ow = mapper.writer().withDefaultPrettyPrinter();

        String requestJson = ow.writeValueAsString(curso);

        mockMvc.perform(MockMvcRequestBuilders.post(URL).contentType(APPLICATION_JSON_UTF8)
                .content(requestJson))
                .andExpect(MockMvcResultMatchers.status().is(201));


    }


    public PasswordEncoder passwordEncoder() {
        PasswordEncoder encoder = new BCryptPasswordEncoder();
        return encoder;
    }


}