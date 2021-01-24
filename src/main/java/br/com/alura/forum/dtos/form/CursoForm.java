package br.com.alura.forum.dtos.form;

import br.com.alura.forum.models.Curso;
import br.com.alura.forum.repository.CursoRepository;

import javax.validation.constraints.NotEmpty;

/**
 * @autor Adriano Rabello 23/01/2021  5:53 PM
 */


public class CursoForm {


    @NotEmpty(message = "O nome é obrigatorio")
    private String nome;

    private String categoria;

    public CursoForm() {

    }

    public CursoForm(String nome, String categoria) {
        this.nome = nome;
        this.categoria = categoria;
    }

    public Curso save(CursoRepository cursoRepository) {

        return cursoRepository.save(new Curso(this));
    }


    public String getNome() {
        return nome;
    }

    public String getCategoria() {
        return categoria;
    }
}
