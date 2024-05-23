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

//    //Route search all customers
//    @Operation(summary = "Search all Customers")
//    @ApiResponses(value = {
//            @ApiResponse(
//                    responseCode = "200", description = "Successfully got all Customers",
//                    content = {@Content(mediaType = "application/jason", schema = @Schema(implementation = AcquirenteDTO.class))}),
//            @ApiResponse(responseCode = "400", description = "No Customers found")
//    })
//    @GetMapping("/getAll")
//    public List<Acquirente> findAllAcquirenti() {
//        return acquirenteService.getAllAcquirenti();
//    }
//
//    // Route search customers by ID
//    @Operation(summary = "Search Customers by ID")
//    @ApiResponses(value = {
//            @ApiResponse(
//                    responseCode = "200", description = "Successfully got Customer by id",
//                    content = {@Content(mediaType = "application/jason", schema = @Schema(implementation = AcquirenteDTO.class))}),
//            @ApiResponse(responseCode = "400", description = "No customers found")
//    })
//    @GetMapping("/getById/{id}")
//    public Optional<Acquirente> getAcquirenteById(@PathVariable Long id) {
//        return acquirenteService.getById(id);
//    }
//
//    // Route search rentals by customer ID
//    @Operation(summary = "Search rentals by Customers ID")
//    @ApiResponses(value = {
//            @ApiResponse(
//                    responseCode = "200", description = "Successfully got rentals by Customers id",
//                    content = {@Content(mediaType = "application/jason", schema = @Schema(implementation = AcquirenteDTO.class))}),
//            @ApiResponse(responseCode = "400", description = "No rentals found")
//    })
//    @GetMapping("/{acquirenteId}/noleggi")
//    public ResponseEntity<List<NoleggioDTO>> getNoleggi(@PathVariable Long acquirenteId) {
//        List<Noleggio> noleggi = noleggioService.findByAcquirente(acquirenteId);
//        if (noleggi.isEmpty()) {
//            return ResponseEntity.notFound().build();
//        }
//        List<NoleggioDTO> noleggiDto = noleggi.stream()
//                .map(NoleggioModel::entityToModel)
//                .map(NoleggioModel::modelToDto)
//                .collect(Collectors.toList());
//        return ResponseEntity.ok(noleggiDto);
//    }
//
//    // Route for creating a rental by customer ID
//    @Operation(summary = "Create a rental for a customer")
//    @ApiResponses(value = {
//            @ApiResponse(
//                    responseCode = "200", description = "Successfully created the rental",
//                    content = {@Content(mediaType = "application/jason", schema = @Schema(implementation = AcquirenteDTO.class))}),
//            @ApiResponse(responseCode = "400", description = "Invalid input or missing required fields")
//    })
//    @PostMapping("/{acquirenteId}/noleggi/add")
//    public ResponseEntity<?> addNoleggio(@Validated @PathVariable Long acquirenteId, @Validated @RequestBody CreateNoleggioRequest createNoleggioRequest) {
//        Acquirente acquirente = acquirenteService.getById(acquirenteId).orElse(null);
//        if (acquirente == null) {
//            return ResponseEntity.badRequest().body("Customer not found by ID: " + acquirenteId);
//        }
//        NoleggioDTO nuovoNoleggio = noleggioService.createNoleggioForAcquirente(acquirenteId, createNoleggioRequest);
//        if (nuovoNoleggio == null) {
//            return ResponseEntity.badRequest().body("Invalid input or missing required fields");
//        }
//        return ResponseEntity.ok(nuovoNoleggio);
//    }
//
//    // Route delete rental
//    @Operation(summary = "Delete rental")
//    @ApiResponses(value = {
//            @ApiResponse(
//                    responseCode = "200", description = "Successfully deleted the rental",
//                    content = {@Content(mediaType = "application/jason", schema = @Schema(implementation = AcquirenteDTO.class))}),
//            @ApiResponse(responseCode = "400", description = "Rental not found")
//    })
//    @DeleteMapping("/{acquirenteId}/noleggi/remove/{noleggioId}")
//    public ResponseEntity<String> deleteNoleggioByAcquirenteId(
//            @PathVariable Long acquirenteId,
//            @PathVariable Long noleggioId) {
//        Noleggio noleggio = noleggioService.findById(noleggioId).orElse(null);
//        if (noleggio == null || !noleggio.getAcquirente().getAcquirenteId().equals(acquirenteId)) {
//            return ResponseEntity.notFound().build();
//        }
//        noleggioService.deleteNoleggio(noleggioId);
//        return ResponseEntity.ok("Rental deleted.");
//    }
//
//    // Route create order's customer
//    @Operation(summary = "Create order's customer")
//    @ApiResponses(value = {
//            @ApiResponse(
//                    responseCode = "200", description = "Successfully created order's customer",
//                    content = {@Content(mediaType = "application/jason", schema = @Schema(implementation = AcquirenteDTO.class))}),
//            @ApiResponse(responseCode = "400", description = "Unable to create the order.")
//    })
//    @PostMapping("/{acquirenteId}/ordine/add")
//    public ResponseEntity<?> addOrdine(@Validated @PathVariable Long acquirenteId, @PathVariable Long vehicleId, @Validated @RequestBody OrdineAcquisto ordineAcquisto) {
//        Acquirente acquirente = acquirenteService.getById(acquirenteId).orElse(null);
//        if (acquirente == null) {
//            return ResponseEntity.badRequest().body("Acquirente non trovato con ID: " + acquirenteId);
//        }
//        OrdineAcquisto nuovoOrdine = ordineAcquistoService.createOrdineAcquisto(acquirenteId, vehicleId, ordineAcquisto);
//        if (nuovoOrdine == null) {
//            return ResponseEntity.badRequest().body("Unable to create the order.");
//        }
//        return ResponseEntity.ok(nuovoOrdine);
//    }
//
//    //Cancellare un ordine
//    @Operation(summary = "delete order")
//    @ApiResponses(value = {
//            @ApiResponse(
//                    responseCode = "200", description = "Successfully deleted order",
//                    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = OrdineAcquistoDTO.class))}),
//            @ApiResponse(responseCode = "400", description = "No orders found")
//    })
//    @DeleteMapping("/{acquirenteId}/ordini/{ordineId}")
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
//    // Route to get all orders and purchases by customer ID
//    @Operation(summary = "Get all order")
//    @ApiResponses(value = {
//            @ApiResponse(
//                    responseCode = "200", description = "Successfully got all orders",
//                    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = OrdineAcquistoDTO.class))}),
//            @ApiResponse(responseCode = "400", description = "No orders found")
//    })
//    @GetMapping("/ordini/{acquirenteId}")
//    public ResponseEntity<List<OrdineAcquisto>> getOrdiniByAcquirenteId(@PathVariable Long acquirenteId) {
//        List<OrdineAcquisto> ordini = ordineAcquistoService.getOrdiniAcquistiByAcquirente(acquirenteId);
//        return ResponseEntity.ok(ordini);
//    }
//
//    @Operation(summary = "Get all purchases")
//    @ApiResponses(value = {
//            @ApiResponse(
//                    responseCode = "200", description = "Successfully got all purchases",
//                    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = OrdineAcquistoDTO.class))}),
//            @ApiResponse(responseCode = "400", description = "No purchases found")
//    })
//    @GetMapping("/acquisti/{acquirenteId}")
//    public ResponseEntity<List<OrdineAcquisto>> getAcquistiByAcquirenteId(@PathVariable Long acquirenteId) {
//        List<OrdineAcquisto> acquisti = ordineAcquistoService.getOrdiniAcquistiByAcquirente(acquirenteId);
//        return ResponseEntity.ok(acquisti);
//    }

}
