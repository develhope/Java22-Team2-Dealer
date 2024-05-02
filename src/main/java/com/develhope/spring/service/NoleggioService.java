package com.develhope.spring.service;

import com.develhope.spring.entity.Noleggio;
import com.develhope.spring.repository.AcquirenteRepository;
import com.develhope.spring.repository.NoleggioRepository;
import com.develhope.spring.repository.VeicoloRepository;
import com.develhope.spring.repository.VenditoreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NoleggioService {

    @Autowired
    private NoleggioRepository noleggioRepository;

    public Noleggio addNoleggio(Noleggio noleggio) {
        return noleggioRepository.save(noleggio);
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
        return noleggioRepository.findByVeicoloId(veicoloId);
    }

    public List<Noleggio> findByVenditore(Long venditoreId) {
        return noleggioRepository.findByVenditoreId(venditoreId);
    }

}

