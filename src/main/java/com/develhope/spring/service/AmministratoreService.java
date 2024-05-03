package com.develhope.spring.service;

import com.develhope.spring.DTOs.Amministratore.AmministratoreDTO;
import com.develhope.spring.DTOs.Amministratore.CreateAmministratoreRequest;
import com.develhope.spring.Models.AmministratoreModel;
import com.develhope.spring.entity.Amministratore;
import com.develhope.spring.repository.AmministratoreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AmministratoreService {

    @Autowired
    private AmministratoreRepository amministratoreRepository;

    //rotta create Amministratore
    public AmministratoreDTO createAmministratore(CreateAmministratoreRequest createAmministratoreRequest) {
        AmministratoreModel amministratoreModel = new AmministratoreModel(createAmministratoreRequest.getNome(), createAmministratoreRequest.getCognome(), createAmministratoreRequest.getEmail(), createAmministratoreRequest.getPassword());
        AmministratoreModel amministratoreModel1 = AmministratoreModel.entityToModel(amministratoreRepository.save(AmministratoreModel.modelToEntity(amministratoreModel)));
        return AmministratoreModel.modelToDto(amministratoreModel1);
    }

    public Optional<Amministratore> deleteById(Long id) {
        Optional<Amministratore> amministratoreOptional = amministratoreRepository.findById(id);
        if (amministratoreOptional.isPresent()) {
            amministratoreRepository.deleteById(id);
        }
        return amministratoreOptional;
    }

    public List<Amministratore> getAllAmministratori() {
        return amministratoreRepository.findAll();
    }

    public Amministratore updateAmministratore(Long id, Amministratore amministratoreMod) {
        Amministratore amministratore = amministratoreRepository.findById(id).orElse(null);
        if (amministratore != null) {
            amministratore.setNome(amministratoreMod.getNome());
            amministratore.setCognome(amministratoreMod.getCognome());
            amministratore.setEmail(amministratoreMod.getEmail());
            amministratore.setPassword(amministratoreMod.getPassword());
            amministratoreRepository.save(amministratore);
            return amministratore;
        }
        return null;
    }

    public Optional<Amministratore> getById(Long id) {
        return amministratoreRepository.findById(id);
    }
}
