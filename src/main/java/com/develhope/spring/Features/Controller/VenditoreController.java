package com.develhope.spring.Features.Controller;

import com.develhope.spring.Features.DTOs.Noleggio.CreateNoleggioRequest;
import com.develhope.spring.Features.DTOs.Noleggio.NoleggioDTO;
import com.develhope.spring.Features.DTOs.Venditore.CreateVenditoreRequest;
import com.develhope.spring.Features.DTOs.Venditore.VenditoreDTO;
import com.develhope.spring.Features.Entity.Noleggio.Noleggio;
import com.develhope.spring.Features.Entity.User.Role;
import com.develhope.spring.Features.Entity.User.User;
import com.develhope.spring.Features.Entity.Venditore.Venditore;
import com.develhope.spring.Features.Service.NoleggioService;
import com.develhope.spring.Features.Service.UserService;
import com.develhope.spring.Features.Service.VenditoreService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.time.format.DateTimeParseException;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/venditore")
public class VenditoreController {

    @Autowired
    private VenditoreService venditoreService;

    @Operation(summary = "Delete a venditore")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Venditore deleted successfully"),
            @ApiResponse(responseCode = "401", description = "Unauthorized"),
            @ApiResponse(responseCode = "404", description = "Venditore not found")
    })
    @DeleteMapping("/{venditoreId}")
    public ResponseEntity<?> deleteVenditore(@PathVariable Long venditoreId, @AuthenticationPrincipal User user) {
        if (user.getRole() == Role.AMMINISTRATORE) {
            try {
                venditoreService.deleteVenditore(venditoreId);
                return ResponseEntity.ok("Venditore deleted successfully");
            } catch (RuntimeException e) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Venditore not found");
            }
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Unauthorized access");
        }
    }

    @Operation(summary = "Update a venditore")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Venditore updated successfully"),
            @ApiResponse(responseCode = "401", description = "Unauthorized"),
            @ApiResponse(responseCode = "404", description = "Venditore not found")
    })
    @PutMapping("/{venditoreId}")
    public ResponseEntity<?> updateVenditore(@PathVariable Long venditoreId, @RequestBody User venditoreDetails, @AuthenticationPrincipal User user) {
        if (user.getRole() == Role.AMMINISTRATORE) {
            try {
                User updatedVenditore = venditoreService.updateVenditore(venditoreId, venditoreDetails);
                return ResponseEntity.ok(updatedVenditore);
            } catch (RuntimeException e) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Venditore not found");
            }
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Unauthorized access");
        }
    }

    @Operation(summary = "Get the number of sales made by a venditore in a given period")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully retrieved sales count"),
            @ApiResponse(responseCode = "400", description = "Invalid date format"),
            @ApiResponse(responseCode = "401", description = "Unauthorized"),
            @ApiResponse(responseCode = "404", description = "Venditore not found")
    })
    @GetMapping("/{id}/salesCount")
    public ResponseEntity<?> getSalesCount(@PathVariable Long id, @RequestParam String startDate, @RequestParam String endDate, @AuthenticationPrincipal User user) {
        if (user.getRole() == Role.AMMINISTRATORE) {
            try {
                OffsetDateTime start = OffsetDateTime.parse(startDate);
                OffsetDateTime end = OffsetDateTime.parse(endDate);
                long salesCount = venditoreService.getSalesCount(id, start, end);
                return ResponseEntity.ok(salesCount);
            } catch (DateTimeParseException e) {
                return ResponseEntity.badRequest().body("Invalid date format. Please provide dates in ISO-8601 format");
            } catch (RuntimeException e) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Venditore not found");
            }
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Unauthorized access");
        }
    }

    @Operation(summary = "Get the total profit generated by a venditore in a given period")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully retrieved profit"),
            @ApiResponse(responseCode = "400", description = "Invalid date format"),
            @ApiResponse(responseCode = "401", description = "Unauthorized"),
            @ApiResponse(responseCode = "404", description = "Venditore not found")
    })
    @GetMapping("/{id}/profit")
    public ResponseEntity<?> getProfit(@PathVariable Long id, @RequestParam String startDate, @RequestParam String endDate, @AuthenticationPrincipal User user) {
        if (user.getRole() == Role.AMMINISTRATORE) {
            try {
                OffsetDateTime start = OffsetDateTime.parse(startDate);
                OffsetDateTime end = OffsetDateTime.parse(endDate);
                BigDecimal profit = venditoreService.getProfit(id, start, end);
                return ResponseEntity.ok(profit);
            } catch (DateTimeParseException e) {
                return ResponseEntity.badRequest().body("Invalid date format. Please provide dates in ISO-8601 format");
            } catch (RuntimeException e) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Venditore not found");
            }
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Unauthorized access");
        }
    }
}

