package com.develhope.spring.Features.Controller;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/acquisto")
public class AcquistoController {

//    @Autowired
//    private OrdineAcquistoService ordineAcquistoService;
//
//    @Autowired
//    private AcquirenteService acquirenteService;
//
//    // Creare un acquisto per un utente
//    @Operation(summary = "Create a purchase",
//            description = "This endpoint allows an administrator or an acquirente to create a new order.")
//    @ApiResponses(value = {
//            @ApiResponse(
//                    responseCode = "201", description = "Created",
//                    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = OrdineAcquisto.class))}),
//            @ApiResponse(responseCode = "400", description = "Invalid input or missing required fields"),
//            @ApiResponse(responseCode = "401", description = "Unauthorized"),
//            @ApiResponse(responseCode = "404", description = "Vehicle not found"),
//            @ApiResponse(responseCode = "500", description = "Internal server error")
//    })
//    @PostMapping("/create/acquirente/{acquirenteId}")
//    public ResponseEntity<?> createAcquisto(@PathVariable Long acquirenteId, @RequestBody OrdineAcquisto ordineAcquisto, @AuthenticationPrincipal User user) {
//        if (user.getRole() == Role.AMMINISTRATORE || user.getRole() == Role.ACQUIRENTE) {
//            Long vehicleId = ordineAcquisto.getVehicle().getVehicleId();
//            try {
//                OrdineAcquisto createdOrdineAcquisto = ordineAcquistoService.createOrdineAcquisto(acquirenteId, vehicleId, ordineAcquisto);
//                return ResponseEntity.status(HttpStatus.CREATED).body(createdOrdineAcquisto);
//            } catch (ResourceNotFoundException e) {
//                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Veicolo non trovato: " + vehicleId);
//            } catch (IllegalArgumentException e) {
//                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Veicolo non acquistabile: " + vehicleId);
//            } catch (Exception e) {
//                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Errore durante la creazione dell'acquisto dal veicolo: " + e.getMessage());
//            }
//        } else {
//            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Accesso non autorizzato");
//        }
//    }
//
//    // Cancellare un acquisto per un utente
//    @Operation(summary = "Delete a purchase",
//            description = "This endpoint allows an administrator to delete an existing order.")
//    @ApiResponses(value = {
//            @ApiResponse(responseCode = "202", description = "Accepted", content = @Content),
//            @ApiResponse(responseCode = "401", description = "Unauthorized"),
//            @ApiResponse(responseCode = "404", description = "Purchase not found due to cancellation"),
//            @ApiResponse(responseCode = "500", description = "Internal server error")
//    })
//    @DeleteMapping("/delete/acquirente/{acquirenteId}/acquisto/{ordineAcquistoId}")
//    public ResponseEntity<?> deleteAcquisto(@PathVariable Long acquirenteId, @PathVariable Long ordineAcquistoId, @AuthenticationPrincipal User user) {
//        if (user.getRole() == Role.AMMINISTRATORE) {
//            try {
//                ordineAcquistoService.deleteOrdineAcquisto(acquirenteId, ordineAcquistoId);
//                return ResponseEntity.status(HttpStatus.ACCEPTED).body("Acquisto cancellato con successo: " + ordineAcquistoId);
//            } catch (ResourceNotFoundException e) {
//                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Acquisto non trovato per cancellazione: " + ordineAcquistoId);
//            } catch (Exception e) {
//                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Errore durante la cancellazione dell'acquisto: " + e.getMessage());
//            }
//        } else {
//            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Accesso non autorizzato");
//        }
//    }
//
//    // Modificare un acquisto per un utente
//    @Operation(summary = "Update a purchase",
//            description = "This endpoint allows an administrator to update an existing order.")
//    @ApiResponses(value = {
//            @ApiResponse(responseCode = "202", description = "Accepted", content = @Content(schema = @Schema(implementation = OrdineAcquisto.class))),
//            @ApiResponse(responseCode = "401", description = "Unauthorized"),
//            @ApiResponse(responseCode = "404", description = "Purchase not found due to update"),
//            @ApiResponse(responseCode = "500", description = "Internal server error")
//    })
//    @PutMapping("/update/{ordineAcquistoId}/acquirente/{acquirenteId}")
//    public ResponseEntity<?> updateAcquisto(@PathVariable Long ordineAcquistoId, @PathVariable Long acquirenteId, @RequestBody OrdineAcquisto ordineAcquisto, @AuthenticationPrincipal User user) {
//        if (user.getRole() == Role.AMMINISTRATORE) {
//            try {
//                OrdineAcquisto updatedOrdineAcquisto = ordineAcquistoService.updateOrdineAcquisto(ordineAcquistoId, acquirenteId, ordineAcquisto);
//                return ResponseEntity.status(HttpStatus.ACCEPTED).body(updatedOrdineAcquisto);
//            } catch (ResourceNotFoundException e) {
//                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Acquisto non trovato per aggiornamento: " + ordineAcquistoId);
//            } catch (Exception e) {
//                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Errore durante l'aggiornamento dell'acquisto: " + e.getMessage());
//            }
//        } else {
//            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Accesso non autorizzato");
//        }
//    }
//
//    // Vedere i propri acquisti
//    @Operation(summary = "Get purchases by buyer",
//            description = "This endpoint allows a buyer to get their purchases.")
//    @ApiResponses(value = {
//            @ApiResponse(responseCode = "200", description = "Ok", content = @Content(schema = @Schema(implementation = OrdineAcquisto.class))),
//            @ApiResponse(responseCode = "401", description = "Unauthorized"),
//            @ApiResponse(responseCode = "404", description = "No purchases found for the buyer"),
//            @ApiResponse(responseCode = "500", description = "Internal server error")
//    })
//    @GetMapping("/getAll/acquirente/{acquirenteId}")
//    public ResponseEntity<?> getAcquistiByAcquirente(@PathVariable Long acquirenteId, @AuthenticationPrincipal User user) {
//        if (user.getRole() == Role.ACQUIRENTE) {
//            try {
//                List<OrdineAcquisto> ordiniAcquisto = ordineAcquistoService.getOrdiniAcquistiByAcquirente(acquirenteId);
//                if (ordiniAcquisto.isEmpty()) {
//                    return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Nessun acquisto trovato per l'acquirente: " + acquirenteId);
//                } else {
//                    return ResponseEntity.ok(ordiniAcquisto);
//                }
//            } catch (ResourceNotFoundException e) {
//                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Acquirente non trovato: " + acquirenteId);
//            } catch (Exception e) {
//                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Errore durante il recupero degli acquisti: " + e.getMessage());
//            }
//        } else {
//            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Accesso non autorizzato");
//        }
//    }

}
