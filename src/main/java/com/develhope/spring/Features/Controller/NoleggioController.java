package com.develhope.spring.Features.Controller;

import com.develhope.spring.Features.DTOs.Noleggio.CreateNoleggioRequest;
import com.develhope.spring.Features.DTOs.Noleggio.NoleggioDTO;
import com.develhope.spring.Features.DTOs.Noleggio.UpdateNoleggioRequest;
import com.develhope.spring.Features.Entity.Noleggio.Noleggio;
import com.develhope.spring.Features.Entity.Noleggio.NoleggioLink;
import com.develhope.spring.Features.Entity.User.Role;
import com.develhope.spring.Features.Entity.User.User;
import com.develhope.spring.Features.Service.NoleggioLinkService;
import com.develhope.spring.Features.Service.NoleggioService;
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
@RequestMapping("/noleggio")
public class NoleggioController {

    @Autowired
    private NoleggioService noleggioService;

    @Autowired
    private NoleggioLinkService noleggioLinkService;

    @Autowired
    private UserService userService;

    @Operation(summary = "Create a rental",
            description = "This endpoint allows an administrator or a buyer to create a new rental.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Created",
                    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = NoleggioDTO.class))}),
            @ApiResponse(responseCode = "400", description = "Invalid input or missing required fields"),
            @ApiResponse(responseCode = "401", description = "Unauthorized"),
            @ApiResponse(responseCode = "404", description = "Buyer not found"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @PostMapping("/create/acquirente/{acquirenteId}")
    public ResponseEntity<?> createNoleggio(@PathVariable Long acquirenteId, @RequestBody CreateNoleggioRequest createNoleggioRequest, @AuthenticationPrincipal User user) {
        if (user.getRole() == Role.AMMINISTRATORE || user.getRole() == Role.ACQUIRENTE) {
            try {
                Noleggio createdNoleggio = noleggioService.createNoleggio(acquirenteId, createNoleggioRequest, user);
                User acquirente = userService.getUserById(acquirenteId);
                if (acquirente == null) {
                    return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Buyer not found with ID: " + acquirenteId);
                }
                NoleggioLink noleggioLink = new NoleggioLink(createdNoleggio, acquirente, user);
                noleggioLinkService.createNoleggioLink(noleggioLink);
                return ResponseEntity.status(HttpStatus.CREATED).body(createdNoleggio);
            } catch (IllegalArgumentException e) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
            } catch (Exception e) {
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error creating rental: " + e.getMessage());
            }
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Unauthorized access");
        }
    }

    @Operation(summary = "Delete a rental",
            description = "This endpoint allows an administrator or a seller or a buyer to delete an existing rental.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "202", description = "Accepted", content = @Content),
            @ApiResponse(responseCode = "401", description = "Unauthorized"),
            @ApiResponse(responseCode = "404", description = "Rental not found"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @DeleteMapping("/delete/{noleggioId}/acquirente/{acquirenteId}")
    public ResponseEntity<?> deleteNoleggio(@PathVariable Long noleggioId, @PathVariable Long acquirenteId, @AuthenticationPrincipal User user) {
        if (user.getRole() == Role.AMMINISTRATORE || user.getRole() == Role. VENDITORE || user.getRole() == Role.ACQUIRENTE) {
            try {
                noleggioLinkService.deleteNoleggioLink(acquirenteId, noleggioId);
                noleggioService.deleteNoleggio(noleggioId);
                return ResponseEntity.status(HttpStatus.ACCEPTED).body("Rental successfully cancelled: " + noleggioId);
            }  catch (ResourceNotFoundException e) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Rental not found due to cancellation: " + noleggioId);
            } catch (Exception e) {
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error canceling rental: " + e.getMessage());
            }
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Unauthorized access");
        }
    }

    @Operation(summary = "Update a rental",
            description = "This endpoint allows an administrator or a seller to update an existing rental.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "202", description = "Accepted", content = @Content(schema = @Schema(implementation = Noleggio.class))),
            @ApiResponse(responseCode = "401", description = "Unauthorized"),
            @ApiResponse(responseCode = "404", description = "Rental not found"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @PutMapping("/update/{noleggioId}")
    public ResponseEntity<?> updateNoleggio(@PathVariable Long noleggioId, @RequestBody UpdateNoleggioRequest updateNoleggioRequest, @AuthenticationPrincipal User user) {
        if (user.getRole() == Role.AMMINISTRATORE || user.getRole() == Role. VENDITORE) {
            try {
                Noleggio updatedNoleggio = noleggioService.updateNoleggio(noleggioId, updateNoleggioRequest);
                return ResponseEntity.status(HttpStatus.ACCEPTED).body(updatedNoleggio);
            } catch (IllegalArgumentException e) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Rental not found: " + noleggioId);
            } catch (Exception e) {
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error updating rental: " + e.getMessage());
            }
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Unauthorized access");
        }
    }

    @Operation(summary = "Get rentals by buyer",
            description = "This endpoint allows a buyer to get their rentals.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Ok", content = @Content(schema = @Schema(implementation = NoleggioLink.class))),
            @ApiResponse(responseCode = "401", description = "Unauthorized"),
            @ApiResponse(responseCode = "404", description = "No rentals found for the buyer"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @GetMapping("/getAll/{acquirenteId}")
    public ResponseEntity<?> getAllNoleggi(@PathVariable Long acquirenteId, @AuthenticationPrincipal User user) {
        if (user.getRole() == Role.ACQUIRENTE) {
            try {
                List<Noleggio> noleggi = noleggioLinkService.findNoleggiByAcquirente(acquirenteId);
                if (noleggi.isEmpty()) {
                    return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No rentals found for buyer: " + acquirenteId);
                } else {
                    return ResponseEntity.ok(noleggi);
                }
            } catch (Exception e) {
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error while retrieving rentals: " + e.getMessage());
            }
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Unauthorized access");
        }
    }
}
