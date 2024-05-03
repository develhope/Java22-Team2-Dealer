package com.develhope.spring.controller;

import com.develhope.spring.DTOs.Acquirente.AcquirenteDTO;
import com.develhope.spring.DTOs.Acquirente.CreateAcquirenteRequest;
import com.develhope.spring.DTOs.Noleggio.CreateNoleggioRequest;
import com.develhope.spring.DTOs.Noleggio.NoleggioDTO;
import com.develhope.spring.entity.Acquirente;
import com.develhope.spring.entity.Noleggio;
import com.develhope.spring.service.AcquirenteService;
import com.develhope.spring.service.NoleggioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/acquirente")
public class AcquirenteController {

    @Autowired
    private AcquirenteService acquirenteService;

    @Autowired
    private NoleggioService noleggioService;

    // rotta creazione acquirente OK
    @PostMapping("/add")
    public ResponseEntity<?> createAcquirente(@Validated @RequestBody CreateAcquirenteRequest createAcquirenteRequest) {
        AcquirenteDTO result = acquirenteService.createAcquirente(createAcquirenteRequest);
        if (result == null) {
            return ResponseEntity.status(400).body("something goes wrong");
        } else {
            return ResponseEntity.ok().body(result);
        }
    }


    // rotta cancellazione acquirente OK
    @DeleteMapping("/remove/{id}")
    public ResponseEntity<String> deleteAcquirenteById(@PathVariable Long id) {
        Optional<Acquirente> optionalAcquirente = acquirenteService.deleteById(id);
        if (optionalAcquirente.isPresent()) {
            return ResponseEntity.ok("Acquirente eliminato.");
        }
        return ResponseEntity.badRequest().body("Acquirente non trovato.");
    }

    //rotta ricerca tutti gli acquirenti OK
    @GetMapping("/getAll")
    public List<Acquirente> findAllAcquirenti() {
        return acquirenteService.getAllAcquirenti();
    }



    @PutMapping("/set/{id}")
    public ResponseEntity<String> setAcquirenteById(@PathVariable Long id, @RequestBody Acquirente acquirenteMod) {
        Acquirente acquirente = acquirenteService.updateAcquirente(id, acquirenteMod);
        if (acquirente != null) {
            return ResponseEntity.ok("Acquirente aggiornato");
        }
        return ResponseEntity.badRequest().body("Acquirente non trovato.");
    }

    // rotta ricerca acquirenti da id
    @GetMapping("/getById/{id}")
    public Optional<Acquirente> getAcquirenteById(@PathVariable Long id) {
        return acquirenteService.getById(id);
    }

    //rotta ricerca noleggi acquirente da id
/*    @GetMapping("/{acquirenteId}/noleggi")
    public ResponseEntity<List<Noleggio>> getNoleggiByAcquirenteId(@PathVariable Long acquirenteId){
        List<Noleggio> noleggi = noleggioService.findByAcquirente(acquirenteId);
        if (noleggi.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(noleggi);
    }*/

    // rotta creazione noleggio dell'acquirente
    @PostMapping("/{acquirenteId}/noleggi/add")
    public ResponseEntity<NoleggioDTO> addNoleggio(@Validated @PathVariable Long acquirenteId, @RequestBody CreateNoleggioRequest createNoleggioRequest) {
        Acquirente acquirente = acquirenteService.getById(acquirenteId).orElse(null);
        if (acquirente == null) {
            return ResponseEntity.notFound().build();
        }
        NoleggioDTO nuovoNoleggio = noleggioService.createNoleggio(createNoleggioRequest);
        return ResponseEntity.ok(nuovoNoleggio);
    }

    // rotta cancellazione noleggio dell'acquirente
    @DeleteMapping("/{acquirenteId}/noleggi/remove/{noleggioId}")
    public ResponseEntity<String> deleteNoleggioByAcquirenteId(
            @PathVariable Long acquirenteId,
            @PathVariable Long noleggioId) {
        List<Noleggio> noleggi = noleggioService.findByAcquirente(acquirenteId);
        for (Noleggio noleggio : noleggi) {
            if (noleggio.getNoleggioId().equals(noleggioId)) {
                noleggioService.deleteNoleggio(noleggioId);
                return ResponseEntity.ok("Noleggio eliminato correttamente.");
            }
        }
        return ResponseEntity.notFound().build();
    }

}
