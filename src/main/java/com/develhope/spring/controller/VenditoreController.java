package com.develhope.spring.controller;

import com.develhope.spring.DTOs.Venditore.CreateVenditoreRequest;
import com.develhope.spring.DTOs.Venditore.VenditoreDTO;
import com.develhope.spring.entity.Venditore;
import com.develhope.spring.service.NoleggioService;
import com.develhope.spring.service.VenditoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/venditore")
public class VenditoreController {

    @Autowired
    private VenditoreService venditoreService;

    @Autowired
    private NoleggioService noleggioService;

    // rotta creazione venditore OK
    @PostMapping("/add")
    public ResponseEntity<?> createVenditore(@Validated @RequestBody CreateVenditoreRequest createVenditoreRequest) {
        VenditoreDTO result = venditoreService.createVenditore(createVenditoreRequest);
        if (result == null) {
            return ResponseEntity.status(400).body("something goes wrong");
        } else {
            return ResponseEntity.ok().body(result);
        }
    }

    // rotta cancellazione venditore OK
    @DeleteMapping("/remove/{id}")
    public ResponseEntity<String> deleteVenditoreById(@PathVariable Long id) {
        Optional<Venditore> optionalVenditore = venditoreService.deleteById(id);
        if (optionalVenditore.isPresent()) {
            return ResponseEntity.ok("Venditore eliminato.");
        }
        return ResponseEntity.badRequest().body("Venditore non trovato.");
    }

    //rotta ricerca tutti gli venditori OK
    @GetMapping("/getAll")
    public List<Venditore> findAllVenditore() {
        return venditoreService.getAllVenditori();
    }

    //set id
    @PutMapping("/set/{id}")
    public ResponseEntity<String> setVenditoreById(@PathVariable Long id, @RequestBody Venditore venditoreMod) {
        Venditore venditore = venditoreService.updateVenditore(id, venditoreMod);
        if (venditore != null) {
            return ResponseEntity.ok("Venditore aggiornato");
        }
        return ResponseEntity.badRequest().body("Venditore non trovato.");
    }

    // rotta ricerca Venditori da id
    @GetMapping("/getById/{id}")
    public Optional<Venditore> getVenditoreById(@PathVariable Long id) {
        return venditoreService.getVenditoreById(id);
    }

}
