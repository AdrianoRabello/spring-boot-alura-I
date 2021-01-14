package br.com.alura.forum.dtos;

import br.com.alura.forum.models.Resposta;

import java.time.LocalDateTime;

/**
 * @author Adriano Rabello
 * @created 14 / 01 / 2021 - 07:03
 */
public class RespostaDto {

    private Long id;
    private String mensagem;
    private LocalDateTime dataCriacao;
    private String nomeAutor;

    public RespostaDto(){

    }

    public RespostaDto(Resposta resposta){

        this.id = resposta.getId();
        this.dataCriacao = resposta.getDataCriacao();
        this.dataCriacao = resposta.getDataCriacao();
        this.nomeAutor = resposta.getAutor().getNome();
    }


    public Long getId() {
        return id;
    }

    public String getMensagem() {
        return mensagem;
    }

    public LocalDateTime getDataCriacao() {
        return dataCriacao;
    }

    public String getNomeAutor() {
        return nomeAutor;
    }
}

