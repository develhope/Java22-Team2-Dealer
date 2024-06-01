package com.develhope.spring.Features.Controller;

import com.develhope.spring.Features.DTOs.Acquirente.AcquirenteDTO;
import com.develhope.spring.Features.Entity.User.User;
import com.develhope.spring.Features.Service.UserService;
import com.develhope.spring.Features.Service.AcquirenteService;
import com.develhope.spring.Features.Service.NoleggioService;
import com.develhope.spring.Features.Service.OrdineAcquistoService;
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

    @Autowired
    private NoleggioService noleggioService;

    @Autowired
    private UserService userService;

    @Autowired
    private OrdineAcquistoService ordineAcquistoService;

    // Route delete customer
    @Operation(summary = "Delete a Customer")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200", description = "Successfully deleted Customer",
                    content = {@Content(mediaType = "application/jason", schema = @Schema(implementation = AcquirenteDTO.class))}),
            @ApiResponse(responseCode = "400", description = "Customer not found")
    })
    @DeleteMapping("/remove/{id}")
    public ResponseEntity<?> deleteAcquirente(@PathVariable Long id, @AuthenticationPrincipal User user) {
        try {
            Optional<User> deletedAcquirente = acquirenteService.deleteById(id, user);
            if (deletedAcquirente.isPresent()) {
                return ResponseEntity.ok("User information deleted successfully");
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User with id " + id + " not found");
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(e.getMessage());
        }
    }

    //Modify customers
    @Operation(summary = "Modify Customers by ID")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200", description = "Successfully modified Customer by id",
                    content = {@Content(mediaType = "application/jason", schema = @Schema(implementation = AcquirenteDTO.class))}),
            @ApiResponse(responseCode = "400", description = "Customer not found")
    })
    @PutMapping("/set/{id}")
    public ResponseEntity<?> updateAcquirente(@PathVariable Long id, @RequestBody User userMod, @AuthenticationPrincipal User callingUser) {
        try {
            User updatedAcquirente = acquirenteService.updateAcquirente(id, userMod, callingUser);
            if (updatedAcquirente != null) {
                return ResponseEntity.ok(updatedAcquirente);
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User with id " + id + " not found");
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(e.getMessage());
        }
    }
    
}
