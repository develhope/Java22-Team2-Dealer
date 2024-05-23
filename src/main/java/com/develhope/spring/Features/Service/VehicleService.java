package com.develhope.spring.Features.Service;

import com.develhope.spring.Features.DTOs.Vehicle.CreateVehicleRequest;
import com.develhope.spring.Features.DTOs.Vehicle.VehicleDTO;
import com.develhope.spring.Features.Models.VehicleModel;
import com.develhope.spring.Features.Entity.Vehicle.Vehicle;
import com.develhope.spring.Features.Entity.Vehicle.Allestimento;
import com.develhope.spring.Features.Entity.OrdineAcquisto.TipoOrdineAcquisto;
import com.develhope.spring.Features.Entity.Vehicle.TipoVeicolo;
import com.develhope.spring.Features.Entity.Vehicle.VehicleCondition;
import com.develhope.spring.Features.Repository.VehicleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Service
public class VehicleService {

    @Autowired
    private VehicleRepository vehicleRepository;

    public VehicleDTO createVehicle(CreateVehicleRequest vehicleRequest){
        VehicleModel vehicle = new VehicleModel(vehicleRequest.getMarca(), vehicleRequest.getTipoVeicolo(), vehicleRequest.getModello(), vehicleRequest.getCilindrata(), vehicleRequest.getColore(), vehicleRequest.getPotenza(), vehicleRequest.getTipoDiCambio(), vehicleRequest.getAnnoImmatricolazione(), vehicleRequest.getAlimentazione(), vehicleRequest.getPrezzo(),vehicleRequest.getAllestimento(), vehicleRequest.getAccessori(),vehicleRequest.getVehicleCondition(),vehicleRequest.getTipoOrdineAcquisto());
        VehicleModel vehicleModel1 =  VehicleModel.entityToModel(vehicleRepository.save(VehicleModel.modelToEntity(vehicle)));
        return VehicleModel.modelToDto(vehicleModel1);
    }

    public ResponseEntity<?> deleteVehicle(Long id) {
        if (vehicleRepository.existsById(id)) {
            vehicleRepository.deleteById(id);
            return ResponseEntity.ok().body("Veicolo cancellato con successo");
        } else {
            return ResponseEntity.status(404).body("Veicolo non trovato");
        }
    }

    public void addVeicolo(Vehicle vehicle) {
        vehicleRepository.save(vehicle);
    }

    public Optional<Vehicle> findById(Long id) {
        return vehicleRepository.findById(id);
    }

    public List<Vehicle> searchByMarca(String marca) {
        return vehicleRepository.searchByMarca(marca);
    }

    public void deleteById(Long id) {
        vehicleRepository.deleteById(id);
    }

    public ResponseEntity<?> getVehicleCondition(Long id) {
        Optional<Vehicle> optionalVehicle = vehicleRepository.findById(id);
        if (optionalVehicle.isPresent()) {
            Vehicle vehicle = optionalVehicle.get();
            return ResponseEntity.ok(vehicle.getVehicleCondition());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    public List<Vehicle> searchByModello(String modello) {
        return vehicleRepository.searchByModello(modello);
    }

    public List<Vehicle> searchByTipoVeicolo(TipoVeicolo tipoVeicolo) {
        return vehicleRepository.searchByTipoVeicolo(tipoVeicolo);
    }

    public List<Vehicle> searchByCilindrata(int cilindrata) {
        return vehicleRepository.searchByCilindrata(cilindrata);
    }

    public List<Vehicle> searchByColore(String colore) {
        return vehicleRepository.searchByColore(colore);
    }

    public List<Vehicle> searchByPotenza(int potenza) {
        return vehicleRepository.searchByPotenza(potenza);
    }

    public List<Vehicle> searchByTipoDiCambio(String cambio) {
        return vehicleRepository.searchByTipoDiCambio(cambio);
    }

    public List<Vehicle> searchByAnnoImmatricolazione(Integer annoImmatricolazione) {
        return vehicleRepository.searchByAnnoImmatricolazione(annoImmatricolazione);
    }

    public List<Vehicle> searchByAlimentazione(String alimentazione) {
        return vehicleRepository.searchByAlimentazione(alimentazione);
    }

    public List<Vehicle> searchByPrezzo(BigDecimal prezzo) {
        return vehicleRepository.searchByPrezzo(prezzo);
    }

    public List<Vehicle> searchByAllestimento(Allestimento allestimento) {
        return vehicleRepository.searchByAllestimento(allestimento);
    }

    public List<Vehicle> searchByAccessori(String accessori) {
        return vehicleRepository.searchByAccessori(accessori);
    }

    public List<Vehicle> searchByVehicleCondition(VehicleCondition vehicleCondition) {
        return vehicleRepository.searchByVehicleCondition(vehicleCondition);
    }

    public List<Vehicle> searchByTipoOrdine(TipoOrdineAcquisto tipoOrdineAcquisto) {
        return vehicleRepository.searchByTipoOrdine(tipoOrdineAcquisto);
    }

}
