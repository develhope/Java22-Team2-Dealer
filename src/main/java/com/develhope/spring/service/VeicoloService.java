package com.develhope.spring.service;

import com.develhope.spring.entity.Veicolo;
import com.develhope.spring.repository.VeicoloRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@Service
public class VeicoloService {

    @Autowired
    private VeicoloRepository veicoloRepository;

    public void addVeicolo(Veicolo veicolo) {
        veicoloRepository.save(veicolo);
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
