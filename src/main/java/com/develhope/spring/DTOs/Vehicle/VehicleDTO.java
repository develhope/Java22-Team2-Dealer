package com.develhope.spring.DTOs.Vehicle;
import com.develhope.spring.entity.enums.Allestimento;
import com.develhope.spring.entity.enums.TipoVeicolo;
import com.develhope.spring.entity.enums.VehicleCondition;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter

public class VehicleDTO {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long veicoloId;
    @Column(name = "marca", nullable = false)
    private String marca;
    @Enumerated(EnumType.STRING)
    @Column(name = "tipoVeicolo", nullable = false)
    private TipoVeicolo tipoVeicolo;
    @Column(name = "modello", nullable = false)
    private String modello;
    @Column(name = "cilindrata", nullable = false)
    private double cilindrata;
    @Column(name = "colore", nullable = false)
    private String colore;
    @Column(name = "potenza", nullable = false)
    private int potenza;
    @Column(name = "tipoDiCambio", nullable = false)
    private String tipoDiCambio;
    @Column(name = "annoImmatricolazione", nullable = false)
    private String annoImmatricolazione;
    @Column(name = "alimentazione", nullable = false)
    private String alimentazione;
    @Column(name = "prezzo", nullable = false)
    private BigDecimal prezzo;
    @Enumerated(EnumType.STRING)
    @Column(name = "allestimento", nullable = false, updatable = false)
    private Allestimento allestimento;
    @Column(name = "accessori", nullable = false)
    private String accessori;
    @Enumerated(EnumType.STRING)
    @Column(name = "vehicleCondition", nullable = false)
    private VehicleCondition vehicleCondition;
    @Column(name = "ordinabile", nullable = false)
    private Boolean ordinabile;
    @Column(name = "acquistabile", nullable = false)
    private Boolean acquistabile;
    @Column(name = "nonDisponibile", nullable = false)
    private Boolean nonDisponibile;

}

