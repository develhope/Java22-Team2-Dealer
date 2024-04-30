package com.develhope.spring.service;

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

    public void addAcquirente(Acquirente acquirente) {
        acquirenteRepository.save(acquirente);
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

}
