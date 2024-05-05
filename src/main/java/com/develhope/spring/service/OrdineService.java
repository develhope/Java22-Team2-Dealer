package com.develhope.spring.service;

import com.develhope.spring.DTOs.Ordine.CreateOrdineRequest;
import com.develhope.spring.DTOs.Ordine.OrdineDTO;
import com.develhope.spring.Models.OrdineModel;
import com.develhope.spring.entity.Acquirente;
import com.develhope.spring.entity.Vehicle;
import com.develhope.spring.entity.Venditore;
import com.develhope.spring.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.OffsetDateTime;

@Service
public class OrdineService {

    @Autowired
    private AcquirenteRepository acquirenteRepository;

    @Autowired
    private VehicleRepository vehicleRepository;

    @Autowired
    private VenditoreRepository venditoreRepository;

    @Autowired
    private OrdineRepository ordineRepository;

    public OrdineDTO createOrdine(CreateOrdineRequest createOrdineRequest) {
        OffsetDateTime dateTime = createOrdineRequest.getDataOrdine() != null ? createOrdineRequest.getDataOrdine() : OffsetDateTime.now();
        OffsetDateTime dateTime2 = dateTime.plusDays(21);

        Acquirente acquirente = acquirenteRepository.findById(createOrdineRequest.getAcquirenteId()).orElse(null);
        Vehicle vehicle = vehicleRepository.findById(createOrdineRequest.getVehicleId()).orElse(null);
        Venditore venditore = venditoreRepository.findById(createOrdineRequest.getVenditoreId()).orElse(null);

        if (acquirente == null || vehicle == null || venditore == null ) {
            throw new IllegalArgumentException("Acquirente, Vehicle o Venditore non trovato");
        } else if (!vehicle.getAcquistabile()) {
            throw new IllegalArgumentException("Veicolo non acquistabile");
        }

        OrdineModel ordineModel = new OrdineModel(dateTime, dateTime2, createOrdineRequest.getAnticipo(), createOrdineRequest.getFlagPagato(), createOrdineRequest.getStatoOrdine(), acquirente, vehicle, venditore);
        OrdineModel ordineModel1 = OrdineModel.entityToModel(ordineRepository.save(OrdineModel.modelToEntity(ordineModel)));
        return OrdineModel.modelToDto(ordineModel1);
    }
}