//    @Autowired
//    private VenditoreService venditoreService;
//
//    @Autowired
//    private NoleggioService noleggioService;
//
//    @Autowired
//    private UserService userService;
//
//    // Route delete seller
//    @Operation(summary = "Delete Seller")
//    @ApiResponses(value = {
//            @ApiResponse(
//                    responseCode = "200", description = "Successfully deleted Seller",
//                    content = {@Content(mediaType = "application/jason", schema = @Schema(implementation = VenditoreDTO.class))}),
//            @ApiResponse(responseCode = "400", description = "Seller not found.")
//    })
//
//    @DeleteMapping("/remove/{id}")
//    public ResponseEntity<?> deleteAcquirente(@PathVariable Long id, @AuthenticationPrincipal User user) {
//        try {
//            Optional<User> deletedAcquirente = venditoreService.deleteById(id, user);
//            if (deletedAcquirente.isPresent()) {
//                return ResponseEntity.ok("User information deleted successfully");
//            } else {
//                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User with id " + id + " not found");
//            }
//        } catch (Exception e) {
//            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(e.getMessage());
//        }
//    }
//
//    @Operation(summary = "Modify Seller by ID")
//    @ApiResponses(value = {
//            @ApiResponse(
//                    responseCode = "200", description = "Successfully modified seller by id",
//                    content = {@Content(mediaType = "application/jason", schema = @Schema(implementation = VenditoreDTO.class))}),
//            @ApiResponse(responseCode = "400", description = "Seller not found")
//    })
//    @PutMapping("/set/{id}")
//    public ResponseEntity<?> updateVenditore(@PathVariable Long id, @RequestBody User userMod, @AuthenticationPrincipal User callingUser) {
//        try {
//            User updatedVenditore = venditoreService.updateVenditore(id, userMod, callingUser);
//            if (updatedVenditore != null) {
//                return ResponseEntity.ok(updatedVenditore);
//            } else {
//                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User with id " + id + " not found");
//            }
//        } catch (Exception e) {
//            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(e.getMessage());
//        }
//    }



//    // Route search sellers by ID
//    @Operation(summary = "Search sellers by ID")
//    @ApiResponses(value = {
//            @ApiResponse(
//                    responseCode = "200", description = "Successfully got seller by id",
//                    content = {@Content(mediaType = "application/jason", schema = @Schema(implementation = VenditoreDTO.class))}),
//            @ApiResponse(responseCode = "400", description = "No seller found")
//    })
//    @GetMapping("/getById/{id}")
//    public Optional<Venditore> getVenditoreById(@PathVariable Long id) {
//        return venditoreService.getVenditoreById(id);
//    }
//
//    //Create rental
//    @Operation(summary = "Create a rental",
//            description = "This endpoint allows to create a new Rentasl by providing the necessary information in the request body."
//    )
//    @ApiResponses(value = {
//            @ApiResponse(
//                    responseCode = "200", description = "Successfully created the rental",
//                    content = {@Content(mediaType = "application/jason", schema = @Schema(implementation = VenditoreDTO.class))}),
//            @ApiResponse(responseCode = "400", description = "Invalid input or missing required fields")
//    })
//    @PostMapping("/addNoleggio")
//    public ResponseEntity<?> createNoleggio(@RequestBody CreateNoleggioRequest createNoleggioRequest){
//        NoleggioDTO result = noleggioService.createNoleggio(createNoleggioRequest);
//        if (result == null) {
//            return ResponseEntity.status(400).body("Invalid input or missing required fields");
//        } else {
//            return ResponseEntity.ok().body(result);
//        }
//    }
//
//    // Route delete rental
//    @Operation(summary = "Delete rental")
//    @ApiResponses(value = {
//            @ApiResponse(
//                    responseCode = "200", description = "Successfully deleted the rental",
//                    content = {@Content(mediaType = "application/jason", schema = @Schema(implementation = VenditoreDTO.class))}),
//            @ApiResponse(responseCode = "400", description = "Rental not found")
//    })
//    @DeleteMapping("/deleteNoleggio/{id}")
//    public ResponseEntity<String> deleteNoleggioById(@PathVariable Long id){
//        Optional<Noleggio> optionalNoleggio = noleggioService.deleteNoleggio(id);
//        if (optionalNoleggio.isPresent()) {
//            return ResponseEntity.ok("Noleggio eliminato.");
//        }
//        return ResponseEntity.badRequest().body("Noleggio non trovato.");
//    }
//
//    //Modify rental
//    @Operation(summary = "Modify rental by id")
//    @ApiResponses(value = {
//            @ApiResponse(
//                    responseCode = "200", description = "Successfully modified rental by id",
//                    content = {@Content(mediaType = "application/jason", schema = @Schema(implementation = VenditoreDTO.class))}),
//            @ApiResponse(responseCode = "400", description = "Rental not found")
//    })
//    @PutMapping("/setNoleggio/{id}")
//    public ResponseEntity<String> setNoleggioById (@PathVariable Long id, @RequestBody CreateNoleggioRequest noleggioAggiornato){
//        Noleggio noleggio = noleggioService.updateNoleggioById(id, noleggioAggiornato);
//        if (noleggio != null) {
//            return ResponseEntity.ok("Rental modified");
//        }
//        return ResponseEntity.badRequest().body("Rental not found.");
//    }
