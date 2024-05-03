package com.develhope.spring.Venditore;

import com.develhope.spring.Venditore.Venditore;
import com.develhope.spring.Venditore.VenditoreRepository;
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
