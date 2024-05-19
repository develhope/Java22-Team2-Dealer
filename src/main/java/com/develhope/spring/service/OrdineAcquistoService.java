package com.develhope.spring.service;

import com.develhope.spring.DTOs.OrdineAcquisto.CreateOrdineAcquistoRequest;
import com.develhope.spring.DTOs.OrdineAcquisto.OrdineAcquistoDTO;
import com.develhope.spring.DTOs.OrdineAcquisto.UpdateOrdineAcquistoRequest;
import com.develhope.spring.Models.OrdineAcquistoModel;
import com.develhope.spring.entity.*;
import com.develhope.spring.entity.enums.TipoOrdine;
import com.develhope.spring.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.stereotype.Service;

import java.time.OffsetDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class OrdineAcquistoService {

    @Autowired
    private AcquirenteRepository acquirenteRepository;

    @Autowired
    private VehicleRepository vehicleRepository;

    @Autowired
    private VenditoreRepository venditoreRepository;

    @Autowired
    private OrdineAcquistoRepository ordineAcquistoRepository;

    // Creare un ordineAcquisto per un utente a partire da un veicolo ordinabile / acquistabile
    public OrdineAcquisto createOrdineAcquisto(Long acquirenteId, Long vehicleId, OrdineAcquisto ordineAcquisto) {
        Acquirente acquirente = acquirenteRepository.findById(acquirenteId).orElseThrow(() -> new ResourceNotFoundException("Acquirente non trovato"));
        Vehicle vehicle = vehicleRepository.findById(vehicleId).orElseThrow(() -> new ResourceNotFoundException("Veicolo non trovato"));
        Venditore venditore = venditoreRepository.findById(ordineAcquisto.getVenditore().getVenditoreId()).orElseThrow(() -> new ResourceNotFoundException("Venditore non trovato"));

        if (vehicle.getTipoOrdine() == TipoOrdine.NON_DISPONIBILE) {
            throw new IllegalArgumentException("Veicolo non acquistabile");
        }

        ordineAcquisto.setAcquirente(acquirente);
        ordineAcquisto.setVehicle(vehicle);
        ordineAcquisto.setVenditore(venditore);

        ordineAcquisto = ordineAcquistoRepository.save(ordineAcquisto);
        return ordineAcquisto;
    }

    // Cancellare un ordineAcquisto per un utente
    public void deleteOrdineAcquisto(Long acquirenteId, Long ordineAcquistoId) {
        OrdineAcquisto ordineAcquisto = ordineAcquistoRepository.findByOrdineAcquistoIdAndAcquirenteId(ordineAcquistoId, acquirenteId)
                .orElseThrow(() -> new ResourceNotFoundException("OrdineAcquisto non trovato"));

        ordineAcquistoRepository.delete(ordineAcquisto);
    }

    // Modificare un ordineAcquisto per un utente
    public OrdineAcquisto updateOrdineAcquisto(Long acquirenteId, Long ordineAcquistoId, OrdineAcquisto ordineAcquisto) {
        OrdineAcquisto updateOrdineAcquisto = ordineAcquistoRepository.findByOrdineAcquistoIdAndAcquirenteId(ordineAcquistoId, acquirenteId)
                .orElseThrow(() -> new ResourceNotFoundException("OrdineAcquisto non trovato"));

        updateOrdineAcquisto.setDataOrdineAcquisto(ordineAcquisto.getDataOrdineAcquisto());
        updateOrdineAcquisto.setDataConsegna(ordineAcquisto.getDataConsegna());
        updateOrdineAcquisto.setAnticipo(ordineAcquisto.getAnticipo());
        updateOrdineAcquisto.setCostoTotale(ordineAcquisto.getCostoTotale());
        updateOrdineAcquisto.setFlagPagato(ordineAcquisto.getFlagPagato());
        updateOrdineAcquisto.setStatoOrdineAcquisto(ordineAcquisto.getStatoOrdineAcquisto());
        updateOrdineAcquisto.setVehicle(ordineAcquisto.getVehicle());
        updateOrdineAcquisto.setVenditore(ordineAcquisto.getVenditore());

        Acquirente acquirente = acquirenteRepository.findById(acquirenteId).orElse(null);
        Vehicle vehicle = vehicleRepository.findById(ordineAcquisto.getVehicle().getVehicleId()).orElse(null);
        Venditore venditore = venditoreRepository.findById(ordineAcquisto.getVenditore().getVenditoreId()).orElse(null);

        if (acquirente == null || vehicle == null || venditore == null) {
            throw new IllegalArgumentException("Acquirente, Vehicle o Venditore non trovato");
        } else if (vehicle.getTipoOrdine() == TipoOrdine.NON_DISPONIBILE) {
            throw new IllegalArgumentException("Veicolo non acquistabile");
        }

        updateOrdineAcquisto = ordineAcquistoRepository.save(updateOrdineAcquisto);
        return new OrdineAcquisto(updateOrdineAcquisto);
    }

    // Metodo per ottenere gli ordini di acquisto di un determinato acquirente
    public List<OrdineAcquisto> getOrdiniAcquistiByAcquirente(Long acquirenteId) {
        Acquirente acquirente = acquirenteRepository.findById(acquirenteId)
                .orElseThrow(() -> new ResourceNotFoundException("Acquirente non trovato: " + acquirenteId));

        List<OrdineAcquisto> ordiniAcquisto = ordineAcquistoRepository.findByAcquirenteId(acquirenteId);
        return ordiniAcquisto.stream().map(OrdineAcquisto::new).collect(Collectors.toList());
    }

}
