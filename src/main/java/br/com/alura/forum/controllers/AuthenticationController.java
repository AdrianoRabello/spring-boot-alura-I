package br.com.alura.forum.controllers;

import br.com.alura.forum.dtos.form.LoginForm;
import br.com.alura.forum.security.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

/**
 * @author Adriano Rabello
 * @created 15 / 01 / 2021 - 14:49
 */

@RestController
@RequestMapping("/auth")
public class AuthenticationController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private TokenService tokenService;


    @PostMapping
    public ResponseEntity<?> authenticate(@RequestBody @Valid LoginForm form) {

        UsernamePasswordAuthenticationToken dadosLogin = form.converter();

        try {

            Authentication authenticate = authenticationManager.authenticate(dadosLogin);

            String token = tokenService.createToken(authenticate);

            return ResponseEntity.ok().build();

        } catch (AuthenticationException e) {

            return ResponseEntity.badRequest().build();
        }


    }
}
