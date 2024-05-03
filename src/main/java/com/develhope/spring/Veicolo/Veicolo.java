package com.develhope.spring.Veicolo;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table
public class Veicolo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long veicoloId;

    private String marca;
    private String modello;
    private String cilindrata;
    private String colore;
    private String potenza;
    private String tipoDiCambio;
    private String annoImmatricolazione;
    private String alimentazione;
    private BigDecimal prezzo;
    private String accessori;
    private Boolean nuovoUsato;
    private Boolean ordinabile;
    private Boolean acquistabile;
    private Boolean nonDisponibile;

}