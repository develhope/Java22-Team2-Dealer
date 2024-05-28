package com.develhope.spring.Features.Controller;

import com.develhope.spring.Features.DTOs.Noleggio.CreateNoleggioRequest;
import com.develhope.spring.Features.DTOs.Noleggio.NoleggioDTO;
import com.develhope.spring.Features.DTOs.Noleggio.UpdateNoleggioRequest;
import com.develhope.spring.Features.DTOs.Venditore.VenditoreDTO;
import com.develhope.spring.Features.Entity.Noleggio.Noleggio;
import com.develhope.spring.Features.Entity.Noleggio.NoleggioLink;
import com.develhope.spring.Features.Entity.User.Role;
import com.develhope.spring.Features.Entity.User.User;
import com.develhope.spring.Features.Models.NoleggioModel;
import com.develhope.spring.Features.Service.AcquirenteService;
import com.develhope.spring.Features.Service.NoleggioService;
import com.develhope.spring.Features.Service.OrdineAcquistoService;
import com.develhope.spring.Features.Service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.nio.file.AccessDeniedException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/noleggio")
public class NoleggioController {

    @Autowired
    private AcquirenteService acquirenteService;

    @Autowired
    private NoleggioService noleggioService;

    @Autowired
    private UserService userService;

    @Autowired
    private OrdineAcquistoService ordineAcquistoService;

    // ACQUIRENTE

