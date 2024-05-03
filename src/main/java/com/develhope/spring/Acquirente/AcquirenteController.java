package com.develhope.spring.Acquirente;

import com.develhope.spring.Noleggio.Noleggio;
import com.develhope.spring.Noleggio.NoleggioService;
import com.develhope.spring.Ordine.OrdineAcquisto;
import com.develhope.spring.Ordine.OrdineAcquistoService;
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

    @Autowired
    private NoleggioService noleggioService;

    @Autowired
    private OrdineAcquistoService ordineAcquistoService;

    @PostMapping("/add")
    public ResponseEntity<String> addAcquirente(@RequestBody Acquirente acquirente) {
        acquirenteService.addAcquirente(acquirente);
        return ResponseEntity.ok("Acquirente aggiunto correttamente.");
    }

    @DeleteMapping("/remove/{id}")
    public ResponseEntity<String> deleteAcquirenteById(@PathVariable Long id) {
        Optional<Acquirente> optionalAcquirente = acquirenteService.deleteById(id);
        if(optionalAcquirente.isPresent()) {
            return ResponseEntity.ok("Acquirente eliminato.");
        }
        return ResponseEntity.badRequest().body("Acquirente non trovato.");
    }

    @GetMapping("/getAll")
    public List<Acquirente> findAllAcquirenti() {
        return acquirenteService.getAllAcquirenti();
    }

    @PutMapping("/set/{id}")
    public ResponseEntity<String> setAcquirenteById(@PathVariable Long id, @RequestBody Acquirente acquirenteMod) {
        Acquirente acquirente = acquirenteService.updateAcquirente(id, acquirenteMod);
        if(acquirente != null) {
            return ResponseEntity.ok("Acquirente aggiornato");
        }
        return ResponseEntity.badRequest().body("Acquirente non trovato.");
    }

    @GetMapping("/getById/{id}")
    public Optional<Acquirente> getAcquirenteById(@PathVariable Long id) {
        return acquirenteService.getById(id);
    }

    @GetMapping("/{acquirenteId}/noleggi")
    public ResponseEntity<List<Noleggio>> getNoleggiByAcquirenteId(@PathVariable Long acquirenteId) {
        List<Noleggio> noleggi = noleggioService.findByAcquirente(acquirenteId);
        if (noleggi.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(noleggi);
    }

    @PostMapping("/{acquirenteId}/noleggi/add")
    public ResponseEntity<Noleggio> addNoleggio(@PathVariable Long acquirenteId, @RequestBody Noleggio noleggio) {
        Acquirente acquirente = acquirenteService.getById(acquirenteId).orElse(null);
        if (acquirente == null) {
            return ResponseEntity.notFound().build();
        }
        noleggio.setAcquirente(acquirente);
        Noleggio nuovoNoleggio = noleggioService.addNoleggio(noleggio);
        return ResponseEntity.ok(nuovoNoleggio);
    }

    @DeleteMapping("/{acquirenteId}/noleggi/remove/{noleggioId}")
    public ResponseEntity<String> deleteNoleggioByAcquirenteId(@PathVariable Long acquirenteId, @PathVariable Long noleggioId) {
        List<Noleggio> noleggi = noleggioService.findByAcquirente(acquirenteId);
        for (Noleggio noleggio : noleggi) {
            if (noleggio.getNoleggioId().equals(noleggioId)) {
                noleggioService.deleteNoleggio(noleggioId);
                return ResponseEntity.ok("Noleggio eliminato correttamente.");
            }
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping("/{acquirenteId}/ordini")
    public ResponseEntity<List<OrdineAcquisto>> getOrdiniByAcquirenteId(@PathVariable Long acquirenteId) {
        List<OrdineAcquisto> ordini = ordineAcquistoService.findOrdiniByAcquirenteId(acquirenteId);
        if (ordini.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(ordini);
    }

    @PostMapping("/{acquirenteId}/ordini/add")
    public ResponseEntity<OrdineAcquisto> addOrdine(@PathVariable Long acquirenteId, @RequestBody OrdineAcquisto ordine) {
        Acquirente acquirente = acquirenteService.getById(acquirenteId).orElse(null);
        if (acquirente == null) {
            return ResponseEntity.notFound().build();
        }
        ordine.setAcquirente(acquirente);
        OrdineAcquisto nuovoOrdine = ordineAcquistoService.createOrdine(ordine);
        return ResponseEntity.ok(nuovoOrdine);
    }

    @DeleteMapping("/{acquirenteId}/ordini/remove/{ordineId}")
    public ResponseEntity<String> deleteOrdineByAcquirenteId(@PathVariable Long acquirenteId, @PathVariable Long ordineId) {
        List<OrdineAcquisto> ordini = ordineAcquistoService.findOrdiniByAcquirenteId(acquirenteId);
        for (OrdineAcquisto ordine : ordini) {
            if (ordine.getOrdineId().equals(ordineId)) {
                ordineAcquistoService.deleteOrdine(ordineId);
                return ResponseEntity.ok("Ordine eliminato correttamente.");
            }
        }
        return ResponseEntity.notFound().build();
    }
}
