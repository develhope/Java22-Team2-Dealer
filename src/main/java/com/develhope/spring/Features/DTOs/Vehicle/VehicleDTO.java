package com.develhope.spring.Features.DTOs.Vehicle;

import com.develhope.spring.Features.Entity.Vehicle.Allestimento;
import com.develhope.spring.Features.Entity.OrdineAcquisto.TipoOrdineAcquisto;
import com.develhope.spring.Features.Entity.Vehicle.TipoVeicolo;
import com.develhope.spring.Features.Entity.Vehicle.VehicleCondition;
import lombok.*;
import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class VehicleDTO {

    private Long veicoloId;

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
    private TipoOrdineAcquisto tipoOrdineAcquisto;

}

