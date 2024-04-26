package com.develhope.spring.service;

import com.develhope.spring.entity.Acquirente;
import com.develhope.spring.entity.Amministratore;
import com.develhope.spring.repository.AcquirenteRepository;
import com.develhope.spring.repository.AministratoreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AmministratoreService {

    @Autowired
    private AministratoreRepository aministratoreRepository;

    public Amministratore addAmministratore(Amministratore amministratore) {
        return aministratoreRepository.save(amministratore);
    }
}
