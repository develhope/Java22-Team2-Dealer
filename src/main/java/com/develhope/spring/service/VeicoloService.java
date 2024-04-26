package com.develhope.spring.service;

import com.develhope.spring.entity.Veicolo;
import com.develhope.spring.repository.VeicoloRepository;
import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@Service
public class VeicoloService {

    @Autowired
    private VeicoloRepository veicoloRepository;

    public Veicolo addVeicolo(Veicolo veicolo) {
        return veicoloRepository.save(veicolo);
    }

    public Optional<Veicolo> findById(Long id) {
        return veicoloRepository.findById(id);
    }
    public List<Veicolo> searchByMarca(String partialMarca) {
        return veicoloRepository.searchByMarca(partialMarca);
    }

    public void deleteById(Long id) {
        veicoloRepository.deleteById(id);
    }

}
