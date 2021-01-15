package br.com.alura.forum.dtos;

import br.com.alura.forum.models.Topico;
import org.springframework.data.domain.Page;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @autor Adriano Rabello 13/01/2021  10:05 PM
 */
public class TopicoDto {

    private Long id;
    private String titulo;
    private String mensagem;
    private LocalDateTime dataCriacao;


    public TopicoDto() {
    }

    public TopicoDto(Topico topico){

        this.id = topico.getId();
        this.titulo = topico.getTitulo();
        this.mensagem = topico.getMensagem();
        this.dataCriacao = topico.getDataCriacao();
    }

    public TopicoDto(Long id, String titulo, String mensagem, LocalDateTime dataCriacao) {
        this.id = id;
        this.titulo = titulo;
        this.mensagem = mensagem;
        this.dataCriacao = dataCriacao;
    }



    public static Page<TopicoDto> convert(Page<Topico> topicos){

        return topicos.map(TopicoDto::new);
    }

    public Long getId() {
        return id;
    }

    public String getTitulo() {
        return titulo;
    }

    public String getMensagem() {
        return mensagem;
    }

    public LocalDateTime getDataCriacao() {
        return dataCriacao;
    }



}
