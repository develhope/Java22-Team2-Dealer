package com.develhope.spring.Features.Controller;

import com.develhope.spring.Features.DTOs.OrdineAcquisto.UpdateOrdineAcquistoRequest;
import com.develhope.spring.Features.Entity.OrdineAcquisto.OrdineAcquisto;
import com.develhope.spring.Features.Entity.OrdineAcquisto.OrdineAcquistoLink;
import com.develhope.spring.Features.Entity.OrdineAcquisto.StatoOrdineAcquisto;
import com.develhope.spring.Features.Entity.User.Role;
import com.develhope.spring.Features.Entity.User.User;
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
            description = "This endpoint allows an administrator or a seller or a buyer to create a new order.")
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
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Resource not found: " + e.getMessage());
            } catch (Exception e) {
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error creating order: " + e.getMessage());
            }
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Unauthorized access");
        }
    }

    @Operation(summary = "Delete a purchase",
            description = "This endpoint allows an administrator or a seller or a buyer to delete an existing order.")
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
                return ResponseEntity.status(HttpStatus.ACCEPTED).body("Order successfully cancelled: " + ordineAcquistoId);
            } catch (ResourceNotFoundException e) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Order not found due to cancellation: " + ordineAcquistoId);
            } catch (Exception e) {
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error canceling order: " + e.getMessage());
            }
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Unauthorized access");
        }
    }

    @Operation(summary = "Update a purchase",
            description = "This endpoint allows an administrator or a seller to update an existing order.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "202", description = "Accepted", content = @Content(schema = @Schema(implementation = OrdineAcquisto.class))),
            @ApiResponse(responseCode = "401", description = "Unauthorized"),
            @ApiResponse(responseCode = "404", description = "Order not found due to update"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @PutMapping("/update/{ordineAcquistoId}")
    public ResponseEntity<?> updateOrdine(@PathVariable Long ordineAcquistoId, @RequestBody UpdateOrdineAcquistoRequest request, @AuthenticationPrincipal User user) {
        if (user.getRole() == Role.AMMINISTRATORE || user.getRole() == Role.VENDITORE) {
            try {
                OrdineAcquisto updatedOrdineAcquisto = ordineAcquistoService.updateOrdineAcquisto(ordineAcquistoId, request);
                return ResponseEntity.status(HttpStatus.ACCEPTED).body(updatedOrdineAcquisto);
            } catch (ResourceNotFoundException e) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Order not found due to update: " + ordineAcquistoId);
            } catch (Exception e) {
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error updating order: " + e.getMessage());
            }
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Unauthorized access");
        }
    }

    @Operation(summary = "Get purchases by buyer",
            description = "This endpoint allows a seller to get their purchases.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Ok", content = @Content(schema = @Schema(implementation = OrdineAcquisto.class))),
            @ApiResponse(responseCode = "401", description = "Unauthorized"),
            @ApiResponse(responseCode = "404", description = "Not found"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @GetMapping("/getAll/acquirente/{acquirenteId}")
    public ResponseEntity<?> getOrdiniByAcquirente(@PathVariable Long acquirenteId, @AuthenticationPrincipal User user) {
        if (user.getRole() == Role.VENDITORE) {
            try {
                List<OrdineAcquisto> ordiniAcquisto = ordineAcquistoLinkService.getOrdiniAcquistiByAcquirente(acquirenteId);
                if (ordiniAcquisto.isEmpty()) {
                    return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No orders found for buyer: " + acquirenteId);
                } else {
                    return ResponseEntity.ok(ordiniAcquisto);
                }
            } catch (ResourceNotFoundException e) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Buyer not found: " + acquirenteId);
            } catch (Exception e) {
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error retrieving orders: " + e.getMessage());
            }
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Unauthorized access");
        }
    }

    @Operation(summary = "Get the status of an order",
            description = "This endpoint allows a seller to get the status of a specific order.")
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
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Order not found: " + ordineAcquistoId);
            } catch (Exception e) {
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error retrieving order status: " + e.getMessage());
            }
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Unauthorized access");
        }
    }

    @Operation(summary = "Update the status of an order",
            description = "This endpoint allows a seller to update the status of an existing order.")
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
                return ResponseEntity.status(HttpStatus.ACCEPTED).body("Order status updated successfully");
            } catch (ResourceNotFoundException e) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Order not found due to status update: " + ordineAcquistoId);
            } catch (Exception e) {
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error updating order status: " + e.getMessage());
            }
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Unauthorized access");
        }
    }

    @Operation(summary = "Get orders by status",
            description = "This endpoint allows a buyer to get orders filtered by status.")
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
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error retrieving orders: " + e.getMessage());
            }
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Unauthorized access");
        }
    }
}
