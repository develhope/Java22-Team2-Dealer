package com.develhope.spring.controller;
import com.develhope.spring.entity.Veicolo;
import com.develhope.spring.service.VeicoloService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/veicolo")
public class VeicoloController {
    @Autowired
    private VeicoloService veicoloService;

    @PostMapping("/addVeicolo")
    public ResponseEntity<String> addVeicolo(@RequestBody Veicolo veicolo) {
        veicoloService.addVeicolo(veicolo);
        return ResponseEntity.ok("Veicolo aggiunto correttamente.");
    }

    @DeleteMapping("/veicoli/{id}")
    public ResponseEntity<?> deleteById(@PathVariable Long id) {
        veicoloService.deleteById(id);
        return ResponseEntity.ok("Veicolo rimosso.");
    }

    @GetMapping("/getMarca")
    public List<Veicolo> searchByMarca(@RequestParam String partialMarca) {
        return veicoloService.searchByMarca(partialMarca);
    }

}
