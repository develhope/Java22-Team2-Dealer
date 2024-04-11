package com.develhope.spring.admin;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1")
public class ControllerAmministratore {

    @RequestMapping(method = RequestMethod.GET, path = "/amministratore")
    public Amministratore logInAmministratore(
            @RequestParam String nome,
            @RequestParam String cognome,
            @RequestParam String email,
            @RequestParam String password
    ) {
        return new Amministratore(nome, cognome, email, password);
    }
}
