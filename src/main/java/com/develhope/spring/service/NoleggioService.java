package com.develhope.spring.service;

import com.develhope.spring.DTOs.Noleggio.CreateNoleggioRequest;
import com.develhope.spring.DTOs.Noleggio.NoleggioDTO;

import com.develhope.spring.Models.NoleggioModel;

import com.develhope.spring.entity.Noleggio;
import com.develhope.spring.repository.NoleggioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.print.attribute.standard.DateTimeAtCreation;
import java.time.OffsetDateTime;
import java.util.List;

@Service
public class NoleggioService {

    @Autowired
    private NoleggioRepository noleggioRepository;

    public NoleggioDTO createNoleggio(CreateNoleggioRequest createNoleggioRequest) {
        OffsetDateTime dateTime = OffsetDateTime.now();
        NoleggioModel noleggioModel = new NoleggioModel(dateTime, createNoleggioRequest.getDataFine(), createNoleggioRequest.getCostoGiornaliero(), createNoleggioRequest.getCostoTotale(), createNoleggioRequest.getFlagPagato(), createNoleggioRequest.getIdVeicoloNoleggiato(), createNoleggioRequest.getAcquirente(), createNoleggioRequest.getVehicle(), createNoleggioRequest.getVenditore());
        NoleggioModel noleggioModel1 = NoleggioModel.entityToModel(noleggioRepository.save(NoleggioModel.modelToEntity(noleggioModel)));
        return NoleggioModel.modelToDto(noleggioModel1);
    }

    public void deleteNoleggio(Long noleggioId) {
        noleggioRepository.deleteById(noleggioId);
    }

    public List<Noleggio> findAll() {
        return noleggioRepository.findAll();
    }

    public List<Noleggio> findByAcquirente(Long acquirenteId) {
        return noleggioRepository.findByAcquirenteId(acquirenteId);
    }

    public List<Noleggio> findByVeicolo(Long veicoloId) {
        return noleggioRepository.findByVehicle_VehicleId(veicoloId);
    }

    public List<Noleggio> findByVenditore(Long venditoreId) {
        return noleggioRepository.findByVenditoreId(venditoreId);
    }

}




