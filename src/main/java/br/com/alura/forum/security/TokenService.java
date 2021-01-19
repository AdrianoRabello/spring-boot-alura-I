package br.com.alura.forum.security;

import br.com.alura.forum.models.Usuario;
import io.jsonwebtoken.Claims;
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

        String token = Jwts.builder().setIssuer("Folum da alura") // who created token
                .setSubject(principal.getId().toString()) // user identify
                .setIssuedAt(hoje)// generated date
                .setExpiration(dataExpiracao)//expiration date
                .signWith(SignatureAlgorithm.HS256, secret) // sighnature and sregret to sign
                .compact(); // creating token

        System.out.println(token);

        return token;
    }

    public boolean tokenIsValid(String token) {

        try {

            /** validating token with secret */
            Jwts.parser().setSigningKey(this.secret).parseClaimsJws(token);
            return true;
        } catch (Exception e) {

            return false;
        }


    }

    /**
     * Geeting Usuario id iside token
     * **/
    public Long getUserIdFromToken(String token) {

        Claims clains = Jwts.parser().setSigningKey(this.secret).parseClaimsJws(token).getBody();

        /**
         * Geeting id inside token and parsing to long */
        return Long.parseLong(clains.getSubject());
    }
}
