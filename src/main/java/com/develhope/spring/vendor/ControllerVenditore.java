package com.develhope.spring.vendor;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1")
public class ControllerVenditore {

    @RequestMapping(method = RequestMethod.GET, path = "/venditore")
    public Venditore logInVenditore(
            @RequestParam String nome,
            @RequestParam String cognome,
            @RequestParam String telefono,
            @RequestParam String email,
            @RequestParam String password
    ) {
        return new Venditore(nome, cognome, telefono, email, password);
    }
}