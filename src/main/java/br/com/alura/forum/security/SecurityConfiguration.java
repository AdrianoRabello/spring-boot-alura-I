package br.com.alura.forum.security;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

/**
 * @autor Adriano Rabello 15/01/2021  8:24 AM
 */

@EnableWebSecurity
@Configuration
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {


    /** configuration for authentication */
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {

    }

    /** configuration for autorization */
    @Override
    protected void configure(HttpSecurity http) throws Exception {


        http.authorizeRequests()
                .antMatchers( "/**").permitAll()
                .antMatchers( "/h2-console/**").permitAll()
                .antMatchers( "/topicos").permitAll()
                .antMatchers(HttpMethod.POST, "/topicos").permitAll()
                .anyRequest().authenticated()
                .and()
                .formLogin();
    }

    /** configuration for static resoruces like css, js, imges etc */
    @Override
    public void configure(WebSecurity web) throws Exception {

    }
}
