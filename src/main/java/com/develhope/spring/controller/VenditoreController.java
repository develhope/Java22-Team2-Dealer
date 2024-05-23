package com.develhope.spring.controller;

import com.develhope.spring.DTOs.Noleggio.CreateNoleggioRequest;
import com.develhope.spring.DTOs.Noleggio.NoleggioDTO;
import com.develhope.spring.DTOs.Venditore.CreateVenditoreRequest;
import com.develhope.spring.DTOs.Venditore.VenditoreDTO;
import com.develhope.spring.entity.Noleggio;
import com.develhope.spring.entity.Venditore;
import com.develhope.spring.service.NoleggioService;
import com.develhope.spring.service.OrdineAcquistoService;
import com.develhope.spring.service.VenditoreService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.time.OffsetDateTime;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/venditore")
public class VenditoreController {

    @Autowired
    private VenditoreService venditoreService;

    @Autowired
    private NoleggioService noleggioService;

    @Autowired
    private OrdineAcquistoService ordineAcquistoService;

    // Route create seller
    @Operation(summary = "Create a new Seller",
            description = "This endpoint allows to create a new Seller by providing the necessary information in the request body.")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200", description = "Successfully created Seller",
                    content = {@Content(mediaType = "application/jason", schema = @Schema(implementation = VenditoreDTO.class))}),
            @ApiResponse(responseCode = "400", description = "Invalid input or missing required fields")
    })
    @PostMapping("/add")
    public ResponseEntity<?> createVenditore(@Validated @RequestBody CreateVenditoreRequest createVenditoreRequest) {
        VenditoreDTO result = venditoreService.createVenditore(createVenditoreRequest);
        if (result == null) {
            return ResponseEntity.status(400).body("Invalid input or missing required fields");
        } else {
            return ResponseEntity.ok().body(result);
        }
    }

    // Route delete seller
    @Operation(summary = "Delete Seller")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200", description = "Successfully deleted Seller",
                    content = {@Content(mediaType = "application/jason", schema = @Schema(implementation = VenditoreDTO.class))}),
            @ApiResponse(responseCode = "400", description = "Seller not found.")
    })
    @DeleteMapping("/remove/{id}")
    public ResponseEntity<String> deleteVenditoreById(@PathVariable Long id) {
        Optional<Venditore> optionalVenditore = venditoreService.deleteById(id);
        if (optionalVenditore.isPresent()) {
            return ResponseEntity.ok("Seller deleted.");
        }
        return ResponseEntity.badRequest().body("Seller not found.");
    }

    //Route search all sellers
    @Operation(summary = "Search all Sellers")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200", description = "Successfully got all Sellers",
                    content = {@Content(mediaType = "application/jason", schema = @Schema(implementation = VenditoreDTO.class))}),
            @ApiResponse(responseCode = "400", description = "No Sellers found")
    })
    @GetMapping("/getAll")
    public List<Venditore> findAllVenditore() {
        return venditoreService.getAllVenditori();
    }


    @Operation(summary = "Modify Seller by ID")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200", description = "Successfully modified seller by id",
                    content = {@Content(mediaType = "application/jason", schema = @Schema(implementation = VenditoreDTO.class))}),
            @ApiResponse(responseCode = "400", description = "Seller not found")
    })
    @PutMapping("/set/{id}")
    public ResponseEntity<String> setVenditoreById(@PathVariable Long id, @RequestBody Venditore venditoreMod) {
        Venditore venditore = venditoreService.updateVenditore(id, venditoreMod);
        if (venditore != null) {
            return ResponseEntity.ok("Seller modified.");
        }
        return ResponseEntity.badRequest().body("Seller not found.");
    }

    // Route search sellers by ID
    @Operation(summary = "Search sellers by ID")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200", description = "Successfully got seller by id",
                    content = {@Content(mediaType = "application/jason", schema = @Schema(implementation = VenditoreDTO.class))}),
            @ApiResponse(responseCode = "400", description = "No seller found")
    })
    @GetMapping("/getById/{id}")
    public Optional<Venditore> getVenditoreById(@PathVariable Long id) {
        return venditoreService.getVenditoreById(id);
    }

    //Create rental
    @Operation(summary = "Create a rental",
            description = "This endpoint allows to create a new Rentasl by providing the necessary information in the request body."
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200", description = "Successfully created the rental",
                    content = {@Content(mediaType = "application/jason", schema = @Schema(implementation = VenditoreDTO.class))}),
            @ApiResponse(responseCode = "400", description = "Invalid input or missing required fields")
    })
    @PostMapping("/addNoleggio")
    public ResponseEntity<?> createNoleggio(@RequestBody CreateNoleggioRequest createNoleggioRequest){
        NoleggioDTO result = noleggioService.createNoleggio(createNoleggioRequest);
        if (result == null) {
            return ResponseEntity.status(400).body("Invalid input or missing required fields");
        } else {
            return ResponseEntity.ok().body(result);
        }
    }

    // Route delete rental
    @Operation(summary = "Delete rental")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200", description = "Successfully deleted the rental",
                    content = {@Content(mediaType = "application/jason", schema = @Schema(implementation = VenditoreDTO.class))}),
            @ApiResponse(responseCode = "400", description = "Rental not found")
    })
    @DeleteMapping("/deleteNoleggio/{id}")
    public ResponseEntity<String> deleteNoleggioById(@PathVariable Long id){
        Optional<Noleggio> optionalNoleggio = noleggioService.deleteNoleggio(id);
        if (optionalNoleggio.isPresent()) {
            return ResponseEntity.ok("Noleggio eliminato.");
        }
        return ResponseEntity.badRequest().body("Noleggio non trovato.");
    }

    //Modify rental
    @Operation(summary = "Modify rental by id")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200", description = "Successfully modified rental by id",
                    content = {@Content(mediaType = "application/jason", schema = @Schema(implementation = VenditoreDTO.class))}),
            @ApiResponse(responseCode = "400", description = "Rental not found")
    })
    @PutMapping("/setNoleggio/{id}")
    public ResponseEntity<String> setNoleggioById (@PathVariable Long id, @RequestBody CreateNoleggioRequest noleggioAggiornato){
        Noleggio noleggio = noleggioService.updateNoleggioById(id, noleggioAggiornato);
        if (noleggio != null) {
            return ResponseEntity.ok("Rental modified");
        }
        return ResponseEntity.badRequest().body("Rental not found.");
    }

    // TODO da testare
