package com.develhope.spring.Models;

import com.develhope.spring.DTOs.Vehicle.VehicleDTO;
import com.develhope.spring.entity.Vehicle;
import com.develhope.spring.entity.enums.Allestimento;
import com.develhope.spring.entity.enums.TipoOrdine;
import com.develhope.spring.entity.enums.TipoVeicolo;
import com.develhope.spring.entity.enums.VehicleCondition;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.time.Year;

@Data
@NoArgsConstructor
public class VehicleModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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
    private TipoOrdine tipoOrdine;


    public VehicleModel(String marca, TipoVeicolo tipoVeicolo, String modello, int cilindrata, String colore, int potenza, String tipoDiCambio, int annoImmatricolazione, String alimentazione, BigDecimal prezzo,Allestimento allestimento, String accessori, VehicleCondition vehicleCondition, TipoOrdine tipoOrdine) {

        this.marca = marca;
        this.tipoVeicolo = tipoVeicolo;
        this.modello = modello;
        this.cilindrata = cilindrata;
        this.colore = colore;
        this.potenza = potenza;
        this.tipoDiCambio = tipoDiCambio;
        this.annoImmatricolazione = annoImmatricolazione;
        this.alimentazione = alimentazione;
        this.prezzo = prezzo;
        this.allestimento=allestimento;
        this.accessori = accessori;
        this.vehicleCondition = vehicleCondition;
        this.tipoOrdine = tipoOrdine;
    }

    public VehicleModel(Long veicoloId, String marca, TipoVeicolo tipoVeicolo, String modello, int cilindrata, String colore, int potenza, String tipoDiCambio, int annoImmatricolazione, String alimentazione, BigDecimal prezzo, Allestimento allestimento, String accessori, VehicleCondition vehicleCondition, TipoOrdine tipoOrdine) {
        this.veicoloId = veicoloId;
        this.marca = marca;
        this.tipoVeicolo = tipoVeicolo;
        this.modello = modello;
        this.cilindrata = cilindrata;
        this.colore = colore;
        this.potenza = potenza;
        this.tipoDiCambio = tipoDiCambio;
        this.annoImmatricolazione = annoImmatricolazione;
        this.alimentazione = alimentazione;
        this.prezzo = prezzo;
        this.allestimento=allestimento;
        this.accessori = accessori;
        this.vehicleCondition = vehicleCondition;
        this.tipoOrdine = tipoOrdine;
    }

    public static VehicleDTO modelToDto(VehicleModel vehicleModel) {
        return new VehicleDTO(vehicleModel.getVeicoloId(), vehicleModel.getMarca(), vehicleModel.getTipoVeicolo(), vehicleModel.getModello(), vehicleModel.getCilindrata(), vehicleModel.getColore(), vehicleModel.getPotenza(), vehicleModel.getTipoDiCambio(), vehicleModel.getAnnoImmatricolazione(), vehicleModel.getAlimentazione(), vehicleModel.getPrezzo(),vehicleModel.getAllestimento(), vehicleModel.getAccessori(), vehicleModel.getVehicleCondition(), vehicleModel.getTipoOrdine());
    }

    public static Vehicle modelToEntity(VehicleModel vehicleModel) {
        return new Vehicle(vehicleModel.getVeicoloId(), vehicleModel.getMarca(), vehicleModel.getTipoVeicolo(), vehicleModel.getModello(), vehicleModel.getCilindrata(), vehicleModel.getColore(), vehicleModel.getPotenza(), vehicleModel.getTipoDiCambio(), vehicleModel.getAnnoImmatricolazione(), vehicleModel.getAlimentazione(), vehicleModel.getPrezzo(),vehicleModel.getAllestimento(), vehicleModel.getAccessori(), vehicleModel.getVehicleCondition(), vehicleModel.getTipoOrdine());
    }

    public static VehicleModel entityToModel(Vehicle vehicle) {
        return new VehicleModel(vehicle.getVehicleId(), vehicle.getMarca(), vehicle.getTipoVeicolo(), vehicle.getModello(), vehicle.getCilindrata(), vehicle.getColore(), vehicle.getPotenza(), vehicle.getTipoDiCambio(), vehicle.getAnnoImmatricolazione(), vehicle.getAlimentazione(), vehicle.getPrezzo(),vehicle.getAllestimento(), vehicle.getAccessori(), vehicle.getVehicleCondition(), vehicle.getTipoOrdine());
    }

    public static VehicleModel dtoToModel(VehicleDTO vehicleDTO) {
        return new VehicleModel(vehicleDTO.getVeicoloId(), vehicleDTO.getMarca(), vehicleDTO.getTipoVeicolo(), vehicleDTO.getModello(), vehicleDTO.getCilindrata(), vehicleDTO.getColore(), vehicleDTO.getPotenza(), vehicleDTO.getTipoDiCambio(), vehicleDTO.getAnnoImmatricolazione(), vehicleDTO.getAlimentazione(), vehicleDTO.getPrezzo(),vehicleDTO.getAllestimento(), vehicleDTO.getAccessori(), vehicleDTO.getVehicleCondition(), vehicleDTO.getTipoOrdine());
    }
}