package com.develhope.spring.Features.Service;

import com.develhope.spring.Features.Entity.OrdineAcquisto.OrdineAcquisto;
import com.develhope.spring.Features.Entity.OrdineAcquisto.StatoOrdineAcquisto;
import com.develhope.spring.Features.Entity.OrdineAcquisto.TipoOrdineAcquisto;
import com.develhope.spring.Features.Entity.Vehicle.Vehicle;
import com.develhope.spring.Features.Repository.OrdineAcquistoRepository;
import com.develhope.spring.Features.Repository.UserRepository;
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
    private UserRepository userRepository;

    @Autowired
    private VehicleRepository vehicleRepository;

    public OrdineAcquisto createOrdineAcquisto(Long vehicleId, OrdineAcquisto ordineAcquisto) throws ResourceNotFoundException {
        Vehicle vehicle = vehicleRepository.findById(vehicleId)
                .orElseThrow(() -> new ResourceNotFoundException("Veicolo non trovato con id: " + vehicleId));

        if (vehicle.getTipoOrdineAcquisto() == TipoOrdineAcquisto.NON_DISPONIBILE) {
            throw new IllegalArgumentException("Veicolo non disponibile: " + vehicleId);
        }

        ordineAcquisto.setVehicle(vehicle);

        return ordineAcquistoRepository.save(ordineAcquisto);
    }

    public void deleteOrdineAcquisto(Long ordineAcquistoId) throws ResourceNotFoundException {
        OrdineAcquisto ordineAcquisto = ordineAcquistoRepository.findById(ordineAcquistoId)
                .orElseThrow(() -> new ResourceNotFoundException("Ordine non trovato con id: " + ordineAcquistoId));

        ordineAcquistoRepository.delete(ordineAcquisto);
    }

    public OrdineAcquisto updateOrdineAcquisto(Long ordineAcquistoId, OrdineAcquisto ordineAcquisto) throws ResourceNotFoundException {
        OrdineAcquisto existingOrdineAcquisto = ordineAcquistoRepository.findById(ordineAcquistoId)
                .orElseThrow(() -> new ResourceNotFoundException("Ordine non trovato con id: " + ordineAcquistoId));

        existingOrdineAcquisto.setDataOrdineAcquisto(ordineAcquisto.getDataOrdineAcquisto());
        existingOrdineAcquisto.setAnticipo(ordineAcquisto.getAnticipo());
        existingOrdineAcquisto.setCostoTotale(ordineAcquisto.getCostoTotale());
        existingOrdineAcquisto.setFlagPagato(ordineAcquisto.getFlagPagato());
        existingOrdineAcquisto.setStatoOrdineAcquisto(ordineAcquisto.getStatoOrdineAcquisto());
        existingOrdineAcquisto.setVehicle(ordineAcquisto.getVehicle());

        return ordineAcquistoRepository.save(existingOrdineAcquisto);
    }

    public StatoOrdineAcquisto getStatoOrdineAcquisto(Long ordineAcquistoId) throws ResourceNotFoundException {
        OrdineAcquisto ordineAcquisto = ordineAcquistoRepository.findById(ordineAcquistoId)
                .orElseThrow(() -> new ResourceNotFoundException("Ordine non trovato con id: " + ordineAcquistoId));
        return ordineAcquisto.getStatoOrdineAcquisto();
    }

    public void updateStatoOrdineAcquisto(Long ordineAcquistoId, StatoOrdineAcquisto nuovoStato) throws ResourceNotFoundException {
        OrdineAcquisto ordineAcquisto = ordineAcquistoRepository.findById(ordineAcquistoId)
                .orElseThrow(() -> new ResourceNotFoundException("Ordine non trovato con id: " + ordineAcquistoId));
        ordineAcquisto.setStatoOrdineAcquisto(nuovoStato);
        ordineAcquistoRepository.save(ordineAcquisto);
    }

    public List<OrdineAcquisto> getOrdiniAcquistiByStato(StatoOrdineAcquisto stato) {
        return ordineAcquistoRepository.findByStatoOrdineAcquisto(stato);
    }

}
