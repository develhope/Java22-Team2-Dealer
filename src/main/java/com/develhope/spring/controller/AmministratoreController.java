package com.develhope.spring.controller;

import com.develhope.spring.DTOs.Acquirente.CreateAcquirenteRequest;
import com.develhope.spring.DTOs.Noleggio.CreateNoleggioRequest;
import com.develhope.spring.DTOs.Noleggio.NoleggioDTO;
import com.develhope.spring.DTOs.Ordine.CreateOrdineAcquistoRequest;
import com.develhope.spring.DTOs.Ordine.OrdineAcquistoDTO;
import com.develhope.spring.DTOs.Vehicle.CreateVehicleRequest;
import com.develhope.spring.DTOs.Vehicle.VehicleDTO;
import com.develhope.spring.DTOs.Venditore.CreateVenditoreRequest;
import com.develhope.spring.entity.*;
import com.develhope.spring.entity.enums.VehicleCondition;
import com.develhope.spring.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/amministratore")
public class AmministratoreController {

    @Autowired
    private AmministratoreService amministratoreService;

    @Autowired
    private VehicleService vehicleService;

    @Autowired
    private OrdineAcquistoService ordineAcquistoService;

    @Autowired
    private NoleggioService noleggioService;

    @Autowired
    private AcquirenteService acquirenteService;

    @Autowired
    private VenditoreService venditoreService;

//    @PostMapping("/add")
//    public ResponseEntity<?> addAmministratore(@Validated @RequestBody CreateAmministratoreRequest createAmministratoreRequest) {
//        AmministratoreDTO result = amministratoreService.createAmministratore(createAmministratoreRequest);
//        if (result == null) {
//            return ResponseEntity.status(400).body("something goes wrong");
//        } else {
//            return ResponseEntity.ok().body(result);
//        }
//    }
//
//    @DeleteMapping("/remove/{id}")
//    public ResponseEntity<String> deleteById(@PathVariable Long id) {
//        Optional<Amministratore> optionalAmministratore = amministratoreService.deleteById(id);
//        if(optionalAmministratore.isPresent()) {
//            return ResponseEntity.ok("Amministratore eliminato.");
//
//        }
//        return ResponseEntity.badRequest().body("Amministratore non trovato.");
//    }
//    @GetMapping("/getAll")
//    public List<Amministratore> findAllAmministratori() {
//        return amministratoreService.getAllAmministratori();
//    }
//
//    @PutMapping("/set/{id}")
//    public ResponseEntity<String> setById(@PathVariable Long id, @RequestBody Amministratore amministratoreMod) {
//        Amministratore amministratore = amministratoreService.updateAmministratore(id, amministratoreMod);
//        if(amministratore != null) {
//            return ResponseEntity.ok("amministratore aggiornato");
//        }
//        return ResponseEntity.badRequest().body("amministratore non trovato.");
//    }
//
//    @GetMapping("/getById/{id}")
//    public Optional<Amministratore> getById(@PathVariable Long id) {
//        return amministratoreService.getById(id);
//    }
//
//    @PostMapping("/register/amministratore")
//    public ResponseEntity<?> registerAmministratore(@RequestBody CreateAmministratoreRequest createAmministratoreRequest) {
//        Amministratore amministratore = amministratoreService.register(createAmministratoreRequest);
//        if (amministratore == null) {
//            return ResponseEntity.badRequest().body("Errore durante la registrazione dell'amministratore");
//        }
//        return ResponseEntity.ok("Amministratore registrato con successo");
//    }

    //rotta creazione veicolo OK
    @PostMapping("/addVehicle")
    public ResponseEntity<?> createVehicle(@Validated @RequestBody CreateVehicleRequest createVehicleRequest) {
        VehicleDTO result = vehicleService.createVehicle(createVehicleRequest);
        if (result == null) {
            return ResponseEntity.status(400).body("something goes wrong");
        } else {
            return ResponseEntity.ok().body(result);
        }
    }

