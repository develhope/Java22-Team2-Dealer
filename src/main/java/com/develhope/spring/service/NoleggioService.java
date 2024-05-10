package com.develhope.spring.service;

import com.develhope.spring.DTOs.Noleggio.CreateNoleggioRequest;
import com.develhope.spring.DTOs.Noleggio.NoleggioDTO;

import com.develhope.spring.Models.NoleggioModel;

import com.develhope.spring.entity.Acquirente;
import com.develhope.spring.entity.Noleggio;
import com.develhope.spring.entity.Vehicle;
import com.develhope.spring.entity.Venditore;
import com.develhope.spring.entity.enums.TipoOrdine;
import com.develhope.spring.repository.AcquirenteRepository;
import com.develhope.spring.repository.NoleggioRepository;
import com.develhope.spring.repository.VehicleRepository;
import com.develhope.spring.repository.VenditoreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Optional;

@Service
public class NoleggioService {

    @Autowired
    private NoleggioRepository noleggioRepository;

    @Autowired
    private AcquirenteRepository acquirenteRepository;

    @Autowired
    private VehicleRepository vehicleRepository;

    @Autowired
    private VenditoreRepository venditoreRepository;

    // create noleggio
    public NoleggioDTO createNoleggio(CreateNoleggioRequest createNoleggioRequest) {
        OffsetDateTime dateTime = createNoleggioRequest.getDataInizio() != null ? createNoleggioRequest.getDataInizio() : OffsetDateTime.now();
        OffsetDateTime dateTime2 = createNoleggioRequest.getDataFine();

        Acquirente acquirente = acquirenteRepository.findById(createNoleggioRequest.getAcquirenteId()).orElse(null);
        Vehicle vehicle = vehicleRepository.findById(createNoleggioRequest.getVehicleId()).orElse(null);
        Venditore venditore = venditoreRepository.findById(createNoleggioRequest.getVenditoreId()).orElse(null);

        if (acquirente == null || vehicle == null || venditore == null ) {
            throw new IllegalArgumentException("Acquirente, Vehicle o Venditore non trovato");
        } else if (vehicle.getTipoOrdine() == TipoOrdine.NON_DISPONIBILE) {
            throw new IllegalArgumentException("Veicolo non ordinabile");
        }

        long numeroGiorni = ChronoUnit.DAYS.between(dateTime, dateTime2);

        BigDecimal costoTotale = createNoleggioRequest.getCostoGiornaliero().multiply(BigDecimal.valueOf(numeroGiorni));

        NoleggioModel noleggioModel = new NoleggioModel(dateTime, dateTime2, createNoleggioRequest.getCostoGiornaliero(), costoTotale, createNoleggioRequest.getFlagPagato(), acquirente, vehicle, venditore);
        NoleggioModel noleggioModel1 = NoleggioModel.entityToModel(noleggioRepository.save(NoleggioModel.modelToEntity(noleggioModel)));
        return NoleggioModel.modelToDto(noleggioModel1);
    }

    // delete noleggio
    public void deleteNoleggio(Long noleggioId) {
        noleggioRepository.deleteById(noleggioId);
    }

    // find all
    public List<Noleggio> findAll() {
        return noleggioRepository.findAll();
    }

    // find by acquirente
    public List<Noleggio> findByAcquirente(Long acquirenteId) {
        return noleggioRepository.findByAcquirenteId(acquirenteId);
    }

    //find by veicolo
    public List<Noleggio> findByVeicolo(Long veicoloId) {
        return noleggioRepository.findByVehicle_VehicleId(veicoloId);
    }

    // find by venditore
    public List<Noleggio> findByVenditore(Long venditoreId) {
        return noleggioRepository.findByVenditoreId(venditoreId);
    }

    // find by noleggio id
    public Optional<Noleggio> findById(Long noleggioId) {
        return noleggioRepository.findById(noleggioId);
    }
}




