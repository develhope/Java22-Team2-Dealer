package com.develhope.spring.Features.Models;

import com.develhope.spring.Features.DTOs.Vehicle.VehicleDTO;
import com.develhope.spring.Features.Entity.Vehicle.Vehicle;
import com.develhope.spring.Features.Entity.Vehicle.Allestimento;
import com.develhope.spring.Features.Entity.OrdineAcquisto.TipoOrdineAcquisto;
import com.develhope.spring.Features.Entity.Vehicle.TipoVeicolo;
import com.develhope.spring.Features.Entity.Vehicle.VehicleCondition;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.math.BigDecimal;

@Data
@NoArgsConstructor
public class VehicleModel {

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

    public VehicleModel(Long veicoloId, String marca, TipoVeicolo tipoVeicolo, String modello, int cilindrata, String colore, int potenza, String tipoDiCambio, int annoImmatricolazione, String alimentazione, BigDecimal prezzo, Allestimento allestimento, String accessori, VehicleCondition vehicleCondition, TipoOrdineAcquisto tipoOrdineAcquisto) {
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
        this.tipoOrdineAcquisto = tipoOrdineAcquisto;
    }

    public VehicleModel(String marca, TipoVeicolo tipoVeicolo, String modello, int cilindrata, String colore, int potenza, String tipoDiCambio, int annoImmatricolazione, String alimentazione, BigDecimal prezzo,Allestimento allestimento, String accessori, VehicleCondition vehicleCondition, TipoOrdineAcquisto tipoOrdineAcquisto) {
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
        this.tipoOrdineAcquisto = tipoOrdineAcquisto;
    }

    public static VehicleDTO modelToDto(VehicleModel vehicleModel) {
        return new VehicleDTO(vehicleModel.getVeicoloId(), vehicleModel.getMarca(), vehicleModel.getTipoVeicolo(), vehicleModel.getModello(), vehicleModel.getCilindrata(), vehicleModel.getColore(), vehicleModel.getPotenza(), vehicleModel.getTipoDiCambio(), vehicleModel.getAnnoImmatricolazione(), vehicleModel.getAlimentazione(), vehicleModel.getPrezzo(),vehicleModel.getAllestimento(), vehicleModel.getAccessori(), vehicleModel.getVehicleCondition(), vehicleModel.getTipoOrdineAcquisto());
    }

    public static Vehicle modelToEntity(VehicleModel vehicleModel) {
        return new Vehicle(vehicleModel.getVeicoloId(), vehicleModel.getMarca(), vehicleModel.getTipoVeicolo(), vehicleModel.getModello(), vehicleModel.getCilindrata(), vehicleModel.getColore(), vehicleModel.getPotenza(), vehicleModel.getTipoDiCambio(), vehicleModel.getAnnoImmatricolazione(), vehicleModel.getAlimentazione(), vehicleModel.getPrezzo(),vehicleModel.getAllestimento(), vehicleModel.getAccessori(), vehicleModel.getVehicleCondition(), vehicleModel.getTipoOrdineAcquisto());
    }

    public static VehicleModel entityToModel(Vehicle vehicle) {
        return new VehicleModel(vehicle.getVehicleId(), vehicle.getMarca(), vehicle.getTipoVeicolo(), vehicle.getModello(), vehicle.getCilindrata(), vehicle.getColore(), vehicle.getPotenza(), vehicle.getTipoDiCambio(), vehicle.getAnnoImmatricolazione(), vehicle.getAlimentazione(), vehicle.getPrezzo(),vehicle.getAllestimento(), vehicle.getAccessori(), vehicle.getVehicleCondition(), vehicle.getTipoOrdineAcquisto());
    }

    public static VehicleModel dtoToModel(VehicleDTO vehicleDTO) {
        return new VehicleModel(vehicleDTO.getVeicoloId(), vehicleDTO.getMarca(), vehicleDTO.getTipoVeicolo(), vehicleDTO.getModello(), vehicleDTO.getCilindrata(), vehicleDTO.getColore(), vehicleDTO.getPotenza(), vehicleDTO.getTipoDiCambio(), vehicleDTO.getAnnoImmatricolazione(), vehicleDTO.getAlimentazione(), vehicleDTO.getPrezzo(),vehicleDTO.getAllestimento(), vehicleDTO.getAccessori(), vehicleDTO.getVehicleCondition(), vehicleDTO.getTipoOrdineAcquisto());
    }
}