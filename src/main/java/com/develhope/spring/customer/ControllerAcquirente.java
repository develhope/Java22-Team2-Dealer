package com.develhope.spring.customer;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1")
public class ControllerAcquirente {

    @RequestMapping(method = RequestMethod.GET, path = "/acquirente")
    public Acquirente logInAcquirente(
            @RequestParam String nome,
            @RequestParam String cognome,
            @RequestParam String telefono,
            @RequestParam String email,
            @RequestParam String password
    ) {
        return new Acquirente(nome, cognome, telefono, email, password);
    }
}
