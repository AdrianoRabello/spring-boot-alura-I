package br.com.alura.forum.repository;

import br.com.alura.forum.models.Curso;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @autor Adriano Rabello 23/01/2021  3:39 PM
 *
 * @todo IMPORTANT. For default All tests run wuth h2 database.
 * @todo If i want to run with another database i need to inlude this anotation: @AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
 *
 */



/**
 * to run like test
 * */
@RunWith(SpringRunner.class)

/** run this tests with this profile */
//@ActiveProfiles("h2")

/**
 * spring dosn't replace database configuration for my tests
 * */
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
/**
 * anotation to test data with repositories
 */
@DataJpaTest
public class CursoRepositoryTest {

    @Autowired
    private CursoRepository cursoRepository;

    @Autowired
    private TestEntityManager em;

    @Test
    public void findByCursoByNome() {

        String courseName = "HTML 5";

        em.persist(new Curso(courseName, "Categoria teste"));


        Curso course = cursoRepository.findByNome(courseName);

        Assert.assertEquals(courseName, course.getNome());
    }

    @Test
    public void notFindByCursoByNome() {

        String courseName = "Teste GG";

        Curso course = cursoRepository.findByNome(courseName);

        Assert.assertNull(course);
    }
}
