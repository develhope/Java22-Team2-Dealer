package com.develhope.spring.Features.Controller;

import com.develhope.spring.Features.DTOs.OrdineAcquisto.OrdineAcquistoDTO;
import com.develhope.spring.Features.Entity.OrdineAcquisto.OrdineAcquisto;
import com.develhope.spring.Features.Service.OrdineAcquistoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/amministratore")
public class AmministratoreController {

//    @Autowired
//    private VehicleService vehicleService;
//
//    @Autowired
//    private OrdineAcquistoService ordineAcquistoService;
//
//    @Autowired
//    private NoleggioService noleggioService;
//
//    @Autowired
//    private AcquirenteService acquirenteService;
//
//    @Autowired
//    private VenditoreService venditoreService;

//    //rotta per la creazione di un ordine per un acquirente OK
//    @Operation(summary = "create an order",
//            description = "This endpoint allows an administrator to create an order for a customer.")
//    @ApiResponses(value = {
//            @ApiResponse(
//                    responseCode = "200", description = "Ok",
//                    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = OrdineAcquistoDTO.class))}),
//            @ApiResponse(responseCode = "400", description = "Invalid input or missing required fields")
//    })
//    @PostMapping("/acquirente/{acquirenteId}/createOrdine")
//    public ResponseEntity<?> createOrdineForAcquirente(@PathVariable Long acquirenteId, @PathVariable Long vehicleId, @Validated @RequestBody OrdineAcquisto ordineAcquisto) {
//        OrdineAcquisto result = ordineAcquistoService.createOrdineAcquisto(acquirenteId, vehicleId, ordineAcquisto);
//        if (result == null) {
//            return ResponseEntity.status(400).body("Impossibile creare l'ordine per l'acquirente con ID: " + acquirenteId);
//        } else {
//            return ResponseEntity.ok().body(result);
//        }
//    }
//
//    //rotta per l'eliminazione di un ordine per un acquirente OK
//    @Operation(summary = "delete a order",
//            description = "This endpoint allows an administrator to delete a order for a customer.")
//    @ApiResponses(value = {
//            @ApiResponse(
//                    responseCode = "200", description = "Ok",
//                    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = OrdineAcquistoService.class))}),
//            @ApiResponse(responseCode = "400", description = "Invalid input or missing required fields")
//    })
//    @DeleteMapping("/acquirente/{acquirenteId}/deleteOrdine/{id}")
//    public ResponseEntity<?> deleteOrdineForAcquirente(@PathVariable Long acquirenteId, @PathVariable Long ordineAcquistoId) {
//        try {
//            ordineAcquistoService.deleteOrdineAcquisto(acquirenteId, ordineAcquistoId);
//            return ResponseEntity.status(HttpStatus.NO_CONTENT).body("Ordine cancellato con successo: " + ordineAcquistoId);
//        } catch (ResourceNotFoundException e) {
//            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Ordine non trovato per cancellazione: " + ordineAcquistoId);
//        } catch (Exception e) {
//            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Errore durante la cancellazione dell'ordine: " + e.getMessage());
//        }
//    }
//
//    //rotta per l'update di un ordine per un acquirente OK
//    @Operation(summary = "update a order",
//            description = "This endpoint allows an administrator to update a order for a customer.")
//    @ApiResponses(value = {
//            @ApiResponse(
//                    responseCode = "200", description = "Ok",
//                    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = OrdineAcquistoService.class))}),
//            @ApiResponse(responseCode = "400", description = "Invalid input or missing required fields")
//    })
//    @PatchMapping("/acquirente/{acquirenteId}/updateOrdine/{id}")
//    public ResponseEntity<?> updateOrdineForAcquirente(@PathVariable Long acquirenteId, @PathVariable Long id, @RequestBody OrdineAcquisto ordineAcquisto) {
//        OrdineAcquisto optionalOrdine = ordineAcquistoService.updateOrdineAcquisto(acquirenteId, id, ordineAcquisto);
//        if (optionalOrdine.getOrdineAcquistoId().describeConstable().isPresent()) {
//            return ResponseEntity.ok("OrdineAcquisto aggiornato con successo per l'acquirente con ID: " + acquirenteId);
//        } else {
//            return ResponseEntity.status(404).body("OrdineAcquisto non trovato per l'acquirente con ID: " + acquirenteId);
//        }
//    }
//
//    //rotta per la creazione di un noleggio per un acquirente OK
//    @Operation(summary = "create a rental",
//            description = "This endpoint allows an administrator to create a rental for a customer.")
//    @ApiResponses(value = {
//            @ApiResponse(
//                    responseCode = "200", description = "Ok",
//                    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = AcquirenteService.class))}),
//            @ApiResponse(responseCode = "400", description = "Invalid input or missing required fields")
//    })
//    @PostMapping("/acquirente/{acquirenteId}/createNoleggio")
//    public ResponseEntity<?> createNoleggioForAcquirente(@PathVariable Long acquirenteId, @Validated @RequestBody CreateNoleggioRequest createNoleggioRequest) {
//        Acquirente acquirente = acquirenteService.getById(acquirenteId).orElse(null);
//        if (acquirente == null) {
//            return ResponseEntity.badRequest().body("Acquirente non trovato con ID: " + acquirenteId);
//        }
//        NoleggioDTO result = noleggioService.createNoleggioForAcquirente(acquirenteId, createNoleggioRequest);
//        if (result != null) {
//            return ResponseEntity.ok().body(result);
//        } else {
//            return ResponseEntity.status(400).body("Impossibile creare il noleggio per l'acquirente con ID: " + acquirenteId);
//        }
//    }
//
//    //rotta per l'eliminazione di un noleggio per un acquirente OK
//    @Operation(summary = "delete a rental",
//            description = "This endpoint allows an administrator to delete a rental for a customer.")
//    @ApiResponses(value = {
//            @ApiResponse(
//                    responseCode = "200", description = "Ok",
//                    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = NoleggioService.class))}),
//            @ApiResponse(responseCode = "400", description = "Invalid input or missing required fields")
//    })
//    @DeleteMapping("/acquirente/{acquirenteId}/deleteNoleggio/{id}")
//    public ResponseEntity<?> deleteNoleggioForAcquirente(@PathVariable Long acquirenteId, @PathVariable Long id) {
//        Optional<Noleggio> optionalNoleggio = noleggioService.deleteNoleggioForAcquirente(acquirenteId, id);
//        if (optionalNoleggio.isPresent()) {
//            return ResponseEntity.ok("Noleggio eliminato con successo per l'acquirente con ID: " + acquirenteId);
//        } else {
//            return ResponseEntity.status(404).body("Noleggio non trovato per l'acquirente con ID: " + acquirenteId);
//        }
//    }
//
//    //rotta per l'update di un noleggio per un acquirente OK
//    @Operation(summary = "update a rental",
//            description = "This endpoint allows an administrator to update a rental for a customer.")
//    @ApiResponses(value = {
//            @ApiResponse(
//                    responseCode = "200", description = "Ok",
//                    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = NoleggioService.class))}),
//            @ApiResponse(responseCode = "400", description = "Invalid input or missing required fields")
//    })
//    @PatchMapping("/acquirente/{acquirenteId}/updateNoleggio/{id}")
//    public ResponseEntity<?> updateNoleggioForAcquirente(@PathVariable Long acquirenteId, @PathVariable Long id, @RequestBody UpdateNoleggioRequest updateNoleggioRequest) {
//        Optional<Noleggio> optionalNoleggio = noleggioService.updateNoleggioForAcquirente(acquirenteId, id, updateNoleggioRequest);
//        if (optionalNoleggio.isPresent()) {
//            return ResponseEntity.ok("Noleggio aggiornato con successo per l'acquirente con ID: " + acquirenteId);
//        } else {
//            return ResponseEntity.status(404).body("Noleggio non trovato per l'acquirente con ID: " + acquirenteId);
//        }
//    }
//
//    // ?  Verificare un venditore quante vendite ha fatto in un determinato periodo di tempo
//
//    // ?  Verificare un venditore quanti soldi ha generato in un determinato periodo di tempo
//
//    // ?  Verificare il guadagno del salone in un determinato periodo
//
//    //rotta per la verifica dello stato di un veicolo
//    @Operation(summary = "get vehicle condition",
//            description = "This endpoint allows an administrator to check a vehicle condition.")
//    @ApiResponses(value = {
//            @ApiResponse(
//                    responseCode = "200", description = "Ok",
//                    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = VehicleService.class))}),
//            @ApiResponse(responseCode = "404", description = "not found")
//    })
//    @GetMapping("/vehicleCondition/{id}")
//    public ResponseEntity<?> getVehicleCondition(@PathVariable Long id) {
//        return vehicleService.getVehicleCondition(id);
//    }
//
//
//    // rotta cancellazione venditore OK
//    @Operation(summary = "delete a seller",
//            description = "This endpoint allows an administrator to delete a seller.")
//    @ApiResponses(value = {
//            @ApiResponse(
//                    responseCode = "200", description = "Ok",
//                    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = VenditoreService.class))}),
//            @ApiResponse(responseCode = "404", description = "Venditore non trovato.")
//    })
//    @DeleteMapping("/removeVenditore/{id}")
//    public ResponseEntity<String> deleteVenditoreById(@PathVariable Long id) {
//        Optional<Venditore> optionalVenditore = venditoreService.deleteById(id);
//        if (optionalVenditore.isPresent()) {
//            return ResponseEntity.ok("Venditore eliminato.");
//        }
//        return ResponseEntity.badRequest().body("Venditore non trovato.");
//    }
//
//    // rotta per la modifica di un venditore OK
//    @Operation(summary = "update a seller",
//            description = "This endpoint allows an administrator to update a seller.")
//    @ApiResponses(value = {
//            @ApiResponse(
//                    responseCode = "200", description = "Ok",
//                    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = VenditoreService.class))}),
//            @ApiResponse(responseCode = "404", description = "Venditore non trovato.")
//    })
//    @PatchMapping("/updateVenditore/{id}")
//    public ResponseEntity<?> updateVenditore(@PathVariable Long id, @RequestBody UpdateVenditoreRequest updateVenditoreRequest) {
//        Venditore venditore = venditoreService.updateVenditore(id, updateVenditoreRequest);
//        if (venditore != null) {
//            return ResponseEntity.ok("Venditore aggiornato con successo");
//        } else {
//            return ResponseEntity.status(404).body("Venditore non trovato");
//        }
//    }
//
//    // ?  Ottenere il vehicle più venduto in un dato periodo
//
//    // ?  Ottenere il vehicle con valore più alto venduto fino a quel dato istante
//
//    // ?  Ottenere il vehicle più ricercato/ordinato

}