//
//    @Operation(summary = "Get order total number for data")
//    @ApiResponses(value = {
//            @ApiResponse(
//                    responseCode = "200", description = "Successfully got total orders for data by id seller",
//                    content = {@Content(mediaType = "application/jason", schema = @Schema(implementation = VenditoreDTO.class))}),
//            @ApiResponse(responseCode = "400", description = "Seller not found")
//    })
//    @GetMapping("/totalOrdersForData/{venditoreId}")
//    public ResponseEntity<String> getNumeroOrdiniForDataByVenditore (@PathVariable Long venditoreId, @RequestParam OffsetDateTime dataPerRecuperareOrdini){
//        return ResponseEntity.ok("ordini effettuati: " + ordineAcquistoService.getNumeroOrdiniForDataByVenditore(venditoreId, dataPerRecuperareOrdini));
//    }
//
//    // TODO da testare
//    @Operation(summary = "Get total price for data")
//    @ApiResponses(value = {
//            @ApiResponse(
//                    responseCode = "200", description = "Successfully got total price for data by id seller",
//                    content = {@Content(mediaType = "application/jason", schema = @Schema(implementation = VenditoreDTO.class))}),
//            @ApiResponse(responseCode = "400", description = "seller not found")
//    })
//    @GetMapping("/totalPriceForData/{venditoreId}")
//    public ResponseEntity<String> getCostoOrdiniForDataByVenditore (@PathVariable Long venditoreId, @RequestParam OffsetDateTime dataPerRecuperareOrdini){
//        return ResponseEntity.ok("Totale costo ordini effettuati: " + ordineAcquistoService.getCostoOrdiniForDataByVenditore(venditoreId, dataPerRecuperareOrdini));
//    }
}
