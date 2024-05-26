package com.develhope.spring.Features.Controller;

import com.develhope.spring.Features.Entity.OrdineAcquisto.OrdineAcquisto;
import com.develhope.spring.Features.Entity.OrdineAcquisto.OrdineAcquistoLink;
import com.develhope.spring.Features.Entity.OrdineAcquisto.StatoOrdineAcquisto;
import com.develhope.spring.Features.Entity.User.Role;
import com.develhope.spring.Features.Entity.User.User;
import com.develhope.spring.Features.Service.AcquirenteService;
import com.develhope.spring.Features.Service.OrdineAcquistoLinkService;
import com.develhope.spring.Features.Service.OrdineAcquistoService;
import com.develhope.spring.Features.Service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/ordine")
public class OrdineController {

    @Autowired
    private OrdineAcquistoService ordineAcquistoService;

    @Autowired
    private OrdineAcquistoLinkService ordineAcquistoLinkService;

    @Autowired
    private UserService userService;

    @Operation(summary = "Create a purchase",
            description = "This endpoint allows an administrator or an acquirente to create a new order.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Created",
                    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = OrdineAcquisto.class))}),
            @ApiResponse(responseCode = "400", description = "Invalid input or missing required fields"),
            @ApiResponse(responseCode = "401", description = "Unauthorized"),
            @ApiResponse(responseCode = "404", description = "Resource not found"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @PostMapping("/create/acquirente/{acquirenteId}")
    public ResponseEntity<?> createOrdine(@PathVariable Long acquirenteId, @RequestBody OrdineAcquisto ordineAcquisto, @AuthenticationPrincipal User user) {
        if (user.getRole() == Role.AMMINISTRATORE || user.getRole() == Role.VENDITORE || user.getRole() == Role.ACQUIRENTE) {
            try {
                OrdineAcquisto createdOrdineAcquisto = ordineAcquistoService.createOrdineAcquisto(acquirenteId, ordineAcquisto);
                User acquirente = userService.getUserById(acquirenteId);
                OrdineAcquistoLink ordineAcquistoLink = new OrdineAcquistoLink(acquirente, createdOrdineAcquisto, user);
                ordineAcquistoLinkService.createOrdineAcquistoLink(ordineAcquistoLink);
                return ResponseEntity.status(HttpStatus.CREATED).body(createdOrdineAcquisto);
            } catch (ResourceNotFoundException e) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Risorsa non trovata: " + e.getMessage());
            } catch (Exception e) {
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Errore durante la creazione dell'ordine: " + e.getMessage());
            }
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Accesso non autorizzato");
        }
    }

    @Operation(summary = "Delete a purchase",
            description = "This endpoint allows an administrator to delete an existing order.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "202", description = "Accepted", content = @Content),
            @ApiResponse(responseCode = "401", description = "Unauthorized"),
            @ApiResponse(responseCode = "404", description = "Order not found due to cancellation"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @DeleteMapping("/delete/acquirente/{acquirenteId}/ordine/{ordineAcquistoId}")
    public ResponseEntity<?> deleteOrdine(@PathVariable Long acquirenteId, @PathVariable Long ordineAcquistoId, @AuthenticationPrincipal User user) {
        if (user.getRole() == Role.AMMINISTRATORE || user.getRole() == Role.VENDITORE || user.getRole() == Role.ACQUIRENTE) {
            try {
                ordineAcquistoLinkService.deleteOrdineAcquistoLink(acquirenteId, ordineAcquistoId);
                ordineAcquistoService.deleteOrdineAcquisto(ordineAcquistoId);
                return ResponseEntity.status(HttpStatus.ACCEPTED).body("Ordine cancellato con successo: " + ordineAcquistoId);
            } catch (ResourceNotFoundException e) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Ordine non trovato per cancellazione: " + ordineAcquistoId);
            } catch (Exception e) {
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Errore durante la cancellazione dell'ordine: " + e.getMessage());
            }
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Accesso non autorizzato");
        }
    }

    @Operation(summary = "Update a purchase",
            description = "This endpoint allows an administrator to update an existing order.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "202", description = "Accepted", content = @Content(schema = @Schema(implementation = OrdineAcquisto.class))),
            @ApiResponse(responseCode = "401", description = "Unauthorized"),
            @ApiResponse(responseCode = "404", description = "Order not found due to update"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @PutMapping("/update/{ordineAcquistoId}/acquirente/{acquirenteId}")
    public ResponseEntity<?> updateOrdine(@PathVariable Long ordineAcquistoId, @PathVariable Long acquirenteId, @RequestBody OrdineAcquisto ordineAcquisto, @AuthenticationPrincipal User user) {
        if (user.getRole() == Role.AMMINISTRATORE || user.getRole() == Role.VENDITORE) {
            try {
                OrdineAcquisto updatedOrdineAcquisto = ordineAcquistoService.updateOrdineAcquisto(ordineAcquistoId, ordineAcquisto);
                return ResponseEntity.status(HttpStatus.ACCEPTED).body(updatedOrdineAcquisto);
            } catch (ResourceNotFoundException e) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Ordine non trovato per aggiornamento: " + ordineAcquistoId);
            } catch (Exception e) {
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Errore durante l'aggiornamento dell'ordine: " + e.getMessage());
            }
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Accesso non autorizzato");
        }
    }

    @Operation(summary = "Get purchases by buyer",
            description = "This endpoint allows a buyer to get their purchases.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Ok", content = @Content(schema = @Schema(implementation = OrdineAcquisto.class))),
            @ApiResponse(responseCode = "401", description = "Unauthorized"),
            @ApiResponse(responseCode = "404", description = "No purchases found for the buyer"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @GetMapping("/getAll/acquirente/{acquirenteId}")
    public ResponseEntity<?> getOrdiniByAcquirente(@PathVariable Long acquirenteId, @AuthenticationPrincipal User user) {
        if (user.getRole() == Role.VENDITORE) {
            try {
                List<OrdineAcquisto> ordiniAcquisto = ordineAcquistoLinkService.getOrdiniAcquistiByAcquirente(acquirenteId);
                if (ordiniAcquisto.isEmpty()) {
                    return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Nessun ordine trovato per l'acquirente: " + acquirenteId);
                } else {
                    return ResponseEntity.ok(ordiniAcquisto);
                }
            } catch (ResourceNotFoundException e) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Acquirente non trovato: " + acquirenteId);
            } catch (Exception e) {
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Errore durante il recupero degli ordini: " + e.getMessage());
            }
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Accesso non autorizzato");
        }
    }

    @Operation(summary = "Get the status of an order",
            description = "This endpoint allows to get the status of a specific order.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Ok", content = @Content(schema = @Schema(implementation = StatoOrdineAcquisto.class))),
            @ApiResponse(responseCode = "401", description = "Unauthorized"),
            @ApiResponse(responseCode = "404", description = "Order not found"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @GetMapping("/getOrdineByStato/{ordineAcquistoId}")
    public ResponseEntity<?> getStatoOrdine(@PathVariable Long ordineAcquistoId, @AuthenticationPrincipal User user) {
        if (user.getRole() == Role.VENDITORE) {
            try {
                StatoOrdineAcquisto statoOrdineAcquisto = ordineAcquistoService.getStatoOrdineAcquisto(ordineAcquistoId);
                return ResponseEntity.ok(statoOrdineAcquisto);
            } catch (ResourceNotFoundException e) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Ordine non trovato: " + ordineAcquistoId);
            } catch (Exception e) {
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Errore durante il recupero dello stato dell'ordine: " + e.getMessage());
            }
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Accesso non autorizzato");
        }
    }

    @Operation(summary = "Update the status of an order",
            description = "This endpoint allows an administrator to update the status of an existing order.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "202", description = "Accepted"),
            @ApiResponse(responseCode = "401", description = "Unauthorized"),
            @ApiResponse(responseCode = "404", description = "Order not found"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @PutMapping("/updateStato/{ordineAcquistoId}")
    public ResponseEntity<?> updateStatoOrdine(@PathVariable Long ordineAcquistoId, @RequestParam StatoOrdineAcquisto nuovoStato, @AuthenticationPrincipal User user) {
        if (user.getRole() == Role.VENDITORE) {
            try {
                ordineAcquistoService.updateStatoOrdineAcquisto(ordineAcquistoId, nuovoStato);
                return ResponseEntity.status(HttpStatus.ACCEPTED).body("Stato dell'ordine aggiornato con successo");
            } catch (ResourceNotFoundException e) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Ordine non trovato per aggiornamento dello stato: " + ordineAcquistoId);
            } catch (Exception e) {
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Errore durante l'aggiornamento dello stato dell'ordine: " + e.getMessage());
            }
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Accesso non autorizzato");
        }
    }

    @Operation(summary = "Get orders by status",
            description = "This endpoint allows to get orders filtered by status.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Ok", content = @Content(schema = @Schema(implementation = List.class))),
            @ApiResponse(responseCode = "401", description = "Unauthorized"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @GetMapping("/filterByStato")
    public ResponseEntity<?> getOrdiniByStato(@RequestParam StatoOrdineAcquisto stato, @AuthenticationPrincipal User user) {
        if (user.getRole() == Role.ACQUIRENTE) {
            try {
                List<OrdineAcquisto> ordiniAcquisto = ordineAcquistoService.getOrdiniAcquistiByStato(stato);
                return ResponseEntity.ok(ordiniAcquisto);
            } catch (Exception e) {
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Errore durante il recupero degli ordini: " + e.getMessage());
            }
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Accesso non autorizzato");
        }
    }

}
