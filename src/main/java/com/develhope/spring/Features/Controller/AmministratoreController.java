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

}