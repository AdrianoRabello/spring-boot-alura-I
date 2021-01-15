package br.com.alura.forum.security;

import br.com.alura.forum.models.Usuario;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * @author Adriano Rabello
 * @created 15 / 01 / 2021 - 15:28
 */

@Service
public class TokenService {

    @Value("${token.expiration}")
    private String expiration;

    @Value("${token.secret}")
    private String secret;


    public String createToken(Authentication authenticate) {

        Usuario principal = (Usuario) authenticate.getPrincipal();

        Date hoje = new Date();

        Date dataExpiracao = new Date(hoje.getTime() + Long.parseLong(expiration));

        String token = Jwts.builder().setIssuer("Folum da alura") //
                .setSubject(principal.getId().toString()) // user identify
                .setIssuedAt(hoje)// data de geacao
                .setExpiration(dataExpiracao)//expiration date
                .signWith(SignatureAlgorithm.HS256, secret) // soghnature
                .compact(); // creating token

        System.out.println(token);

        return token;
    }
}
