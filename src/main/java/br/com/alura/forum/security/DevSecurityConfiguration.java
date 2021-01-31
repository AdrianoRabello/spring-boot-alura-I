package br.com.alura.forum.security;

import br.com.alura.forum.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

/**
 * @autor Adriano Rabello 15/01/2021  8:24 AM
 */

@EnableWebSecurity
@Configuration

/**
 * this anotation will permite this class process only in dev profile
 * */
@Profile( value =  {"dev"}) /** this cofiguration is for load many profiles */
public class DevSecurityConfiguration extends WebSecurityConfigurerAdapter {


    /**
     * this service implements spring security  UserDatilsService
     */
    @Autowired
    private AuthenticationService authenticationService;

    @Autowired
    private TokenService tokenService;

    @Autowired
    private UsuarioRepository usuarioRepository;


    /**
     * We need to configure this bean to get authentication. For default spring dosen't inejct 'it.
     * */
    @Override
    @Bean
    protected AuthenticationManager authenticationManager() throws Exception {
        return super.authenticationManager();
    }

    /**
     * configuration for authentication
     */

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        /**
         * passwordeEncoder pass passwordEndorder Algorithm
         * */
        auth.userDetailsService(authenticationService).passwordEncoder(bCryptPasswordEncoder());
    }

    /**
     * configuration for autorization
     */
    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http.cors();
        //http.headers().frameOptions().disable(); /** for h2 to work */
        http.authorizeRequests()
                .antMatchers("/h2-console/*").permitAll()
                .antMatchers("/h2-console/**").permitAll()
                .antMatchers("/upload").permitAll()
                .antMatchers("/cursos").permitAll()
                .antMatchers(HttpMethod.GET,"/actuator").permitAll()
                .antMatchers(HttpMethod.GET,"/actuator/**").permitAll()
                .antMatchers(HttpMethod.POST,"/auth").permitAll()
                .antMatchers("/topicos").permitAll()/** I need t oconfigure to permit findByID, cuz is blockled only with this configuration */
                .antMatchers("/topicos/*").permitAll()
                .antMatchers(HttpMethod.DELETE, "/topicos/*").hasRole("ADMIN")  /** will permit only admins delete */
                .antMatchers(HttpMethod.POST, "/topicos").permitAll()
                .anyRequest().authenticated()
                .and().csrf().disable()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .addFilterBefore(new AuthenticationFilter(tokenService, usuarioRepository), UsernamePasswordAuthenticationFilter.class); // this configuration set AuthenticationFilter before spring autenticationfilter default.



    }

    /**
     * configuration for static resoruces like css, js, imges etc
     */
    @Override
    public void configure(WebSecurity web) throws Exception {

        web.ignoring().antMatchers("/**.html", "/v2/api-docs", "/webjars/**","/swagger-resources/**");
    }

    /**
     * Bean configuration for encrypt password
     */
    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
