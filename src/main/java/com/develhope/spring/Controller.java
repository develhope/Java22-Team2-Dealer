package com.develhope.spring;

import com.develhope.spring.customer.Acquirente;
import org.springframework.web.bind.annotation.*;
@RestController
@RequestMapping("/v1")
public class Controller {
    @RequestMapping(method = RequestMethod.GET, path = "/acquirente")
    public Acquirente logIn(@RequestParam String nome,
                            @RequestParam String cognome,
                            @RequestParam String telefono,
                            @RequestParam String email,
                            @RequestParam String password) {
        return new Acquirente(nome, cognome, telefono, email, password);
    }

}
