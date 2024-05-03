package com.develhope.spring.Amministratore;

import com.develhope.spring.Amministratore.Amministratore;
import com.develhope.spring.Amministratore.AministratoreRepository;
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
