package br.com.alura.forum.controllers;

import br.com.alura.forum.dtos.form.CursoForm;
import br.com.alura.forum.models.Curso;
import br.com.alura.forum.repository.CursoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * @autor Adriano Rabello 13/01/2021  9:27 PM
 */

@RestController
@RequestMapping(value = "/cursos")
@CrossOrigin("*")
public class CursoController {

    @Autowired
    private CursoRepository cursoRepository;


    @GetMapping
    public ResponseEntity<?> findAll() {
        return new ResponseEntity(cursoRepository.findAll(), HttpStatus.ACCEPTED);
    }

    @PostMapping
    public ResponseEntity<Curso> save(@RequestBody @Valid CursoForm cursoForm) {

        return new ResponseEntity<>(cursoForm.save(cursoRepository), HttpStatus.CREATED);
    }


}
