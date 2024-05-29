package com.develhope.spring.Features.Controller;

import com.develhope.spring.Features.DTOs.Vehicle.CreateVehicleRequest;
import com.develhope.spring.Features.DTOs.Vehicle.VehicleDTO;
import com.develhope.spring.Features.Entity.User.Role;
import com.develhope.spring.Features.Entity.User.User;
import com.develhope.spring.Features.Entity.Vehicle.Vehicle;
import com.develhope.spring.Features.Entity.Vehicle.Allestimento;
import com.develhope.spring.Features.Entity.OrdineAcquisto.TipoOrdineAcquisto;
import com.develhope.spring.Features.Entity.Vehicle.TipoVeicolo;
import com.develhope.spring.Features.Entity.Vehicle.VehicleCondition;
import com.develhope.spring.Features.Service.VehicleService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.List;
import java.util.Optional;

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
            @ApiResponse(responseCode = "400", description = "Invalid data format")
    })
    @PostMapping("/add")
    public ResponseEntity<?> createVehicle(@Validated @RequestBody CreateVehicleRequest createVehicleRequest, @AuthenticationPrincipal User user) {
        if (user.getRole() == Role.AMMINISTRATORE) {
            VehicleDTO result = vehicleService.createVehicle(createVehicleRequest);
            if (result == null) {
                return ResponseEntity.status(400).body("Invalid data format");
            } else {
                return ResponseEntity.ok().body(result);
            }
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Only admins can create vehicles");
        }
    }

    //rotta update veicolo
    @Operation(summary = "update a vehicle",
            description = "This endpoint allows an administrator to update a vehicle.")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200", description = "Ok",
                    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = VehicleService.class))}),
            @ApiResponse(responseCode = "400", description = "Invalid input or missing required fields")
    })
    @PutMapping("/update/{id}")
    public ResponseEntity<?> updateVehicle(@PathVariable Long id, @AuthenticationPrincipal User user, @Validated @RequestBody Vehicle vehicleMod) {
        return vehicleService.updateVehicle(id, user, vehicleMod);
    }

    //rotta cancellazione veicolo da id
    @Operation(summary = "Delete vehicles by id",
            description = "This endpoint allows to delete a vehicle by providing the necessary id in the root's path variable.")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200", description = "Successfully deleted a vehicle",
                    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = VehicleDTO.class))}),
            @ApiResponse(responseCode = "400", description = "not found")
    })
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteVehicle(@PathVariable Long id, @AuthenticationPrincipal User user) {
        return vehicleService.deleteVehicle(id, user);
    }

    //rotta per modifica condizione veicolo OK
    @Operation(summary = "change vehicle condition",
            description = "This endpoint allows an administrator to change a vehicle's conditions.")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200", description = "Successfully modified a vehicle",
                    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = VehicleService.class))}),
            @ApiResponse(responseCode = "400", description = "Invalid input or missing required fields")
    })
    @PutMapping("/changeVehicleCondition/{id}")
    public ResponseEntity<?> changeVehicleCondition(@PathVariable Long id, @RequestParam VehicleCondition newCondition, @AuthenticationPrincipal User user) {
        return vehicleService.changeVehicleCondition(id, newCondition, user);
    }

    //ricerca veicoli in base alle caratteristiche
    @Operation(summary = "Search vehicles by caract")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200", description = "Successfully found vehicle",
                    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = VehicleService.class))}),
            @ApiResponse(responseCode = "403", description = "Only customers can perform searches")
    })
    @GetMapping("/searchCaract")
    public ResponseEntity<?> searchVehicles(@RequestParam(required = false) String marca,
                                            @RequestParam(required = false) String modello,
                                            @RequestParam(required = false) TipoVeicolo tipoVeicolo,
                                            @RequestParam(required = false) Integer cilindrata,
                                            @RequestParam(required = false) String colore,
                                            @RequestParam(required = false) Integer potenza,
                                            @RequestParam(required = false) String tipoDiCambio,
                                            @RequestParam(required = false) Integer annoImmatricolazione,
                                            @RequestParam(required = false) String alimentazione,
                                            @RequestParam(required = false) BigDecimal prezzo,
                                            @RequestParam(required = false) Allestimento allestimento,
                                            @RequestParam(required = false) String accessori,
                                            @RequestParam(required = false) VehicleCondition vehicleCondition,
                                            @RequestParam(required = false) TipoOrdineAcquisto tipoOrdineAcquisto,
                                            @AuthenticationPrincipal User user) {
        if (user.getRole() == Role.ACQUIRENTE) {
            // Esegui la ricerca solo se l'utente è un acquirente
            List<Vehicle> result = vehicleService.searchVehicles(marca, modello, tipoVeicolo, cilindrata, colore, potenza, tipoDiCambio, annoImmatricolazione, alimentazione, prezzo, allestimento, accessori, vehicleCondition, tipoOrdineAcquisto);
            return ResponseEntity.ok().body(result);
        } else {
            // Se l'utente non è un acquirente, restituisci un errore di autorizzazione
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Only customers can perform searches");
        }
    }

    //permette di cercare i veicoli per la condizione e il tipo di ordine
    @Operation(summary = "Search vehicles by condition and order type")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200", description = "Successfully found vehicle",
                    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = VehicleDTO.class))}),
            @ApiResponse(responseCode = "400", description = "Type and value parameters are required")
    })
    @GetMapping("/search")
    public ResponseEntity<?> searchVehicles(@RequestParam(required = false) String type, @RequestParam(required = false) String value, @AuthenticationPrincipal User user) {
        if (user.getRole() != Role.AMMINISTRATORE) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Only administrators can access this resource");
        }
        if (type == null || value == null) {
            return ResponseEntity.badRequest().body("Type and value parameters are required");
        }
        switch (type.toUpperCase()) {
            case "TIPOORDINE":
                TipoOrdineAcquisto tipoOrdine = switch (value.toUpperCase()) {
                    case "ACQUISTABILE" -> TipoOrdineAcquisto.ACQUISTABILE;
                    case "ORDINABILE" -> TipoOrdineAcquisto.ORDINABILE;
                    default -> TipoOrdineAcquisto.NON_DISPONIBILE;
                };
                return ResponseEntity.ok().body(vehicleService.searchByTipoOrdine(tipoOrdine));

            case "VEHICLECONDITION":
                VehicleCondition condition = switch (value.toUpperCase()) {
                    case "NUOVO" -> VehicleCondition.NUOVO;
                    case "USATO" -> VehicleCondition.USATO;
                    default -> throw new IllegalArgumentException("Invalid vehicle condition type: " + value);
                };
                return ResponseEntity.ok().body(vehicleService.searchByVehicleCondition(condition));

            default:
                return ResponseEntity.badRequest().body("Invalid search type: " + type);
        }
    }

    //Rotta che permette di fare ricerche per tipo veicolo
    @Operation(summary = "Search vehicles by type")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200", description = "Successfully found vehicle",
                    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = VehicleDTO.class))}),
            @ApiResponse(responseCode = "400", description = "Vehicle not valid")
    })
    @GetMapping("/searchByTipoVeicolo")
    public ResponseEntity<?> searchByTipoVeicolo(@RequestParam String vehicleType, @AuthenticationPrincipal User user) {
        if (user.getRole() != Role.VENDITORE && user.getRole() != Role.ACQUIRENTE) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Only customers and sellers can access this resource");
        }
        TipoVeicolo tipoVeicolo = TipoVeicolo.convertStringToVehicleType(vehicleType);
        if (tipoVeicolo == TipoVeicolo.UNDEFINED) {
            return ResponseEntity.badRequest().body("Vehicle not valid");
        }
        List<Vehicle> vehicles = vehicleService.searchByTipoVeicolo(tipoVeicolo);
        return ResponseEntity.ok().body(vehicles);
    }

    //veicolo più venduto in un determinato periodo
    @Operation(summary = "Get most sold vehicle in a given period")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200", description = "Successfully retrieved the most sold vehicle",
                    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = VehicleDTO.class))}),
            @ApiResponse(responseCode = "403", description = "Only admin can access this resource"),
            @ApiResponse(responseCode = "404", description = "No vehicles sold in the given period")
    })
    @GetMapping("/mostSold")
    public ResponseEntity<?> getMostSoldVehicleInPeriod(@AuthenticationPrincipal User user, @RequestParam String startDate, @RequestParam String endDate) {
        if (user.getRole() != Role.AMMINISTRATORE) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Only admin can access this resource");
        }
        OffsetDateTime start = OffsetDateTime.parse(startDate);
        OffsetDateTime end = OffsetDateTime.parse(endDate);
        List<Object[]> results = vehicleService.getMostSoldVehicleInPeriod(start, end);
        if (results.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No vehicles sold in the given period");
        }
        return ResponseEntity.ok(results.getFirst());
    }

    //veicolo più ordinato
    @Operation(summary = "Get most ordered vehicle")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200", description = "Successfully retrieved the most ordered vehicle",
                    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = VehicleDTO.class))}),
            @ApiResponse(responseCode = "403", description = "Only admin can access this resource"),
            @ApiResponse(responseCode = "404", description = "No vehicles ordered")
    })
    @GetMapping("/mostOrdered")
    public ResponseEntity<?> getMostOrderedVehicle(@AuthenticationPrincipal User user) {
        if (user.getRole() != Role.AMMINISTRATORE) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Only admin can access this resource");
        }
        Object[] results = vehicleService.getMostOrderedVehicle();
        if (results == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No vehicles ordered");
        }
        return ResponseEntity.ok(results);
    }

    //veicolo piè venduto in una certa data
    @Operation(summary = "Get highest priced vehicle sold until a given date")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200", description = "Successfully retrieved the highest priced vehicle sold",
                    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = VehicleDTO.class))}),
            @ApiResponse(responseCode = "403", description = "Only admin can access this resource"),
            @ApiResponse(responseCode = "404", description = "No vehicles sold until the given date")
    })
    @GetMapping("/highestPricedSoldUntil")
    public ResponseEntity<?> getHighestPricedVehicleSoldUntil(@RequestParam String date, @AuthenticationPrincipal User user) {
        if (user.getRole() != Role.AMMINISTRATORE) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Only admin can access this resource");
        }
        OffsetDateTime dateTime = OffsetDateTime.parse(date);
        Vehicle vehicle = vehicleService.getHighestPricedVehicleSoldUntil(dateTime);
        if (vehicle == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No vehicles sold until the given date");
        }
        return ResponseEntity.ok(vehicle);
    }

}

