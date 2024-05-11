package com.develhope.spring.DTOs.Vehicle;

import com.develhope.spring.entity.enums.Allestimento;
import com.develhope.spring.entity.enums.TipoOrdine;
import com.develhope.spring.entity.enums.TipoVeicolo;
import com.develhope.spring.entity.enums.VehicleCondition;
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
    private double cilindrata;
    private String colore;
    private int potenza;
    private String tipoDiCambio;
    private String annoImmatricolazione;
    private String alimentazione;
    private BigDecimal prezzo;
    private Allestimento allestimento;
    private String accessori;
    private VehicleCondition vehicleCondition;
    private TipoOrdine tipoOrdine;

}