    //rotta update veicolo OK
    @PutMapping("/updateVehicle/{id}")
    public ResponseEntity<?> updateVehicle(@PathVariable Long id, @Validated @RequestBody CreateVehicleRequest updateVehicleRequest) {
        Optional<Vehicle> optionalVehicle = vehicleService.findById(id);
        if (optionalVehicle.isPresent()) {
            Vehicle vehicle = optionalVehicle.get();
            vehicle.setMarca(updateVehicleRequest.getMarca());
            vehicle.setTipoVeicolo(updateVehicleRequest.getTipoVeicolo());
            vehicle.setModello(updateVehicleRequest.getModello());
            vehicle.setCilindrata(updateVehicleRequest.getCilindrata());
            vehicle.setColore(updateVehicleRequest.getColore());
            vehicle.setPotenza(updateVehicleRequest.getPotenza());
            vehicle.setTipoDiCambio(updateVehicleRequest.getTipoDiCambio());
            vehicle.setAnnoImmatricolazione(updateVehicleRequest.getAnnoImmatricolazione());
            vehicle.setAlimentazione(updateVehicleRequest.getAlimentazione());
            vehicle.setPrezzo(updateVehicleRequest.getPrezzo());
            vehicle.setAllestimento(updateVehicleRequest.getAllestimento());
            vehicle.setAccessori(updateVehicleRequest.getAccessori());
            vehicle.setVehicleCondition(updateVehicleRequest.getVehicleCondition());
            vehicle.setOrdinabile(updateVehicleRequest.getOrdinabile());
            vehicle.setAcquistabile(updateVehicleRequest.getAcquistabile());
            vehicle.setNonDisponibile(updateVehicleRequest.getNonDisponibile());

            vehicleService.addVeicolo(vehicle);

            return ResponseEntity.ok("Veicolo aggiornato con successo");
        } else {
            return ResponseEntity.status(404).body("Veicolo non trovato");
        }
    }

    //rotta cancellazione veicolo da id OK
    @DeleteMapping("/deleteVehicle/{id}")
    public ResponseEntity<?> deleteVehicle(@PathVariable Long id) {
        return vehicleService.deleteVehicle(id);
    }

    //rotta per modifica condizione veicolo OK
    @PutMapping("/changeVehicleCondition/{id}")
    public ResponseEntity<?> changeVehicleCondition(@PathVariable Long id, @RequestParam VehicleCondition newCondition) {
        Optional<Vehicle> optionalVehicle = vehicleService.findById(id);
        if (optionalVehicle.isPresent()) {
            Vehicle vehicle = optionalVehicle.get();
            vehicle.setVehicleCondition(newCondition);

            vehicleService.addVeicolo(vehicle);

            return ResponseEntity.ok("Stato del veicolo cambiato con successo");
        } else {
            return ResponseEntity.status(404).body("Veicolo non trovato");
        }
    }

    //rotta per la creazione di un ordine per un acquirente OK
    @PostMapping("/acquirente/{acquirenteId}/createOrdine")
    public ResponseEntity<?> createOrdineForAcquirente(@PathVariable Long acquirenteId, @Validated @RequestBody CreateOrdineAcquistoRequest createOrdineAcquistoRequest) {
        OrdineAcquistoDTO result = ordineAcquistoService.createOrdineForAcquirente(acquirenteId, createOrdineAcquistoRequest);
        if (result == null) {
            return ResponseEntity.status(400).body("Impossibile creare l'ordine per l'acquirente con ID: " + acquirenteId);
        } else {
            return ResponseEntity.ok().body(result);
        }
    }

    //rotta per l'eliminazione di un ordine per un acquirente OK
    @DeleteMapping("/acquirente/{acquirenteId}/deleteOrdine/{id}")
    public ResponseEntity<?> deleteOrdineForAcquirente(@PathVariable Long acquirenteId, @PathVariable Long id) {
        Optional<OrdineAcquisto> optionalOrdine = ordineAcquistoService.deleteOrdineForAcquirente(acquirenteId, id);
        if (optionalOrdine.isPresent()) {
            return ResponseEntity.ok("Ordine eliminato con successo per l'acquirente con ID: " + acquirenteId);
        } else {
            return ResponseEntity.status(404).body("Ordine non trovato per l'acquirente con ID: " + acquirenteId);
        }
    }

