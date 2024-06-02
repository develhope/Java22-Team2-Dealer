package com.develhope.spring.Features.Controller;

import com.develhope.spring.Features.DTOs.Acquirente.AcquirenteDTO;
import com.develhope.spring.Features.Entity.User.User;
import com.develhope.spring.Features.Service.AcquirenteService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/acquirente")
public class AcquirenteController {

    @Autowired
    private AcquirenteService acquirenteService;

    @Operation(summary = "Delete a Buyer")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200", description = "Successfully deleted Buyer",
                    content = {@Content(mediaType = "application/jason", schema = @Schema(implementation = AcquirenteDTO.class))}),
            @ApiResponse(responseCode = "400", description = "Buyer not found"),
            @ApiResponse(responseCode = "401", description = "Unauthorized")
    })
    @DeleteMapping("/remove/{id}")
    public ResponseEntity<?> deleteAcquirente(@PathVariable Long id, @AuthenticationPrincipal User user) {
        try {
            Optional<User> deletedAcquirente = acquirenteService.deleteById(id, user);
            if (deletedAcquirente.isPresent()) {
                return ResponseEntity.ok("Buyer information deleted successfully");
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Buyer with id " + id + " not found");
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Unauthorized access");
        }
    }

    @Operation(summary = "Modify a Buyer by ID")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200", description = "Successfully modified Buyer by id",
                    content = {@Content(mediaType = "application/jason", schema = @Schema(implementation = AcquirenteDTO.class))}),
            @ApiResponse(responseCode = "400", description = "Buyer not found"),
            @ApiResponse(responseCode = "401", description = "Unauthorized")
    })
    @PutMapping("/set/{id}")
    public ResponseEntity<?> updateAcquirente(@PathVariable Long id, @RequestBody User userMod, @AuthenticationPrincipal User callingUser) {
        try {
            User updatedAcquirente = acquirenteService.updateAcquirente(id, userMod, callingUser);
            if (updatedAcquirente != null) {
                return ResponseEntity.ok(updatedAcquirente);
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Buyer with id " + id + " not found");
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Unauthorized access");
        }
    }
}
