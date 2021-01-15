package br.com.alura.forum.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/**
 * @autor Adriano Rabello 15/01/2021  8:24 AM
 */

@EnableWebSecurity
@Configuration
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {


    /**
     * configuration for authentication
     */


    @Override
    @Bean
    protected AuthenticationManager authenticationManager() throws Exception {
        return super.authenticationManager();
    }

    @Autowired
    private AuthenticationService authenticationService;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {

        auth.userDetailsService(authenticationService).passwordEncoder(bCryptPasswordEncoder());
    }

    /**
     * configuration for autorization
     */
    @Override
    protected void configure(HttpSecurity http) throws Exception {


        http.authorizeRequests()
                .antMatchers("/h2-console/**").permitAll()
                .antMatchers("/auth").permitAll()
                .antMatchers("/topicos").permitAll()
                .antMatchers(HttpMethod.POST, "/topicos").permitAll()
                .anyRequest().authenticated()
                .and().csrf().disable()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);

    }

    /**
     * configuration for static resoruces like css, js, imges etc
     */
    @Override
    public void configure(WebSecurity web) throws Exception {

    }

    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
