package br.com.alura.forum.security;

import br.com.alura.forum.models.Usuario;
import br.com.alura.forum.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * @author Adriano Rabello
 * @created 15 / 01 / 2021 - 14:17
 */

@Service
public class AuthenticationService implements UserDetailsService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    /**
     * we dont ned to create a controller. Spring for dafault create it. We jsut need to implements the securities interfaces.
     * */
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        Optional<Usuario> usuario = usuarioRepository.findByEmail(username);

        if(usuario.isPresent()){
            return  usuario.get();
        }

       throw  new UsernameNotFoundException("Usuario não encontrado ");
    }



}
