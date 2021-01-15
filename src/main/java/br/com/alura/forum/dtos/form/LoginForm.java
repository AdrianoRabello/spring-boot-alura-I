package br.com.alura.forum.dtos.form;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

/**
 * @author Adriano Rabello
 * @created 15 / 01 / 2021 - 14:57
 */
public class LoginForm {


    private String email;

    private String senha;



    public UsernamePasswordAuthenticationToken converter(){

        return new UsernamePasswordAuthenticationToken(email, senha);
    }


    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }
}