    //rotta per l'update di un ordine per un acquirente OK
    @PutMapping("/acquirente/{acquirenteId}/updateOrdine/{id}")
    public ResponseEntity<?> updateOrdineForAcquirente(@PathVariable Long acquirenteId, @PathVariable Long id, @RequestBody CreateOrdineAcquistoRequest createOrdineAcquistoRequest) {
        Optional<OrdineAcquisto> optionalOrdine = ordineAcquistoService.updateOrdineForAcquirente(acquirenteId, id, createOrdineAcquistoRequest);
        if (optionalOrdine.isPresent()) {
            return ResponseEntity.ok("OrdineAcquisto aggiornato con successo per l'acquirente con ID: " + acquirenteId);
        } else {
            return ResponseEntity.status(404).body("OrdineAcquisto non trovato per l'acquirente con ID: " + acquirenteId);
        }
    }

    //rotta per la creazione di un noleggio per un acquirente NO
    @PostMapping("/acquirente/{acquirenteId}/createNoleggio")
    public ResponseEntity<?> createNoleggioForAcquirente(@PathVariable Long acquirenteId, @Validated @RequestBody CreateNoleggioRequest createNoleggioRequest) {
        Acquirente acquirente = acquirenteService.getById(acquirenteId).orElse(null);
        if (acquirente == null) {
            return ResponseEntity.badRequest().body("Acquirente non trovato con ID: " + acquirenteId);
        }
        NoleggioDTO result = noleggioService.createNoleggioForAcquirente(acquirenteId, createNoleggioRequest);
        if (result != null) {
            return ResponseEntity.ok().body(result);
        } else {
            return ResponseEntity.status(400).body("Impossibile creare il noleggio per l'acquirente con ID: " + acquirenteId);
        }
    }

    //rotta per l'eliminazione di un noleggio per un acquirente OK
    @DeleteMapping("/acquirente/{acquirenteId}/deleteNoleggio/{id}")
    public ResponseEntity<?> deleteNoleggioForAcquirente(@PathVariable Long acquirenteId, @PathVariable Long id) {
        Optional<Noleggio> optionalNoleggio = noleggioService.deleteNoleggioForAcquirente(acquirenteId, id);
        if (optionalNoleggio.isPresent()) {
            return ResponseEntity.ok("Noleggio eliminato con successo per l'acquirente con ID: " + acquirenteId);
        } else {
            return ResponseEntity.status(404).body("Noleggio non trovato per l'acquirente con ID: " + acquirenteId);
        }
    }

    //rotta per l'update di un noleggio per un acquirente OK
    @PutMapping("/acquirente/{acquirenteId}/updateNoleggio/{id}")
    public ResponseEntity<?> updateNoleggioForAcquirente(@PathVariable Long acquirenteId, @PathVariable Long id, @RequestBody CreateNoleggioRequest createNoleggioRequest) {
        Optional<Noleggio> optionalNoleggio = noleggioService.updateNoleggioForAcquirente(acquirenteId, id, createNoleggioRequest);
        if (optionalNoleggio.isPresent()) {
            return ResponseEntity.ok("Noleggio aggiornato con successo per l'acquirente con ID: " + acquirenteId);
        } else {
            return ResponseEntity.status(404).body("Noleggio non trovato per l'acquirente con ID: " + acquirenteId);
        }
    }

    //rotta per la creazione di un acquisto per un acquirente NO
    @PostMapping("/acquirente/{acquirenteId}/createAcquisto")
    public ResponseEntity<?> createAcquistoForAcquirente(@PathVariable Long acquirenteId, @Validated @RequestBody CreateOrdineAcquistoRequest createOrdineAcquistoRequest) {
        OrdineAcquistoDTO result = ordineAcquistoService.createAcquistoForAcquirente(acquirenteId, createOrdineAcquistoRequest);
        if (result == null) {
            return ResponseEntity.status(400).body("Impossibile creare l'acquisto per l'acquirente con ID: " + acquirenteId);
        } else {
            return ResponseEntity.ok().body(result);
        }
    }

