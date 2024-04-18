package com.develhope.spring.service;

import com.develhope.spring.entity.Veicolo;
import com.develhope.spring.repository.VeicoloRepository;
import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@AllArgsConstructor
@Service
public class VeicoloService {
    @Autowired
    private VeicoloRepository veicoloRepository;

    public Veicolo addVeicolo(Veicolo veicolo) {
        return veicoloRepository.save(veicolo);
    }

    public Optional<Veicolo> findById(Veicolo veicolo) {
        Optional<Veicolo> optionalVeicolo = veicoloRepository.findById(veicolo.getId());
        return optionalVeicolo;

    }
}
