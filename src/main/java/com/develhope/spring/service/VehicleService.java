package com.develhope.spring.service;

import com.develhope.spring.DTOs.Vehicle.CreateVehicleRequest;
import com.develhope.spring.DTOs.Vehicle.VehicleDTO;
import com.develhope.spring.Models.VehicleModel;
import com.develhope.spring.entity.Vehicle;
import com.develhope.spring.entity.enums.VehicleCondition;
import com.develhope.spring.repository.VehicleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class VehicleService {

    @Autowired
    private VehicleRepository vehicleRepository;

    public VehicleDTO createVehicle(CreateVehicleRequest vehicleRequest){
        VehicleModel vehicle = new VehicleModel(vehicleRequest.getMarca(), vehicleRequest.getTipoVeicolo(), vehicleRequest.getModello(), vehicleRequest.getCilindrata(), vehicleRequest.getColore(), vehicleRequest.getPotenza(), vehicleRequest.getTipoDiCambio(), vehicleRequest.getAnnoImmatricolazione(), vehicleRequest.getAlimentazione(), vehicleRequest.getPrezzo(),vehicleRequest.getAllestimento(), vehicleRequest.getAccessori(),vehicleRequest.getVehicleCondition(),vehicleRequest.getOrdinabile(),vehicleRequest.getAcquistabile(),vehicleRequest.getNonDisponibile());
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
}
