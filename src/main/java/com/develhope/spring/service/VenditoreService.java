package com.develhope.spring.service;

import com.develhope.spring.entity.Amministratore;
import com.develhope.spring.entity.Venditore;
import com.develhope.spring.repository.AministratoreRepository;
import com.develhope.spring.repository.VeicoloRepository;
import com.develhope.spring.repository.VenditoreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class VenditoreService {

    @Autowired
    private VenditoreRepository venditoreRepository;

    public Venditore addVenditore(Venditore venditore) {
        return venditoreRepository.save(venditore);
    }
}
