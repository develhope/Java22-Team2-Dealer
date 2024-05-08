package com.develhope.spring.service;

import com.develhope.spring.DTOs.Acquirente.CreateAcquirenteRequest;
import com.develhope.spring.DTOs.Ordine.CreateOrdineRequest;
import com.develhope.spring.DTOs.Ordine.OrdineDTO;
import com.develhope.spring.Models.OrdineModel;
import com.develhope.spring.entity.Ordine;
import com.develhope.spring.repository.OrdineRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class OrdineService {
    @Autowired
    private OrdineRepository ordineRepository;

    // create acquirente
    public OrdineDTO createOrdine(CreateOrdineRequest ordineRequest) {
        OrdineModel model = OrdineModel.dtoToModel(ordineRequest);
        Ordine savedOrdine = ordineRepository.save(OrdineModel.modelToEntity(model));
        OrdineModel savedModel = OrdineModel.entityToModel(savedOrdine);
        return OrdineModel.modelToDto(savedModel);
    }

    public Optional<Ordine> deleteById(Long id) {
        Optional<Ordine> optionalOrdine = ordineRepository.findById(id);
        if (optionalOrdine.isPresent()) {
            ordineRepository.deleteById(id);
        }
        return optionalOrdine;
    }

    public List<Ordine> getAllOrdini() {
        return ordineRepository.findAll();
    }

    public Ordine updateOrdibe(Long id, Ordine ordineMod) {
        Ordine ordine = ordineRepository.findById(id).orElse(null);
        if(ordine != null) {
            ordine.setAnticipo(ordineMod.getAnticipo());
            ordine.setFlagPagato(ordineMod.getFlagPagato());
            ordine.setStatoOrdine(ordineMod.getStatoOrdine());
            ordine.setIdVeicoloOrdinato(ordineMod.getIdVeicoloOrdinato());
            ordineRepository.save(ordine);
            return ordine;
        }
        return null;
    }

    public Optional<Ordine> getById(Long id) {
        return ordineRepository.findById(id);
    }
}
