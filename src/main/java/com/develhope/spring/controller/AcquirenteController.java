package com.develhope.spring.controller;

import com.develhope.spring.DTOs.Acquirente.AcquirenteDTO;
import com.develhope.spring.DTOs.Acquirente.CreateAcquirenteRequest;
import com.develhope.spring.DTOs.Noleggio.CreateNoleggioRequest;
import com.develhope.spring.DTOs.Noleggio.NoleggioDTO;
import com.develhope.spring.DTOs.OrdineAcquisto.CreateOrdineAcquistoRequest;
import com.develhope.spring.DTOs.OrdineAcquisto.OrdineAcquistoDTO;
import com.develhope.spring.Models.NoleggioModel;
import com.develhope.spring.entity.Acquirente;
import com.develhope.spring.entity.Noleggio;
import com.develhope.spring.service.AcquirenteService;
import com.develhope.spring.service.NoleggioService;
import com.develhope.spring.service.OrdineAcquistoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.media.Content;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/acquirente")
public class AcquirenteController {

    @Autowired
    private AcquirenteService acquirenteService;

    @Autowired
    private NoleggioService noleggioService;

    @Autowired
    private OrdineAcquistoService ordineAcquistoService;

    //Route create Customer
    @Operation(summary = "Create a new Customer",
            description = "This endpoint allows to create a new Customer by providing the necessary information in the request body.")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200", description = "Successfully created Customer",
                    content = {@Content(mediaType = "application/jason", schema = @Schema(implementation = AcquirenteDTO.class))}),
            @ApiResponse(responseCode = "400", description = "Invalid input or missing required fields")
    })
    @PostMapping("/add")
    public ResponseEntity<?> createAcquirente(@Validated @RequestBody CreateAcquirenteRequest createAcquirenteRequest) {
        AcquirenteDTO result = acquirenteService.createAcquirente(createAcquirenteRequest);
        if (result == null) {
            return ResponseEntity.status(400).body("Invalid input or missing required fields");
        } else {
            return ResponseEntity.ok().body(result);
        }
    }

    // Route delete customer
    @Operation(summary = "Delete a Customer")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200", description = "Successfully deleted Customer",
                    content = {@Content(mediaType = "application/jason", schema = @Schema(implementation = AcquirenteDTO.class))}),
            @ApiResponse(responseCode = "400", description = "Customer not found")
    })
    @DeleteMapping("/remove/{id}")
    public ResponseEntity<String> deleteAcquirenteById(@PathVariable Long id) {
        Optional<Acquirente> optionalAcquirente = acquirenteService.deleteById(id);
        if (optionalAcquirente.isPresent()) {
            return ResponseEntity.ok("Customer deleted.");
        }
        return ResponseEntity.badRequest().body("Customer not found");
    }

    //Route search all customers
    @Operation(summary = "Search all Customers")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200", description = "Successfully got all Customers",
                    content = {@Content(mediaType = "application/jason", schema = @Schema(implementation = AcquirenteDTO.class))}),
            @ApiResponse(responseCode = "400", description = "No Customers found")
    })
    @GetMapping("/getAll")
    public List<Acquirente> findAllAcquirenti() {
        return acquirenteService.getAllAcquirenti();
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
    public ResponseEntity<String> setAcquirenteById(@PathVariable Long id, @RequestBody Acquirente acquirenteMod) {
        Acquirente acquirente = acquirenteService.updateAcquirente(id, acquirenteMod);
        if (acquirente != null) {
            return ResponseEntity.ok("Customer modified");
        }
        return ResponseEntity.badRequest().body("Customer not found.");
    }

    // Route search customers by ID
    @Operation(summary = "Search Customers by ID")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200", description = "Successfully got Customer by id",
                    content = {@Content(mediaType = "application/jason", schema = @Schema(implementation = AcquirenteDTO.class))}),
            @ApiResponse(responseCode = "400", description = "No customers found")
    })
    @GetMapping("/getById/{id}")
    public Optional<Acquirente> getAcquirenteById(@PathVariable Long id) {
        return acquirenteService.getById(id);
    }

    // Route search rentals by customer ID
    @Operation(summary = "Search rentals by Customers ID")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200", description = "Successfully got rentals by Customers id",
                    content = {@Content(mediaType = "application/jason", schema = @Schema(implementation = AcquirenteDTO.class))}),
            @ApiResponse(responseCode = "400", description = "No rentals found")
    })
    @GetMapping("/{acquirenteId}/noleggi")
    public ResponseEntity<List<NoleggioDTO>> getNoleggi(@PathVariable Long acquirenteId) {
        List<Noleggio> noleggi = noleggioService.findByAcquirente(acquirenteId);
        if (noleggi.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        List<NoleggioDTO> noleggiDto = noleggi.stream()
                .map(NoleggioModel::entityToModel)
                .map(NoleggioModel::modelToDto)
                .collect(Collectors.toList());
        return ResponseEntity.ok(noleggiDto);
    }

    // Route for creating a rental by customer ID
    @Operation(summary = "Create a rental for a customer")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200", description = "Successfully created the rental",
                    content = {@Content(mediaType = "application/jason", schema = @Schema(implementation = AcquirenteDTO.class))}),
            @ApiResponse(responseCode = "400", description = "Invalid input or missing required fields")
    })
    @PostMapping("/{acquirenteId}/noleggi/add")
    public ResponseEntity<?> addNoleggio(@Validated @PathVariable Long acquirenteId, @Validated @RequestBody CreateNoleggioRequest createNoleggioRequest) {
        Acquirente acquirente = acquirenteService.getById(acquirenteId).orElse(null);
        if (acquirente == null) {
            return ResponseEntity.badRequest().body("Customer not found by ID: " + acquirenteId);
        }
        NoleggioDTO nuovoNoleggio = noleggioService.createNoleggioForAcquirente(acquirenteId, createNoleggioRequest);
        if (nuovoNoleggio == null) {
            return ResponseEntity.badRequest().body("Invalid input or missing required fields");
        }
        return ResponseEntity.ok(nuovoNoleggio);
    }

    // Route delete rental
    @Operation(summary = "Delete rental")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200", description = "Successfully deleted the rental",
                    content = {@Content(mediaType = "application/jason", schema = @Schema(implementation = AcquirenteDTO.class))}),
            @ApiResponse(responseCode = "400", description = "Rental not found")
    })
    @DeleteMapping("/{acquirenteId}/noleggi/remove/{noleggioId}")
    public ResponseEntity<String> deleteNoleggioByAcquirenteId(
            @PathVariable Long acquirenteId,
            @PathVariable Long noleggioId) {
        Noleggio noleggio = noleggioService.findById(noleggioId).orElse(null);
        if (noleggio == null || !noleggio.getAcquirente().getId().equals(acquirenteId)) {
            return ResponseEntity.notFound().build();
        }
        noleggioService.deleteNoleggio(noleggioId);
        return ResponseEntity.ok("Rental deleted.");
    }

    // Route create order's customer
    @Operation(summary = "Create order's customer")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200", description = "Successfully created order's customer",
                    content = {@Content(mediaType = "application/jason", schema = @Schema(implementation = AcquirenteDTO.class))}),
            @ApiResponse(responseCode = "400", description = "Unable to create the order.")
    })
    @PostMapping("/{acquirenteId}/ordine/add")
    public ResponseEntity<?> addOrdine(@Validated @PathVariable Long acquirenteId, @Validated @RequestBody CreateOrdineAcquistoRequest createOrdineAcquistoRequest) {
        Acquirente acquirente = acquirenteService.getById(acquirenteId).orElse(null);
        if (acquirente == null) {
            return ResponseEntity.badRequest().body("Acquirente non trovato con ID: " + acquirenteId);
        }
        OrdineAcquistoDTO nuovoOrdine = ordineAcquistoService.createOrdineAcquisto(acquirenteId, createOrdineAcquistoRequest);
        if (nuovoOrdine == null) {
            return ResponseEntity.badRequest().body("Unable to create the order.");
        }
        return ResponseEntity.ok(nuovoOrdine);
    }

}
