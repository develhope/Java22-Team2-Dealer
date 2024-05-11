package com.develhope.spring.service;
import com.develhope.spring.DTOs.Acquirente.AcquirenteDTO;
import com.develhope.spring.DTOs.Acquirente.CreateAcquirenteRequest;
import com.develhope.spring.Models.AcquirenteModel;
import com.develhope.spring.entity.Acquirente;
import com.develhope.spring.repository.AcquirenteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AcquirenteService {

    @Autowired
    private AcquirenteRepository acquirenteRepository;

    // create acquirente
    public AcquirenteDTO createAcquirente(CreateAcquirenteRequest acquirenteRequest) {
        AcquirenteModel acquirenteModel = new AcquirenteModel(acquirenteRequest.getNome(), acquirenteRequest.getCognome(), acquirenteRequest.getTelefono(), acquirenteRequest.getEmail(), acquirenteRequest.getPassword());
        AcquirenteModel acquirenteModel1 =  AcquirenteModel.entityToModel(acquirenteRepository.save(AcquirenteModel.modelToEntity(acquirenteModel)));
        return AcquirenteModel.modelToDto(acquirenteModel1);
    }

    public Optional<Acquirente> deleteById(Long id) {
        Optional<Acquirente> optionalAcquirente = acquirenteRepository.findById(id);
        if (optionalAcquirente.isPresent()) {
            acquirenteRepository.deleteById(id);
        }
        return optionalAcquirente;
    }

    public List<Acquirente> getAllAcquirenti() {
         return acquirenteRepository.findAll();
    }

    public Acquirente updateAcquirente(Long id, Acquirente acquirenteMod) {
        Acquirente acquirente = acquirenteRepository.findById(id).orElse(null);
        if(acquirente != null) {
            acquirente.setNome(acquirenteMod.getNome());
            acquirente.setCognome(acquirenteMod.getCognome());
            acquirente.setTelefono(acquirenteMod.getTelefono());
            acquirente.setEmail(acquirenteMod.getEmail());
            acquirente.setPassword(acquirenteMod.getPassword());
            acquirenteRepository.save(acquirente);
            return acquirente;
        }
        return null;
    }

    public Optional<Acquirente> getById(Long id) {
        return acquirenteRepository.findById(id);
    }

    public Acquirente register(CreateAcquirenteRequest acquirenteRequest) {
        Acquirente acquirente = new Acquirente();
        acquirente.setNome(acquirenteRequest.getNome());
        acquirente.setCognome(acquirenteRequest.getCognome());
        acquirente.setEmail(acquirenteRequest.getEmail());
        acquirente.setPassword(acquirenteRequest.getPassword());  // dovresti criptare la password
        return acquirenteRepository.save(acquirente);
    }

    public Acquirente updateAcquirente(Long id, CreateAcquirenteRequest updateAcquirenteRequest) {
        Acquirente acquirente = acquirenteRepository.findById(id).orElse(null);
        if(acquirente != null) {
            acquirente.setNome(updateAcquirenteRequest.getNome());
            acquirente.setCognome(updateAcquirenteRequest.getCognome());
            acquirente.setTelefono(updateAcquirenteRequest.getTelefono());
            acquirente.setEmail(updateAcquirenteRequest.getEmail());
            acquirente.setPassword(updateAcquirenteRequest.getPassword());
            return acquirenteRepository.save(acquirente);
        }
        return null;
    }
}
