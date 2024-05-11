package com.develhope.spring.controller;

import com.develhope.spring.DTOs.Acquirente.AcquirenteDTO;
import com.develhope.spring.DTOs.Acquirente.CreateAcquirenteRequest;
import com.develhope.spring.DTOs.Noleggio.CreateNoleggioRequest;
import com.develhope.spring.DTOs.Noleggio.NoleggioDTO;
import com.develhope.spring.DTOs.Ordine.CreateOrdineAcquistoRequest;
import com.develhope.spring.DTOs.Ordine.OrdineAcquistoDTO;
import com.develhope.spring.Models.NoleggioModel;
import com.develhope.spring.entity.Acquirente;
import com.develhope.spring.entity.Noleggio;
import com.develhope.spring.service.AcquirenteService;
import com.develhope.spring.service.NoleggioService;
import com.develhope.spring.service.OrdineAcquistoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/acquirente")
public class AcquirenteController {

    @Autowired
    private AcquirenteService acquirenteService;

    @Autowired
    private NoleggioService noleggioService;

    @Autowired
    private OrdineAcquistoService ordineAcquistoService;

    // rotta creazione acquirente
    @PostMapping("/add")
    public ResponseEntity<?> createAcquirente(@Validated @RequestBody CreateAcquirenteRequest createAcquirenteRequest) {
        AcquirenteDTO result = acquirenteService.createAcquirente(createAcquirenteRequest);
        if (result == null) {
            return ResponseEntity.status(400).body("something goes wrong");
        } else {
            return ResponseEntity.ok().body(result);
        }
    }


    // rotta cancellazione acquirente
    @DeleteMapping("/remove/{id}")
    public ResponseEntity<String> deleteAcquirenteById(@PathVariable Long id) {
        Optional<Acquirente> optionalAcquirente = acquirenteService.deleteById(id);
        if (optionalAcquirente.isPresent()) {
            return ResponseEntity.ok("Acquirente eliminato.");
        }
        return ResponseEntity.badRequest().body("Acquirente non trovato.");
    }

    //rotta ricerca tutti gli acquirenti
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

    // rotta ricerca noleggi d acquirente id
    @GetMapping("/{acquirenteId}/noleggi")
    public ResponseEntity<List<NoleggioDTO>> getNoleggi(@PathVariable Long acquirenteId) {
        List<Noleggio> noleggi = noleggioService.findByAcquirente(acquirenteId);
        if (noleggi.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        List<NoleggioDTO> noleggiDto = noleggi.stream()
                .map(NoleggioModel::entityToModel)
                .map(NoleggioModel::modelToDto)
                .collect(Collectors.toList());
        return ResponseEntity.ok(noleggiDto);
    }

    // rotta creazione noleggio dell'acquirente
    @PostMapping("/{acquirenteId}/noleggi/add")
    public ResponseEntity<?> addNoleggio(@Validated @PathVariable Long acquirenteId, @Validated @RequestBody CreateNoleggioRequest createNoleggioRequest) {
        Acquirente acquirente = acquirenteService.getById(acquirenteId).orElse(null);
        if (acquirente == null) {
            return ResponseEntity.badRequest().body("Acquirente non trovato con ID: " + acquirenteId);
        }
        NoleggioDTO nuovoNoleggio = noleggioService.createNoleggioForAcquirente(acquirenteId, createNoleggioRequest);
        if (nuovoNoleggio == null) {
            return ResponseEntity.badRequest().body("Impossibile creare il noleggio");
        }
        return ResponseEntity.ok(nuovoNoleggio);
    }

    // rotta cancellazione noleggio
    @DeleteMapping("/{acquirenteId}/noleggi/remove/{noleggioId}")
    public ResponseEntity<String> deleteNoleggioByAcquirenteId(
            @PathVariable Long acquirenteId,
            @PathVariable Long noleggioId) {
        Noleggio noleggio = noleggioService.findById(noleggioId).orElse(null);
        if (noleggio == null || !noleggio.getAcquirente().getId().equals(acquirenteId)) {
            return ResponseEntity.notFound().build();
        }
        noleggioService.deleteNoleggio(noleggioId);
        return ResponseEntity.ok("Noleggio eliminato correttamente.");
    }

    // rotta creazione ordine dell'acquirente
    @PostMapping("/{acquirenteId}/ordine/add")
    public ResponseEntity<?> addOrdine(@Validated @PathVariable Long acquirenteId, @Validated @RequestBody CreateOrdineAcquistoRequest createOrdineAcquistoRequest) {
        Acquirente acquirente = acquirenteService.getById(acquirenteId).orElse(null);
        if (acquirente == null) {
            return ResponseEntity.badRequest().body("Acquirente non trovato con ID: " + acquirenteId);
        }
        OrdineAcquistoDTO nuovoOrdine = ordineAcquistoService.createOrdineAcquisto(acquirenteId, createOrdineAcquistoRequest);
        if (nuovoOrdine == null) {
            return ResponseEntity.badRequest().body("Impossibile creare l'ordine");
        }
        return ResponseEntity.ok(nuovoOrdine);
    }

}
