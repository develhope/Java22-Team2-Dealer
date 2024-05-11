package com.develhope.spring.service;

import com.develhope.spring.DTOs.Ordine.CreateOrdineAcquistoRequest;
import com.develhope.spring.DTOs.Ordine.OrdineAcquistoDTO;
import com.develhope.spring.Models.OrdineAcquistoModel;
import com.develhope.spring.entity.Acquirente;
import com.develhope.spring.entity.OrdineAcquisto;
import com.develhope.spring.entity.Vehicle;
import com.develhope.spring.entity.Venditore;
import com.develhope.spring.entity.enums.TipoOrdine;
import com.develhope.spring.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.OffsetDateTime;
import java.util.Optional;

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

    public OrdineAcquistoDTO createOrdineAcquisto(Long id, CreateOrdineAcquistoRequest createOrdineAcquistoRequest) {
        OffsetDateTime dateTime = createOrdineAcquistoRequest.getDataOrdine() != null ? createOrdineAcquistoRequest.getDataOrdine() : OffsetDateTime.now();
        OffsetDateTime dateTime2 = dateTime.plusDays(21);

        Acquirente acquirente = acquirenteRepository.findById(createOrdineAcquistoRequest.getAcquirenteId()).orElse(null);
        Vehicle vehicle = vehicleRepository.findById(createOrdineAcquistoRequest.getVehicleId()).orElse(null);
        Venditore venditore = venditoreRepository.findById(createOrdineAcquistoRequest.getVenditoreId()).orElse(null);

        if (acquirente == null || vehicle == null || venditore == null ) {
            throw new IllegalArgumentException("Acquirente, Vehicle o Venditore non trovato");
        } else if (vehicle.getTipoOrdine() == TipoOrdine.NON_DISPONIBILE) {
            throw new IllegalArgumentException("Veicolo non acquistabile");
        }

        OrdineAcquistoModel ordineAcquistoModel = new OrdineAcquistoModel(dateTime, dateTime2, createOrdineAcquistoRequest.getAnticipo(), createOrdineAcquistoRequest.getFlagPagato(), createOrdineAcquistoRequest.getStatoOrdineAcquisto(), acquirente, vehicle, venditore);
        OrdineAcquistoModel ordineAcquistoModel1 = OrdineAcquistoModel.entityToModel(ordineAcquistoRepository.save(OrdineAcquistoModel.modelToEntity(ordineAcquistoModel)));
        return OrdineAcquistoModel.modelToDto(ordineAcquistoModel1);
    }

    public OrdineAcquistoDTO createOrdineForAcquirente(Long acquirenteId, CreateOrdineAcquistoRequest createOrdineAcquistoRequest) {
        OffsetDateTime dateTime = createOrdineAcquistoRequest.getDataOrdine() != null ? createOrdineAcquistoRequest.getDataOrdine() : OffsetDateTime.now();
        OffsetDateTime dateTime2 = dateTime.plusDays(21);

        Acquirente acquirente = acquirenteRepository.findById(acquirenteId).orElse(null);
        Vehicle vehicle = vehicleRepository.findById(createOrdineAcquistoRequest.getVehicleId()).orElse(null);
        Venditore venditore = venditoreRepository.findById(createOrdineAcquistoRequest.getVenditoreId()).orElse(null);

        if (acquirente == null || vehicle == null || venditore == null) {
            throw new IllegalArgumentException("Acquirente, Vehicle o Venditore non trovato");
        } else if (vehicle.getTipoOrdine() == TipoOrdine.NON_DISPONIBILE) {
            throw new IllegalArgumentException("Veicolo non acquistabile");
        }

        OrdineAcquistoModel ordineAcquistoModel = new OrdineAcquistoModel(dateTime, dateTime2, createOrdineAcquistoRequest.getAnticipo(), createOrdineAcquistoRequest.getFlagPagato(), createOrdineAcquistoRequest.getStatoOrdineAcquisto(), acquirente, vehicle, venditore);
        OrdineAcquistoModel ordineAcquistoModel1 = OrdineAcquistoModel.entityToModel(ordineAcquistoRepository.save(OrdineAcquistoModel.modelToEntity(ordineAcquistoModel)));
        return OrdineAcquistoModel.modelToDto(ordineAcquistoModel1);
    }

    public Optional<OrdineAcquisto> deleteOrdineForAcquirente(Long acquirenteId, Long ordineId) {
        Optional<OrdineAcquisto> optionalOrdine = ordineAcquistoRepository.findById(ordineId);
        if (optionalOrdine.isPresent()) {
            OrdineAcquisto ordineAcquisto = optionalOrdine.get();
            if (ordineAcquisto.getAcquirente().getId().equals(acquirenteId)) {
                ordineAcquistoRepository.deleteById(ordineId);
                return optionalOrdine;
            } else {
                throw new IllegalArgumentException("L'ordine non appartiene all'acquirente specificato");
            }
        } else {
            return Optional.empty();
        }
    }

    public Optional<OrdineAcquisto> updateOrdineForAcquirente(Long acquirenteId, Long ordineId, CreateOrdineAcquistoRequest createOrdineAcquistoRequest) {
        Optional<OrdineAcquisto> optionalOrdine = ordineAcquistoRepository.findById(ordineId);
        if (optionalOrdine.isPresent()) {
            OrdineAcquisto ordineAcquisto = optionalOrdine.get();
            if (ordineAcquisto.getAcquirente().getId().equals(acquirenteId)) {

                ordineAcquisto.setDataOrdineAcquisto(createOrdineAcquistoRequest.getDataOrdine() != null ? createOrdineAcquistoRequest.getDataOrdine() : ordineAcquisto.getDataOrdineAcquisto());
                ordineAcquisto.setDataConsegna(createOrdineAcquistoRequest.getDataConsegna() != null ? createOrdineAcquistoRequest.getDataConsegna() : ordineAcquisto.getDataConsegna());
                ordineAcquisto.setAnticipo(createOrdineAcquistoRequest.getAnticipo() != null ? createOrdineAcquistoRequest.getAnticipo() : ordineAcquisto.getAnticipo());
                ordineAcquisto.setFlagPagato(createOrdineAcquistoRequest.getFlagPagato() != null ? createOrdineAcquistoRequest.getFlagPagato() : ordineAcquisto.getFlagPagato());
                ordineAcquisto.setStatoOrdineAcquisto(createOrdineAcquistoRequest.getStatoOrdineAcquisto() != null ? createOrdineAcquistoRequest.getStatoOrdineAcquisto() : ordineAcquisto.getStatoOrdineAcquisto());

                ordineAcquistoRepository.save(ordineAcquisto);

                return optionalOrdine;
            } else {
                throw new IllegalArgumentException("L'ordine non appartiene all'acquirente specificato");
            }
        } else {
            return Optional.empty();
        }
    }

    public OrdineAcquistoDTO createAcquistoForAcquirente(Long acquirenteId, CreateOrdineAcquistoRequest createOrdineAcquistoRequest) {
        OffsetDateTime dateTime = createOrdineAcquistoRequest.getDataOrdine() != null ? createOrdineAcquistoRequest.getDataOrdine() : OffsetDateTime.now();
        OffsetDateTime dateTime2 = dateTime.plusDays(21);

        Acquirente acquirente = acquirenteRepository.findById(acquirenteId).orElse(null);
        Vehicle vehicle = vehicleRepository.findById(createOrdineAcquistoRequest.getVehicleId()).orElse(null);
        Venditore venditore = venditoreRepository.findById(createOrdineAcquistoRequest.getVenditoreId()).orElse(null);

        if (acquirente == null || vehicle == null || venditore == null) {
            throw new IllegalArgumentException("Acquirente, Vehicle o Venditore non trovato");
        } else if (vehicle.getTipoOrdine() == TipoOrdine.NON_DISPONIBILE) {
            throw new IllegalArgumentException("Veicolo non acquistabile");
        }

        OrdineAcquistoModel ordineAcquistoModel = new OrdineAcquistoModel(dateTime, dateTime2, createOrdineAcquistoRequest.getAnticipo(), createOrdineAcquistoRequest.getFlagPagato(), createOrdineAcquistoRequest.getStatoOrdineAcquisto(), acquirente, vehicle, venditore);
        OrdineAcquistoModel ordineAcquistoModel1 = OrdineAcquistoModel.entityToModel(ordineAcquistoRepository.save(OrdineAcquistoModel.modelToEntity(ordineAcquistoModel)));
        return OrdineAcquistoModel.modelToDto(ordineAcquistoModel1);
    }

    public Optional<OrdineAcquisto> deleteAcquistoForAcquirente(Long acquirenteId, Long ordineId) {
        Optional<OrdineAcquisto> optionalAcquisto = ordineAcquistoRepository.findById(ordineId);
        if (optionalAcquisto.isPresent()) {
            OrdineAcquisto ordineAcquisto = optionalAcquisto.get();
            if (ordineAcquisto.getAcquirente().getId().equals(acquirenteId)) {
                ordineAcquistoRepository.deleteById(ordineId);
                return optionalAcquisto;
            } else {
                throw new IllegalArgumentException("L'acquisto non appartiene all'acquirente specificato");
            }
        } else {
            return Optional.empty();
        }
    }

    public Optional<OrdineAcquisto> updateAcquistoForAcquirente(Long acquirenteId, Long ordineId, CreateOrdineAcquistoRequest createOrdineAcquistoRequest) {
        Optional<OrdineAcquisto> optionalAcquisto = ordineAcquistoRepository.findById(ordineId);
        if (optionalAcquisto.isPresent()) {
            OrdineAcquisto ordineAcquisto = optionalAcquisto.get();
            if (ordineAcquisto.getAcquirente().getId().equals(acquirenteId)) {

                ordineAcquisto.setDataOrdineAcquisto(createOrdineAcquistoRequest.getDataOrdine() != null ? createOrdineAcquistoRequest.getDataOrdine() : ordineAcquisto.getDataOrdineAcquisto());
                ordineAcquisto.setDataConsegna(createOrdineAcquistoRequest.getDataConsegna() != null ? createOrdineAcquistoRequest.getDataConsegna() : ordineAcquisto.getDataConsegna());
                ordineAcquisto.setAnticipo(createOrdineAcquistoRequest.getAnticipo() != null ? createOrdineAcquistoRequest.getAnticipo() : ordineAcquisto.getAnticipo());
                ordineAcquisto.setFlagPagato(createOrdineAcquistoRequest.getFlagPagato() != null ? createOrdineAcquistoRequest.getFlagPagato() : ordineAcquisto.getFlagPagato());
                ordineAcquisto.setStatoOrdineAcquisto(createOrdineAcquistoRequest.getStatoOrdineAcquisto() != null ? createOrdineAcquistoRequest.getStatoOrdineAcquisto() : ordineAcquisto.getStatoOrdineAcquisto());

                ordineAcquistoRepository.save(ordineAcquisto);

                return optionalAcquisto;
            } else {
                throw new IllegalArgumentException("L'acquisto non appartiene all'acquirente specificato");
            }
        } else {
            return Optional.empty();
        }
    }
}
