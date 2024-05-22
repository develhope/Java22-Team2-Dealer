package com.develhope.spring.controller;

import com.develhope.spring.DTOs.Vehicle.CreateVehicleRequest;
import com.develhope.spring.DTOs.Vehicle.VehicleDTO;
import com.develhope.spring.entity.Vehicle;
import com.develhope.spring.entity.enums.Allestimento;
import com.develhope.spring.entity.enums.TipoOrdine;
import com.develhope.spring.entity.enums.TipoVeicolo;
import com.develhope.spring.entity.enums.VehicleCondition;
import com.develhope.spring.service.VehicleService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@RestController
@RequestMapping("/veicolo")
public class VehicleController {

    @Autowired
    private VehicleService vehicleService;

    //rotta creazione veicolo OK
    @Operation(summary = "Create a new Vehicle",
            description = "This endpoint allows to create a new Vehicle by providing the necessary information in the request body.")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200", description = "Successfully created Vehicle",
                    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = VehicleDTO.class))}),
            @ApiResponse(responseCode = "400", description = "something goes wrong")
    })
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
    @Operation(summary = "Delete a new Customer",
            description = "This endpoint allows to delete a vehicle by providing the necessary id in the root's path variable.")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200", description = "Successfully deleted a vehicle",
                    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = VehicleDTO.class))}),
            @ApiResponse(responseCode = "40a", description = "not found")
    })
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteVehicle(@PathVariable Long id) {
        return vehicleService.deleteVehicle(id);
    }

    //rotta ricerca veicoli da marca OK
    @Operation(summary = "Get a vehicle by brand",
            description = "This endpoint allows to find a vehicle by providing the necessary root's path variable.")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200", description = "Ok",
                    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = VehicleDTO.class))}),
            @ApiResponse(responseCode = "400", description = "something goes wrong")
    })
    @GetMapping("/getbymarca/{marca}")
    public ResponseEntity<?> getByMarca(@PathVariable String marca) {
        List<Vehicle> vehicles = vehicleService.searchByMarca(marca);
        if (vehicles.isEmpty()) {
            return ResponseEntity.status(400).body("something goes wrong");
        } else {
            return ResponseEntity.ok().body(vehicles);
        }
    }
    // rotta ricerca veicolo da ID
    @Operation(summary = "Get a vehicle by id",
            description = "This endpoint allows to find a vehicle by providing the necessary root's path variable.")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200", description = "Ok",
                    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = VehicleDTO.class))}),
            @ApiResponse(responseCode = "400", description = "something goes wrong")
    })
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
// rotta ricerca veicoli per cilindrata
    @Operation(summary = "Get a vehicle by displacement",
            description = "This endpoint allows to find a vehicle by providing the necessary root's path variable.")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200", description = "Ok",
                    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = VehicleService.class))}),
            @ApiResponse(responseCode = "400", description = "Invalid input or missing required fields")
    })
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
// ricerca veicolo sulla base del tipo
    @Operation(summary = "Get a vehicle by type",
            description = "This endpoint allows to find a vehicle by providing the necessary root's path variable.")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200", description = "Ok",
                    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = VehicleService.class))}),
            @ApiResponse(responseCode = "400", description = "Tipo veicolo non valido: \" + tipoVeicolo")
    })
    @GetMapping("/tipoVeicolo/{tipoVeicolo}")
    public List<Vehicle> getByTipoVeicolo(@PathVariable String tipoVeicolo) {
        TipoVeicolo tipo = switch (tipoVeicolo.toUpperCase()) {
            case "SCOOTER" -> TipoVeicolo.SCOOTER;
            case "FURGONE" -> TipoVeicolo.FURGONE;
            case "AUTO" -> TipoVeicolo.AUTO;
            case "MOTO" -> TipoVeicolo.MOTO;
            default -> throw new IllegalArgumentException("Tipo veicolo non valido: " + tipoVeicolo);
        };
        return vehicleService.searchByTipoVeicolo(tipo);
    }

    // ricerca veicolo per colore
    @Operation(summary = "Get a vehicle by colour",
            description = "This endpoint allows to find a vehicle by providing the necessary root's path variable.")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200", description = "Ok",
                    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = VehicleService.class))}),
            @ApiResponse(responseCode = "400", description = "something goes wrong")
    })
    @GetMapping("/colore/{colore}")
    public ResponseEntity<?> getByColore(@PathVariable String colore) {
        List<Vehicle> vehicles = vehicleService.searchByColore(colore);
        if (vehicles.isEmpty()) {
            return ResponseEntity.status(400).body("something goes wrong");
        } else {
            return ResponseEntity.ok().body(vehicles);
        }
    }
