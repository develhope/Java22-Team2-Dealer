package com.develhope.spring.Ordine;

import com.develhope.spring.Acquirente.Acquirente;
import com.develhope.spring.Acquirente.AcquirenteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
public class OrdineAcquistoService {

    @Autowired
    private OrdineAcquistoRepository ordineAcquistoRepository;

    @Autowired
    private AcquirenteRepository acquirenteRepository;

    public OrdineAcquisto createOrdine(OrdineAcquisto ordineAcquisto) {
        return ordineAcquistoRepository.save(ordineAcquisto);
    }

    public void deleteOrdine(Long ordineId) {
        ordineAcquistoRepository.deleteById(ordineId);
    }

    public List<OrdineAcquisto> findOrdiniByAcquirenteId(Long acquirenteId) {
        Acquirente acquirente = acquirenteRepository.findById(acquirenteId).orElse(null);
        if (acquirente != null) {
            return ordineAcquistoRepository.findByAcquirente(acquirente);
        } else {
            return Collections.emptyList();
        }
    }
}
