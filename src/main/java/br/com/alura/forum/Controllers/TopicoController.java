package br.com.alura.forum.Controllers;

import br.com.alura.forum.dtos.TopicoDto;
import br.com.alura.forum.dtos.form.TopicoForm;
import br.com.alura.forum.models.Curso;
import br.com.alura.forum.models.Topico;
import br.com.alura.forum.repository.CursoRepository;
import br.com.alura.forum.repository.TopicoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.List;

/**
 * @autor Adriano Rabello 13/01/2021  9:27 PM
 */

@RestController
@RequestMapping(value = "/topicos")
public class TopicoController {

    @Autowired
    private TopicoRepository topicoRepository;

    @Autowired
    private CursoRepository cursoRepository;


    @GetMapping
    public ResponseEntity<List<TopicoDto>> findAll(){

        List<Topico> topicos = topicoRepository.findAll();


        return  new ResponseEntity<>(TopicoDto.convert(topicos), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<TopicoDto> save(@RequestBody @Validated TopicoForm topicoForm, UriComponentsBuilder uriComponentsBuilder){

        /** thves to convert are insidde TopicoForm Object. I passad cursoRepository for TopicoForm to find Curso by name */
        Topico topico = topicoForm.convert(cursoRepository);

        topicoRepository.save(topico);

        /** generationg  URI for new resource generated */
        URI uri = uriComponentsBuilder.path("/topicos/{id}").buildAndExpand(topico.getId()).toUri();

        return ResponseEntity.created(uri).body( new TopicoDto(topico));
    }
}
