package com.develhope.spring.Features.Controller;

import com.develhope.spring.Features.Entity.User.Role;
import com.develhope.spring.Features.Entity.User.User;
import com.develhope.spring.Features.Service.VehicleService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.time.OffsetDateTime;

@RestController
@RequestMapping("/salone")
public class SaloneController {

    @Autowired
    private VehicleService vehicleService;

    @Operation(summary = "Get dealership profit in a given period")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully retrieved the dealership profit",
                    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = BigDecimal.class))}),
            @ApiResponse(responseCode = "401", description = "Unauthorized"),
            @ApiResponse(responseCode = "403", description = "Only admin can access this resource"),
            @ApiResponse(responseCode = "404", description = "No sales or rentals in the given period")
    })
    @GetMapping("/profit")
    public ResponseEntity<?> getDealershipProfitInPeriod(@RequestParam String startDate, @RequestParam String endDate, @AuthenticationPrincipal User user) {
        if (user.getRole() == Role.AMMINISTRATORE) {
            OffsetDateTime start = OffsetDateTime.parse(startDate);
            OffsetDateTime end = OffsetDateTime.parse(endDate);
            BigDecimal profit = vehicleService.calculateDealershipProfit(start, end);
            if (profit == null) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No sales or rentals in the given period");
            }
            return ResponseEntity.ok(profit);
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Unauthorized access");
        }
    }

}
