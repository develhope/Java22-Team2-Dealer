package com.develhope.spring.controller;

import com.develhope.spring.DTOs.Amministratore.AmministratoreDTO;
import com.develhope.spring.DTOs.Amministratore.CreateAmministratoreRequest;
import com.develhope.spring.entity.Amministratore;
import com.develhope.spring.service.AmministratoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/amministratore")
public class AmministratoreController {


    @Autowired
    private AmministratoreService amministratoreService;
    @PostMapping("/add")
    public ResponseEntity<?> addAmministratore(@Validated @RequestBody CreateAmministratoreRequest createAmministratoreRequest) {
        AmministratoreDTO result = amministratoreService.createAmministratore(createAmministratoreRequest);
        if (result == null) {
            return ResponseEntity.status(400).body("something goes wrong");
        } else {
            return ResponseEntity.ok().body(result);
        }
    }

    @DeleteMapping("/remove/{id}")
    public ResponseEntity<String> deleteById(@PathVariable Long id) {
        Optional<Amministratore> optionalAmministratore = amministratoreService.deleteById(id);
        if(optionalAmministratore.isPresent()) {
            return ResponseEntity.ok("Amministratore eliminato.");

        }
        return ResponseEntity.badRequest().body("Amministratore non trovato.");
    }
    @GetMapping("/getAll")
    public List<Amministratore> findAllAmministratori() {
        return amministratoreService.getAllAmministratori();
    }

    @PutMapping("/set/{id}")
    public ResponseEntity<String> setById(@PathVariable Long id, @RequestBody Amministratore amministratoreMod) {
        Amministratore amministratore = amministratoreService.updateAmministratore(id, amministratoreMod);
        if(amministratore != null) {
            return ResponseEntity.ok("amministratore aggiornato");
        }
        return ResponseEntity.badRequest().body("amministratore non trovato.");
    }

    @GetMapping("/getById/{id}")
    public Optional<Amministratore> getById(@PathVariable Long id) {
        return amministratoreService.getById(id);
    }
}