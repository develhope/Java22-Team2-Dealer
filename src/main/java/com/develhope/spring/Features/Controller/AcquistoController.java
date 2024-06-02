package com.develhope.spring.Features.Controller;

import com.develhope.spring.Features.DTOs.OrdineAcquisto.UpdateOrdineAcquistoRequest;
import com.develhope.spring.Features.Entity.OrdineAcquisto.OrdineAcquisto;
import com.develhope.spring.Features.Entity.OrdineAcquisto.OrdineAcquistoLink;
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
@RequestMapping("/acquisto")
public class AcquistoController {

    @Autowired
    private OrdineAcquistoService ordineAcquistoService;

    @Autowired
    private OrdineAcquistoLinkService ordineAcquistoLinkService;

    @Autowired
    private UserService userService;

    @Operation(summary = "Create a purchase",
            description = "This endpoint allows an administrator or a buyer to create a new purchase.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Created",
                    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = OrdineAcquisto.class))}),
            @ApiResponse(responseCode = "400", description = "Invalid input or missing required fields"),
            @ApiResponse(responseCode = "401", description = "Unauthorized"),
            @ApiResponse(responseCode = "404", description = "Vehicle not found"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @PostMapping("/create/acquirente/{acquirenteId}")
    public ResponseEntity<?> createAcquisto(@PathVariable Long acquirenteId, @RequestBody OrdineAcquisto ordineAcquisto, @AuthenticationPrincipal User user) {
        if (user.getRole() == Role.AMMINISTRATORE || user.getRole() == Role.ACQUIRENTE) {
            Long vehicleId = ordineAcquisto.getVehicle().getVehicleId();
            try {
                OrdineAcquisto createdOrdineAcquisto = ordineAcquistoService.createOrdineAcquisto(vehicleId, ordineAcquisto);
                User acquirente = userService.getUserById(acquirenteId);
                OrdineAcquistoLink ordineAcquistoLink = new OrdineAcquistoLink(acquirente, createdOrdineAcquisto, user);
                ordineAcquistoLinkService.createOrdineAcquistoLink(ordineAcquistoLink);
                return ResponseEntity.status(HttpStatus.CREATED).body(createdOrdineAcquisto);
            } catch (ResourceNotFoundException e) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Vehicle not found: " + vehicleId);
            } catch (IllegalArgumentException e) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Vehicle not purchasable: " + vehicleId);
            } catch (Exception e) {
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error creating purchase from vehicle: " + e.getMessage());
            }
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Unauthorized access");
        }
    }

    @Operation(summary = "Delete a purchase",
            description = "This endpoint allows an administrator to delete an existing purchase.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "202", description = "Accepted", content = @Content),
            @ApiResponse(responseCode = "401", description = "Unauthorized"),
            @ApiResponse(responseCode = "404", description = "Purchase not found due to cancellation"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @DeleteMapping("/delete/acquirente/{acquirenteId}/acquisto/{ordineAcquistoId}")
    public ResponseEntity<?> deleteAcquisto(@PathVariable Long acquirenteId, @PathVariable Long ordineAcquistoId, @AuthenticationPrincipal User user) {
        if (user.getRole() == Role.AMMINISTRATORE) {
            try {
                ordineAcquistoLinkService.deleteOrdineAcquistoLink(acquirenteId, ordineAcquistoId);
                ordineAcquistoService.deleteOrdineAcquisto(ordineAcquistoId);
                return ResponseEntity.status(HttpStatus.ACCEPTED).body("Purchase successfully cancelled: " + ordineAcquistoId);
            } catch (ResourceNotFoundException e) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Purchase not found due to cancellation: " + ordineAcquistoId);
            } catch (Exception e) {
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error canceling purchase: " + e.getMessage());
            }
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Unauthorized access");
        }
    }

    @Operation(summary = "Update a purchase",
            description = "This endpoint allows an administrator to update an existing purchase.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "202", description = "Accepted", content = @Content(schema = @Schema(implementation = OrdineAcquisto.class))),
            @ApiResponse(responseCode = "401", description = "Unauthorized"),
            @ApiResponse(responseCode = "404", description = "Purchase not found due to update"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @PutMapping("/update/{ordineAcquistoId}/acquirente/{acquirenteId}")
    public ResponseEntity<?> updateAcquisto(@PathVariable Long ordineAcquistoId, @RequestBody UpdateOrdineAcquistoRequest request, @AuthenticationPrincipal User user) {
        if (user.getRole() == Role.AMMINISTRATORE) {
            try {
                OrdineAcquisto updatedOrdineAcquisto = ordineAcquistoService.updateOrdineAcquisto(ordineAcquistoId, request);
                return ResponseEntity.status(HttpStatus.ACCEPTED).body(updatedOrdineAcquisto);
            } catch (ResourceNotFoundException e) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Purchase not found due to update: " + ordineAcquistoId);
            } catch (Exception e) {
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error updating purchase: " + e.getMessage());
            }
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Unauthorized access");
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
    public ResponseEntity<?> getAcquistiByAcquirente(@PathVariable Long acquirenteId, @AuthenticationPrincipal User user) {
        if (user.getRole() == Role.ACQUIRENTE) {
            try {
                List<OrdineAcquisto> ordiniAcquisto = ordineAcquistoLinkService.getOrdiniAcquistiByAcquirente(acquirenteId);
                if (ordiniAcquisto.isEmpty()) {
                    return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No purchases found for buyer: " + acquirenteId);
                } else {
                    return ResponseEntity.ok(ordiniAcquisto);
                }
            } catch (ResourceNotFoundException e) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Buyer not found: " + acquirenteId);
            } catch (Exception e) {
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error while retrieving purchases: " + e.getMessage());
            }
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Unauthorized access");
        }
    }
}