    // Route for creating a rental by customer ID
    @Operation(summary = "Create a rental for a customer")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully created the rental"),
            @ApiResponse(responseCode = "400", description = "Invalid input or missing required fields"),
            @ApiResponse(responseCode = "403", description = "Unauthorized to create rental for this customer")
    })
    @PostMapping("/{acquirenteId}/noleggi/add")
    public ResponseEntity<?> addNoleggio(@PathVariable Long acquirenteId,
                                         @Validated @RequestBody CreateNoleggioRequest createNoleggioRequest,
                                         @AuthenticationPrincipal User callingUser) {
        try {
            if (callingUser.getRole() != Role.ACQUIRENTE || callingUser.getRole() != Role.AMMINISTRATORE && !callingUser.getUserId().equals(acquirenteId)) {
                return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Unauthorized to create rental for this customer");
            }

            NoleggioDTO nuovoNoleggio = noleggioService.createNoleggio(createNoleggioRequest, acquirenteId); // Chiama createNoleggio, non createNoleggioForAcquirente
            return ResponseEntity.ok(nuovoNoleggio);

        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }



    // Route delete rental
    @Operation(summary = "Delete rental")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully deleted the rental"),
            @ApiResponse(responseCode = "404", description = "Rental not found or unauthorized access"),
            @ApiResponse(responseCode = "403", description = "Unauthorized to delete this rental")
    })
    @DeleteMapping("/{acquirenteId}/noleggi/remove/{noleggioLinkId}")
    public ResponseEntity<String> deleteNoleggioByAcquirenteId(@PathVariable Long acquirenteId,
                                                               @PathVariable Long noleggioLinkId,
                                                               @AuthenticationPrincipal User callingUser) {

        try {
            Optional<NoleggioLink> noleggioLinkOptional = noleggioService.findById(noleggioLinkId);
            if (noleggioLinkOptional.isEmpty()) {
                return ResponseEntity.notFound().build();
            }

            NoleggioLink noleggioLink = noleggioLinkOptional.get();
            Noleggio noleggio = noleggioLink.getNoleggio();

            if (!noleggioLink.getAcquirente().getUserId().equals(callingUser.getUserId()) &&
                    callingUser.getRole() != Role.AMMINISTRATORE) {
                throw new AccessDeniedException("Non autorizzato a eliminare questo noleggio");
            }

            noleggioService.deleteNoleggio(noleggio.getNoleggioId());
            return ResponseEntity.ok("Rental deleted.");

        } catch (IllegalArgumentException e) {
            return ResponseEntity.notFound().build();
        } catch (org.springframework.security.access.AccessDeniedException e) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    // Route search rentals by customer ID
    @Operation(summary = "Search rentals by Customer ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully got rentals by customer ID"),
            @ApiResponse(responseCode = "404", description = "No rentals found for the customer"),
            @ApiResponse(responseCode = "403", description = "Unauthorized access to rentals")
    })
    @GetMapping("/{acquirenteId}/noleggi")
    public ResponseEntity<?> getNoleggi(@PathVariable Long acquirenteId, @AuthenticationPrincipal User callingUser) {
        try {
            if (callingUser.getRole() != Role.AMMINISTRATORE && !callingUser.getUserId().equals(acquirenteId)) {
                return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Unauthorized access to rentals");
            }

            List<NoleggioLink> noleggiLink = noleggioService.findByAcquirente(acquirenteId);
            if (noleggiLink.isEmpty()) {
                return ResponseEntity.notFound().build();
            }

            List<NoleggioDTO> noleggiDto = noleggiLink.stream()
                    .map(NoleggioLink::getNoleggio)
                    .map(NoleggioModel::entityToModel)
                    .map(NoleggioModel::modelToDto)
                    .collect(Collectors.toList());

            return ResponseEntity.ok(noleggiDto);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    // VENDITORE

    // Crea noleggio
    @Operation(summary = "Create a rental",
            description = "This endpoint allows a seller or admin to create a new Rental by providing the necessary information in the request body."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully created the rental"),
            @ApiResponse(responseCode = "400", description = "Invalid input or missing required fields"),
            @ApiResponse(responseCode = "403", description = "Unauthorized to create the rental")
    })
    @PostMapping("/noleggi/add/{acquirenteId}")
    public ResponseEntity<?> createNoleggio(@RequestBody CreateNoleggioRequest createNoleggioRequest,
                                            @PathVariable Long acquirenteId,
                                            @AuthenticationPrincipal UserDetails userDetails) {
        try {
            boolean isVenditoreOrAdmin = userDetails.getAuthorities().stream()
                    .anyMatch(auth -> auth.getAuthority().equals(Role.VENDITORE.name()) ||
                            auth.getAuthority().equals(Role.AMMINISTRATORE.name()));

            if (!isVenditoreOrAdmin) {
                return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Unauthorized to create the rental");
            }

            NoleggioDTO result = noleggioService.createNoleggio(createNoleggioRequest, acquirenteId);
            return ResponseEntity.ok().body(result);

        } catch (IllegalArgumentException | org.springframework.security.access.AccessDeniedException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }


    // Route delete rental
    @Operation(summary = "Delete rental")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully deleted the rental"),
            @ApiResponse(responseCode = "404", description = "Rental not found"),
            @ApiResponse(responseCode = "403", description = "Unauthorized to delete this rental")
    })
    @DeleteMapping("/deleteNoleggio/{noleggioLinkId}")
    public ResponseEntity<String> deleteNoleggioById(@PathVariable Long noleggioLinkId,
                                                     @AuthenticationPrincipal org.apache.catalina.User callingUser) {
        try {
            Optional<NoleggioLink> noleggioLinkOptional = noleggioService.findById(noleggioLinkId);
            if (noleggioLinkOptional.isEmpty()) {
                return ResponseEntity.notFound().build();
            }

            NoleggioLink noleggioLink = noleggioLinkOptional.get();
            noleggioService.deleteNoleggio(noleggioLinkId);
            return ResponseEntity.ok("Noleggio eliminato.");

        } catch (IllegalArgumentException e) { // NoleggioLink non trovato
            return ResponseEntity.notFound().build();
        } catch (org.springframework.security.access.AccessDeniedException e) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(e.getMessage());
        } catch (Exception e) { // Cattura altre eccezioni non previste
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }


    // Modifica noleggio
    @Operation(summary = "Modify rental by ID")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200", description = "Successfully modified rental by id",
                    content = {@Content(mediaType = "application/jason", schema = @Schema(implementation = VenditoreDTO.class))}),
            @ApiResponse(responseCode = "400", description = "Rental not found or unauthorized access"),
            @ApiResponse(responseCode = "403", description = "Unauthorized to modify this rental")
    })
    @PatchMapping("/setNoleggio/{id}")
    public ResponseEntity<String> setNoleggioById (@PathVariable Long id, @RequestBody UpdateNoleggioRequest updateNoleggioRequest, @AuthenticationPrincipal org.apache.catalina.User callingUser){
        try{
            Noleggio noleggio = noleggioService.updateNoleggio(id, updateNoleggioRequest);
            if (noleggio != null) {
                return ResponseEntity.ok("Rental modified");
            }
            return ResponseEntity.badRequest().body("Rental not found.");
        }
        catch(Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

}
