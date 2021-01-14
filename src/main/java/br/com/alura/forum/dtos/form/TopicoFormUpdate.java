package br.com.alura.forum.dtos.form;

import br.com.alura.forum.dtos.TopicoDto;
import br.com.alura.forum.models.Topico;
import br.com.alura.forum.repository.TopicoRepository;

import java.util.Optional;

/**
 * @author Adriano Rabello
 * @created 14 / 01 / 2021 - 07:26
 */
public class TopicoFormUpdate {


    private String titulo;
    private String mensagem;




    public String getTitulo() {
        return titulo;
    }

    public String getMensagem() {
        return mensagem;
    }

    public TopicoDto update(Long id, TopicoRepository topicoRepository){
        Topico topico = topicoRepository.findById(id).orElse(null);

        topico.setMensagem(this.mensagem);
        topico.setTitulo(this.titulo);
        /** if i use trnsactional, i dont need to save. Transaction open a safe connect and commit in DB */
        //topicoRepository.save(topico)
        return new TopicoDto(topico);
    }
}