    //rotta per l'eliminazione di un acquisto per un acquirente OK
    @DeleteMapping("/acquirente/{acquirenteId}/deleteAcquisto/{id}")
    public ResponseEntity<?> deleteAcquistoForAcquirente(@PathVariable Long acquirenteId, @PathVariable Long id) {
        Optional<OrdineAcquisto> optionalAcquisto = ordineAcquistoService.deleteAcquistoForAcquirente(acquirenteId, id);
        if (optionalAcquisto.isPresent()) {
            return ResponseEntity.ok("Acquisto eliminato con successo per l'acquirente con ID: " + acquirenteId);
        } else {
            return ResponseEntity.status(404).body("Acquisto non trovato per l'acquirente con ID: " + acquirenteId);
        }
    }

    //rotta per l'update di un acquisto per un acquirente OK
    @PutMapping("/acquirente/{acquirenteId}/updateAcquisto/{id}")
    public ResponseEntity<?> updateAcquistoForAcquirente(@PathVariable Long acquirenteId, @PathVariable Long id, @RequestBody CreateOrdineAcquistoRequest createOrdineAcquistoRequest) {
        Optional<OrdineAcquisto> optionalAcquisto = ordineAcquistoService.updateAcquistoForAcquirente(acquirenteId, id, createOrdineAcquistoRequest);
        if (optionalAcquisto.isPresent()) {
            return ResponseEntity.ok("Acquisto aggiornato con successo per l'acquirente con ID: " + acquirenteId);
        } else {
            return ResponseEntity.status(404).body("Acquisto non trovato per l'acquirente con ID: " + acquirenteId);
        }
    }

// ?  Verificare un venditore quante vendite ha fatto in un determinato periodo di tempo

// ?  Verificare un venditore quanti soldi ha generato in un determinato periodo di tempo

// ?  Verificare il guadagno del salone in un determinato periodo

    //rotta per la verifica dello stato di un veicolo
    @GetMapping("/vehicleCondition/{id}")
    public ResponseEntity<?> getVehicleCondition(@PathVariable Long id) {
        return vehicleService.getVehicleCondition(id);
    }

    // rotta cancellazione acquirente
    @DeleteMapping("/removeAcquirente/{id}")
    public ResponseEntity<String> deleteAcquirenteById(@PathVariable Long id) {
        Optional<Acquirente> optionalAcquirente = acquirenteService.deleteById(id);
        if (optionalAcquirente.isPresent()) {
            return ResponseEntity.ok("Acquirente eliminato.");
        }
        return ResponseEntity.badRequest().body("Acquirente non trovato.");
    }

    // rotta per la modifica di un acquirente OK
    @PutMapping("/updateAcquirente/{id}")
    public ResponseEntity<?> updateAcquirente(@PathVariable Long id, @RequestBody CreateAcquirenteRequest updateAcquirenteRequest) {
        Acquirente acquirente = acquirenteService.updateAcquirente(id, updateAcquirenteRequest);
        if (acquirente != null) {
            return ResponseEntity.ok("Acquirente aggiornato con successo");
        } else {
            return ResponseEntity.status(404).body("Acquirente non trovato");
        }
    }

    // rotta cancellazione venditore OK
    @DeleteMapping("/removeVenditore/{id}")
    public ResponseEntity<String> deleteVenditoreById(@PathVariable Long id) {
        Optional<Venditore> optionalVenditore = venditoreService.deleteById(id);
        if (optionalVenditore.isPresent()) {
            return ResponseEntity.ok("Venditore eliminato.");
        }
        return ResponseEntity.badRequest().body("Venditore non trovato.");
    }

    // rotta per la modifica di un venditore OK
    @PutMapping("/updateVenditore/{id}")
    public ResponseEntity<?> updateVenditore(@PathVariable Long id, @RequestBody CreateVenditoreRequest updateVenditoreRequest) {
        Venditore venditore = venditoreService.updateVenditore(id, updateVenditoreRequest);
        if (venditore != null) {
            return ResponseEntity.ok("Venditore aggiornato con successo");
        } else {
            return ResponseEntity.status(404).body("Venditore non trovato");
        }
    }

// ?  Ottenere il vehicle più venduto in un dato periodo

// ?  Ottenere il vehicle con valore più alto venduto fino a quel dato istante

// ?  Ottenere il vehicle più ricercato/ordinato

}