package com.develhope.spring.controller;

import com.develhope.spring.DTOs.Vehicle.CreateVehicleRequest;
import com.develhope.spring.DTOs.Vehicle.VehicleDTO;
import com.develhope.spring.entity.Vehicle;
import com.develhope.spring.entity.enums.Allestimento;
import com.develhope.spring.entity.enums.TipoOrdine;
import com.develhope.spring.entity.enums.TipoVeicolo;
import com.develhope.spring.entity.enums.VehicleCondition;
import com.develhope.spring.service.VehicleService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.time.Year;
import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@RestController
@RequestMapping("/veicolo")
public class VehicleController {

    @Autowired
    private VehicleService vehicleService;

    //rotta creazione veicolo OK
    @PostMapping("/add")
    public ResponseEntity<?> createVehicle(@Validated @RequestBody CreateVehicleRequest createVehicleRequest) {
        VehicleDTO result = vehicleService.createVehicle(createVehicleRequest);
        if (result == null) {
            return ResponseEntity.status(400).body("something goes wrong");
        } else {
            return ResponseEntity.ok().body(result);
        }
    }

    //rotta cancellazione veicolo da id OK
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteVehicle(@PathVariable Long id) {
        return vehicleService.deleteVehicle(id);
    }

    //rotta ricerca veicoli da marca OK
    @GetMapping("/getbymarca/{marca}")
    public ResponseEntity<?> getByMarca(@PathVariable String marca) {
        List<Vehicle> vehicles = vehicleService.searchByMarca(marca);
        if (vehicles.isEmpty()) {
            return ResponseEntity.status(400).body("something goes wrong");
        } else {
            return ResponseEntity.ok().body(vehicles);
        }
    }

    @GetMapping("/getById/{id}")
    public Optional<Vehicle> findById(@PathVariable Long id) {
        return vehicleService.findById(id);
    }

    @GetMapping("/modello/{modello}")
    public ResponseEntity<?> getByModello(@PathVariable String modello) {
        List<Vehicle> vehicles = vehicleService.searchByModello(modello);
        if (vehicles.isEmpty()) {
            return ResponseEntity.status(400).body("something goes wrong");
        } else {
            return ResponseEntity.ok().body(vehicles);
        }
    }

