package com.develhope.spring.controller;

import com.develhope.spring.entity.Acquirente;
import com.develhope.spring.entity.Veicolo;
import com.develhope.spring.service.AcquirenteService;
import com.develhope.spring.service.VeicoloService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/acquirente")
public class AcquirenteController {
    @Autowired
    private AcquirenteService acquirenteService;
    @PostMapping("/addAcquirente")
    public ResponseEntity<String> addAcquirente(@RequestBody Acquirente acquirente) {
        acquirenteService.addAcquirente(acquirente);
        return ResponseEntity.ok("Acquirente aggiunto correttamente.");
    }

    @DeleteMapping("/removeAcquirente/{id}")
    public ResponseEntity<String> deleteById(@PathVariable Long id) {
        Optional<Acquirente> optionalAcquirente = acquirenteService.deleteById(id);
        if(optionalAcquirente.isPresent()) {
            return ResponseEntity.ok("Acquirente eliminato.");

        }
        return ResponseEntity.badRequest().body("Acquirente non trovato.");
    }
        @GetMapping("/getAllAcquirenti")
    public List<Acquirente> findAllAcquirenti() {
        return acquirenteService.getAllAcquirenti();
    }

    @PutMapping("/setAcquirente/{id}")
    public ResponseEntity<String> setById(@PathVariable Long id, @RequestBody Acquirente acquirenteMod) {
        Acquirente acquirente = acquirenteService.updateAcquirente(id, acquirenteMod);
        if(acquirente != null) {
            return ResponseEntity.ok("Acquirente aggiornato");
        }
        return ResponseEntity.badRequest().body("Acquirente non trovato.");
    }

    @GetMapping("/getById/{id}")
    public Optional<Acquirente> getById(@PathVariable Long id) {
        return acquirenteService.getById(id);
    }
}
