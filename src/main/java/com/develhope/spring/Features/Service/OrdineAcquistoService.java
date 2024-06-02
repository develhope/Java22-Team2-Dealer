package com.develhope.spring.Features.Service;

import com.develhope.spring.Features.DTOs.OrdineAcquisto.UpdateOrdineAcquistoRequest;
import com.develhope.spring.Features.Entity.OrdineAcquisto.OrdineAcquisto;
import com.develhope.spring.Features.Entity.OrdineAcquisto.StatoOrdineAcquisto;
import com.develhope.spring.Features.Entity.OrdineAcquisto.TipoOrdineAcquisto;
import com.develhope.spring.Features.Entity.Vehicle.Vehicle;
import com.develhope.spring.Features.Repository.OrdineAcquistoRepository;
import com.develhope.spring.Features.Repository.VehicleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrdineAcquistoService {

    @Autowired
    private OrdineAcquistoRepository ordineAcquistoRepository;

    @Autowired
    private VehicleRepository vehicleRepository;

    public OrdineAcquisto createOrdineAcquisto(Long vehicleId, OrdineAcquisto ordineAcquisto) throws ResourceNotFoundException {
        Vehicle vehicle = vehicleRepository.findById(vehicleId)
                .orElseThrow(() -> new ResourceNotFoundException("Vehicle not found with id: " + vehicleId));

        if (vehicle.getTipoOrdineAcquisto() == TipoOrdineAcquisto.NON_DISPONIBILE) {
            throw new IllegalArgumentException("Vehicle not available: " + vehicleId);
        }

        ordineAcquisto.setVehicle(vehicle);

        return ordineAcquistoRepository.save(ordineAcquisto);
    }

    public void deleteOrdineAcquisto(Long ordineAcquistoId) throws ResourceNotFoundException {
        OrdineAcquisto ordineAcquisto = ordineAcquistoRepository.findById(ordineAcquistoId)
                .orElseThrow(() -> new ResourceNotFoundException("Order not found with id: " + ordineAcquistoId));

        ordineAcquistoRepository.delete(ordineAcquisto);
    }

    public OrdineAcquisto updateOrdineAcquisto(Long ordineAcquistoId, UpdateOrdineAcquistoRequest request) throws ResourceNotFoundException {
        OrdineAcquisto existingOrdineAcquisto = ordineAcquistoRepository.findById(ordineAcquistoId)
                .orElseThrow(() -> new ResourceNotFoundException("Order not found with id: " + ordineAcquistoId));

        Vehicle vehicle = new Vehicle();
        if (request.getVehicleId() != null) {
            vehicle = vehicleRepository.findById(request.getVehicleId()).orElseThrow(() -> new ResourceNotFoundException("Vehicle not found with id: " + request.getVehicleId()));
        }

        existingOrdineAcquisto.setDataOrdineAcquisto(request.getDataOrdineAcquisto() == null ? existingOrdineAcquisto.getDataOrdineAcquisto() : request.getDataOrdineAcquisto());
        existingOrdineAcquisto.setAnticipo(request.getStatoOrdineAcquisto() == null ? existingOrdineAcquisto.getAnticipo() : request.getAnticipo());
        existingOrdineAcquisto.setCostoTotale(request.getCostoTotale() == null ? existingOrdineAcquisto.getCostoTotale() : request.getCostoTotale());
        existingOrdineAcquisto.setFlagPagato(request.getFlagPagato() == null ? existingOrdineAcquisto.getFlagPagato() : request.getFlagPagato());
        existingOrdineAcquisto.setStatoOrdineAcquisto(request.getStatoOrdineAcquisto() == null ? existingOrdineAcquisto.getStatoOrdineAcquisto() : request.getStatoOrdineAcquisto());
        existingOrdineAcquisto.setVehicle(request.getVehicleId() == null ? existingOrdineAcquisto.getVehicle() : vehicle);

        return ordineAcquistoRepository.save(existingOrdineAcquisto);
    }

    public StatoOrdineAcquisto getStatoOrdineAcquisto(Long ordineAcquistoId) throws ResourceNotFoundException {
        OrdineAcquisto ordineAcquisto = ordineAcquistoRepository.findById(ordineAcquistoId)
                .orElseThrow(() -> new ResourceNotFoundException("Order not found with id: " + ordineAcquistoId));
        return ordineAcquisto.getStatoOrdineAcquisto();
    }

    public void updateStatoOrdineAcquisto(Long ordineAcquistoId, StatoOrdineAcquisto nuovoStato) throws ResourceNotFoundException {
        OrdineAcquisto ordineAcquisto = ordineAcquistoRepository.findById(ordineAcquistoId)
                .orElseThrow(() -> new ResourceNotFoundException("Order not found with id: " + ordineAcquistoId));
        ordineAcquisto.setStatoOrdineAcquisto(nuovoStato);
        ordineAcquistoRepository.save(ordineAcquisto);
    }

    public List<OrdineAcquisto> getOrdiniAcquistiByStato(StatoOrdineAcquisto stato) {
        return ordineAcquistoRepository.findByStatoOrdineAcquisto(stato);
    }
}
