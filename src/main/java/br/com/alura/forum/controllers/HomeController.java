package br.com.alura.forum.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Adriano Rabello
 * @created 15 / 01 / 2021 - 14:32
 */

@RestController
public class HomeController {

    @GetMapping
    public String home(){

        return "Hello world";
    }
}
