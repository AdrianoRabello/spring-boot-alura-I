package br.com.alura.forum.Controllers;

import br.com.alura.forum.repository.CursoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @autor Adriano Rabello 13/01/2021  9:27 PM
 */

@RestController
@RequestMapping(value = "cursos")
public class CursoController {

    @Autowired
    private CursoRepository cursoRepository;


    @GetMapping
    private ResponseEntity<?> findAll(){
        return new ResponseEntity(cursoRepository.findAll(), HttpStatus.ACCEPTED);
    }

}