// ricerca veicolo per potenza
    @Operation(summary = "Get a vehicle by horsepower",
            description = "This endpoint allows to find a vehicle by providing the necessary root's path variable.")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200", description = "Ok",
                    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = VehicleService.class))}),
            @ApiResponse(responseCode = "40a", description = "not found")
    })
    @GetMapping("/potenza/{potenza}")
    public ResponseEntity<List<Vehicle>> getByPotenza(@PathVariable int potenza) {
        List<Vehicle> vehicles = vehicleService.searchByPotenza(potenza);
        if (vehicles.isEmpty()) {
            return ResponseEntity.notFound().build();
        } else {
            return ResponseEntity.ok(vehicles);
        }
    }

    // ricerca veicolo per tipologia di cambio
    @Operation(summary = "Get a vehicle by shifter method",
            description = "This endpoint allows to find a vehicle by providing the necessary root's path variable.")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200", description = "Ok",
                    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = VehicleService.class))}),
            @ApiResponse(responseCode = "400", description = "something goes wrong")
    })
    @GetMapping("/cambio/{cambio}")
    public ResponseEntity<?> getByCambio(@PathVariable String cambio) {
        List<Vehicle> vehicles = vehicleService.searchByTipoDiCambio(cambio);
        if (vehicles.isEmpty()) {
            return ResponseEntity.status(400).body("something goes wrong");
        } else {
            return ResponseEntity.ok().body(vehicles);
        }
    }

    // ricerca veicolo in base alla tipologia di alimentazione
    @Operation(summary = "Get a vehicle by fuel type",
            description = "This endpoint allows to find a vehicle by providing the necessary root's path variable.")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200", description = "Ok",
                    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = VehicleDTO.class))}),
            @ApiResponse(responseCode = "400", description = "something goes wrong")
    })
    @GetMapping("/alimentazione/{alimentazione}")
    public ResponseEntity<?> getByAlimentazione(@PathVariable String alimentazione) {
        List<Vehicle> vehicles = vehicleService.searchByAlimentazione(alimentazione);
        if (vehicles.isEmpty()) {
            return ResponseEntity.status(400).body("something goes wrong");
        } else {
            return ResponseEntity.ok().body(vehicles);
        }
    }

    // ricerca veicolo sulla base degli accessori
    @Operation(summary = "Get a vehicle by accessories",
            description = "This endpoint allows to find a vehicle by providing the necessary root's path variable.")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200", description = "Ok",
                    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = VehicleDTO.class))}),
            @ApiResponse(responseCode = "400", description = "something goes wrong")
    })
    @GetMapping("/accessori/{accessori}")
    public ResponseEntity<?> getByAccessori(@PathVariable String accessori) {
        List<Vehicle> vehicles = vehicleService.searchByAccessori(accessori);
        if (vehicles.isEmpty()) {
            return ResponseEntity.status(400).body("something goes wrong");
        } else {
            return ResponseEntity.ok().body(vehicles);
        }
    }

    // ricerca veicolo per prezzo
    @Operation(summary = "Get a vehicle by price",
            description = "This endpoint allows to find a vehicle by providing the necessary root's path variable.")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200", description = "Ok",
                    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = VehicleDTO.class))}),
            @ApiResponse(responseCode = "404", description = "not found")
    })
    @GetMapping("/prezzo/{prezzo}")
    public ResponseEntity<List<Vehicle>> getByPrezzo(@PathVariable BigDecimal prezzo) {
        List<Vehicle> vehicles = vehicleService.searchByPrezzo(prezzo);
        if (vehicles.isEmpty()) {
            return ResponseEntity.notFound().build();
        } else {
            return ResponseEntity.ok(vehicles);
        }
    }

    // ricerca veicolo per anno immatricolazione
    @Operation(summary = "Get a vehicle by matriculation",
            description = "This endpoint allows to find a vehicle by providing the necessary root's path variable.")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200", description = "Ok",
                    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = VehicleDTO.class))}),
            @ApiResponse(responseCode = "404", description = "not found")
    })
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

    // ricerca veicolo sulla base dell allestimento
    @Operation(summary = "Get a vehicle by setup",
            description = "This endpoint allows to find a vehicle by providing the necessary root's path variable.")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200", description = "Ok",
                    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = VehicleDTO.class))}),
            @ApiResponse(responseCode = "400", description = "\"Tipo di allestimento non valido: \" + allestimento")
    })
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

// ricerca veicolo sulla base del tipo di ordine disponibile
    @Operation(summary = "Get a vehicle by order type available",
            description = "This endpoint allows to find a vehicle by providing the necessary root's path variable.")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200", description = "Ok",
                    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = VehicleDTO.class))}),
            @ApiResponse(responseCode = "400", description = "Invalid input or missing required fields")
    })
    @GetMapping("/tipoOrdine/{tipoOrdine}")
    public List<Vehicle> getByTipoOrdine (@PathVariable String tipoOrdine){
        TipoOrdine tipo = switch (tipoOrdine.toUpperCase()) {
            case "ACQUISTABILE" -> TipoOrdine.ACQUISTABILE;
            case "ORDINABILE" -> TipoOrdine.ORDINABILE;
            default -> TipoOrdine.NON_DISPONIBILE;
        };
        return vehicleService.searchByTipoOrdine(tipo);
    }

//  ricerca veicoli in base alla condizione
    @Operation(summary = "Get a vehicle by condition",
            description = "This endpoint allows to find a vehicle by providing the necessary root's path variable.")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200", description = "Ok",
                    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = VehicleDTO.class))}),
            @ApiResponse(responseCode = "400", description = "\"Tipo di condizione non valida: \" + vehicleCondition")
    })
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