    @GetMapping("/cilindrata/{cilindrata}")
    public ResponseEntity<?> getByCilindrataInRange(@PathVariable int cilindrata) {
        if (cilindrata >= 50 && cilindrata <= 3000) {
            List<Vehicle> vehicles = vehicleService.searchByCilindrata(cilindrata);
            if (vehicles.isEmpty()) {
                return new ResponseEntity<>("Nessun veicolo trovato con la cilindrata specificata", HttpStatus.NOT_FOUND);
            }
            return new ResponseEntity<>(vehicles, HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Cilindrata deve essere tra 50 e 3000", HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/tipoVeicolo/{tipoVeicolo}")
    public List<Vehicle> getByTipoVeicolo(@PathVariable String tipoVeicolo) {
        TipoVeicolo tipo = null;
        switch (tipoVeicolo.toUpperCase()) {
            case "SCOOTER":
                tipo = TipoVeicolo.SCOOTER;
                break;
            case "FURGONE":
                tipo = TipoVeicolo.FURGONE;
                break;
            case "AUTO":
                tipo = TipoVeicolo.AUTO;
                break;
            case "MOTO":
                tipo = TipoVeicolo.MOTO;
                break;
            default:
                throw new IllegalArgumentException("Tipo veicolo non valido: " + tipoVeicolo);
        }
        return vehicleService.searchByTipoVeicolo(tipo);
    }

    @GetMapping("/colore/{colore}")
    public ResponseEntity<?> getByColore(@PathVariable String colore) {
        List<Vehicle> vehicles = vehicleService.searchByColore(colore);
        if (vehicles.isEmpty()) {
            return ResponseEntity.status(400).body("something goes wrong");
        } else {
            return ResponseEntity.ok().body(vehicles);
        }
    }

    @GetMapping("/potenza/{potenza}")
    public ResponseEntity<List<Vehicle>> getByPotenza(@PathVariable int potenza) {
        List<Vehicle> vehicles = vehicleService.searchByPotenza(potenza);
        if (vehicles.isEmpty()) {
            return ResponseEntity.notFound().build();
        } else {
            return ResponseEntity.ok(vehicles);
        }
    }

    @GetMapping("/cambio/{cambio}")
    public ResponseEntity<?> getByCambio(@PathVariable String cambio) {
        List<Vehicle> vehicles = vehicleService.searchByTipoDiCambio(cambio);
        if (vehicles.isEmpty()) {
            return ResponseEntity.status(400).body("something goes wrong");
        } else {
            return ResponseEntity.ok().body(vehicles);
        }
    }

    @GetMapping("/alimentazione/{alimentazione}")
    public ResponseEntity<?> getByAlimentazione(@PathVariable String alimentazione) {
        List<Vehicle> vehicles = vehicleService.searchByAlimentazione(alimentazione);
        if (vehicles.isEmpty()) {
            return ResponseEntity.status(400).body("something goes wrong");
        } else {
            return ResponseEntity.ok().body(vehicles);
        }
    }

    @GetMapping("/accessori/{accessori}")
    public ResponseEntity<?> getByAccessori(@PathVariable String accessori) {
        List<Vehicle> vehicles = vehicleService.searchByAccessori(accessori);
        if (vehicles.isEmpty()) {
            return ResponseEntity.status(400).body("something goes wrong");
        } else {
            return ResponseEntity.ok().body(vehicles);
        }
    }

    @GetMapping("/prezzo/{prezzo}")
    public ResponseEntity<List<Vehicle>> getByPrezzo(@PathVariable BigDecimal prezzo) {
        List<Vehicle> vehicles = vehicleService.searchByPrezzo(prezzo);
        if (vehicles.isEmpty()) {
            return ResponseEntity.notFound().build();
        } else {
            return ResponseEntity.ok(vehicles);
        }
    }

    @GetMapping("/annoImmatricolazione/{annoImmatricolazione}")
    public ResponseEntity<?> getByAnnoImmatricolazione(@PathVariable("annoImmatricolazione") int annoImmatricolazione) {
        if (annoImmatricolazione < 1900 || annoImmatricolazione > 2024) {
            return ResponseEntity.badRequest().body("Inserire una data compresa tra 1900 e 2024");
        }
        List<Vehicle> vehicles = vehicleService.searchByAnnoImmatricolazione(annoImmatricolazione);
        if (vehicles.isEmpty()) {
            return ResponseEntity.notFound().build();
        } else {
            return ResponseEntity.ok(vehicles);
        }
    }

    @GetMapping("/allestimento/{allestimento}")
    public List<Vehicle> getByAllestimento (@PathVariable String allestimento){
        Allestimento tipo = switch (allestimento.toUpperCase()) {
            case "BASE" -> Allestimento.BASE;
            case "SPORT" -> Allestimento.SPORT;
            case "BUSINESS" -> Allestimento.BUSINESS;
            default -> throw new IllegalArgumentException("Tipo di allestimento non valido: " + allestimento);
        };
        return vehicleService.searchByAllestimento(tipo);
    }

    @GetMapping("/tipoOrdine/{tipoOrdine}")
    public List<Vehicle> getByTipoOrdine (@PathVariable String tipoOrdine){
        TipoOrdine tipo = switch (tipoOrdine.toUpperCase()) {
            case "ACQUISTABILE" -> TipoOrdine.ACQUISTABILE;
            case "ORDINABILE" -> TipoOrdine.ORDINABILE;
            default -> TipoOrdine.NON_DISPONIBILE;
        };
        return vehicleService.searchByTipoOrdine(tipo);
    }

    @GetMapping("/vehicleCondition/{vehicleCondition}")
    public List<Vehicle> getByVehicleCondition (@PathVariable String vehicleCondition){
        VehicleCondition tipo = switch (vehicleCondition.toUpperCase()) {
            case "NUOVO" -> VehicleCondition.NUOVO;
            case "USATO" -> VehicleCondition.USATO;
            default -> throw new IllegalArgumentException("Tipo di condizione non valida: " + vehicleCondition);
        };
        return vehicleService.searchByVehicleCondition(tipo);
    }

}
