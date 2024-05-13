package com.develhope.spring.DTOs.Vehicle;

import com.develhope.spring.entity.enums.Allestimento;
import com.develhope.spring.entity.enums.TipoOrdine;
import com.develhope.spring.entity.enums.TipoVeicolo;
import com.develhope.spring.entity.enums.VehicleCondition;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UpdateVehicleRequest {
    private String marca;
    private TipoVeicolo tipoVeicolo;
    private String modello;
    private int cilindrata;
    private String colore;
    private int potenza;
    private String tipoDiCambio;
    private int annoImmatricolazione;
    private String alimentazione;
    private BigDecimal prezzo;
    private Allestimento allestimento;
    private String accessori;
    private VehicleCondition vehicleCondition;
    private TipoOrdine tipoOrdine;

}
