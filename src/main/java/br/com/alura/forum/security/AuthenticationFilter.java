package br.com.alura.forum.security;

import br.com.alura.forum.models.Usuario;
import br.com.alura.forum.repository.UsuarioRepository;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @autor Adriano Rabello 16/01/2021  4:20 PM
 *
 * Inside this class we can't use @Autowide. If we need to use any beans, we need to pass in constructor like parameter.
 */
public class AuthenticationFilter extends OncePerRequestFilter {


    private TokenService tokenService;
    private UsuarioRepository usuarioRepository;

    public AuthenticationFilter(TokenService tokenService, UsuarioRepository usuarioRepository){
        this.tokenService = tokenService;
        this.usuarioRepository = usuarioRepository;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, FilterChain filterChain) throws ServletException, IOException {

        String token = getToken(httpServletRequest);

        boolean isValid = tokenService.tokenIsValid(token);

        if(isValid){
            userIsAuthenticated(token);
        }

        filterChain.doFilter(httpServletRequest, httpServletResponse);
    }

    private void userIsAuthenticated(String token){
        Long id = tokenService.getUserIdFromToken(token);
        Usuario user = getUserById(id);

        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(user, null,user.getAuthorities());

        /**
         * Setting auth user after validate token
         * */
        SecurityContextHolder.getContext().setAuthentication(authenticationToken);
    }

    private String getToken(HttpServletRequest request){

        String token = request.getHeader("Authorization");

        if(token == null || token.isEmpty() || !token.startsWith("Bearer ")){

            return null;
        }

        return token.substring(7);
    }

    private Usuario getUserById(Long id){
        return usuarioRepository.findById(id).orElse(null);
    }
}
