package br.com.alura.forum.dtos.form;

import br.com.alura.forum.models.Curso;
import br.com.alura.forum.models.Topico;
import br.com.alura.forum.repository.CursoRepository;
import com.sun.istack.NotNull;
import org.hibernate.validator.constraints.Length;

/**
 * @autor Adriano Rabello 13/01/2021  10:07 PM
 */
public class TopicoForm {

    @NotNull
    @Length(min = 5)
    private String titulo;
    private String mensagem;
    private String nomeCurso;



    public Topico convert(CursoRepository cursoRepository){

        Curso curso = cursoRepository.findByNome(nomeCurso);

        return new Topico(titulo,mensagem,curso);

    }


    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getMensagem() {
        return mensagem;
    }

    public void setMensagem(String mensagem) {
        this.mensagem = mensagem;
    }

    public String getNomeCurso() {
        return nomeCurso;
    }

    public void setNomeCurso(String nomeCurso) {
        this.nomeCurso = nomeCurso;
    }
}
