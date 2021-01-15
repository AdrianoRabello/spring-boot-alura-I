package br.com.alura.forum.Controllers;

import br.com.alura.forum.dtos.TopicoDto;
import br.com.alura.forum.dtos.TopicoDtoDetail;
import br.com.alura.forum.dtos.form.TopicoForm;
import br.com.alura.forum.dtos.form.TopicoFormUpdate;
import br.com.alura.forum.models.Curso;
import br.com.alura.forum.models.Topico;
import br.com.alura.forum.repository.CursoRepository;
import br.com.alura.forum.repository.TopicoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.transaction.Transactional;
import java.net.URI;
import java.util.List;
import java.util.Optional;

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
    /** @Requestparam define param required or not */
//    public ResponseEntity<Page<TopicoDto>> findAll(@RequestParam(required = false) String nome,
//                                                   @RequestParam int page,
//                                                   @RequestParam int quantity,
//                                                   @RequestParam(required = false) String ordenacao) {

    /** to use this wee nedd to enable @EnableSpringDataWebSupport in Application main. Value is the id name for atribute
     * cache  */
    @Cacheable(value = "topicoList")
    public ResponseEntity<Page<TopicoDto>> findAll(@RequestParam(required = false) String nome,
                                                   @PageableDefault(sort = "id", direction = Sort.Direction.ASC) Pageable pageable) {






        if (nome == null) {
            Page<Topico> pages = topicoRepository.findAll(pageable);

            return new ResponseEntity<>(TopicoDto.convert(pages), HttpStatus.OK);

        }


        Page<Topico> topicos = topicoRepository.findByCursoNome(nome, pageable);

        return new ResponseEntity<>(TopicoDto.convert(topicos), HttpStatus.OK);
    }

    @PostMapping
    /** to clear all cache. */
    @CacheEvict(value = "topicoList", allEntries = true)
    public ResponseEntity<TopicoDto> save(@RequestBody @Validated TopicoForm topicoForm, UriComponentsBuilder uriComponentsBuilder) {

        /** thves to convert are insidde TopicoForm Object. I passad cursoRepository for TopicoForm to find Curso by name */
        Topico topico = topicoForm.convert(cursoRepository);

        topicoRepository.save(topico);

        /** generationg  URI for new resource generated */
        URI uri = uriComponentsBuilder.path("/topicos/{id}").buildAndExpand(topico.getId()).toUri();

        return ResponseEntity.created(uri).body(new TopicoDto(topico));
    }

    @GetMapping("/{id}")
    public ResponseEntity<TopicoDto> findById(@PathVariable Long id) {
        Optional<Topico> topico = topicoRepository.findById(id);

        if (topico.isPresent()) {
            return ResponseEntity.ok(new TopicoDto(topico.get()));
        }

        return ResponseEntity.notFound().build();
    }

    @GetMapping("/{id}/detail")
    public TopicoDtoDetail findTopicoDtoDetail(@PathVariable Long id) {
        Optional<Topico> topico = topicoRepository.findById(id);

        return new TopicoDtoDetail(topico.orElse(null));
    }

    @PutMapping("{id}")
    @Transactional
    @CacheEvict(value = "topicoList", allEntries = true)
    public TopicoDto update(@PathVariable Long id, @RequestBody TopicoFormUpdate topicoUpdate) {

        return topicoUpdate.update(id, topicoRepository);

    }

    @DeleteMapping("{id}")
    @CacheEvict(value = "topicoList", allEntries = true)
    public ResponseEntity delete(@PathVariable Long id) {

        topicoRepository.deleteById(id);

        return ResponseEntity.ok().build();
    }
}
