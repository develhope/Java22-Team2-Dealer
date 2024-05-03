package com.develhope.spring.service;

import com.develhope.spring.DTOs.Venditore.CreateVenditoreRequest;
import com.develhope.spring.DTOs.Venditore.VenditoreDTO;
import com.develhope.spring.Models.VenditoreModel;
import com.develhope.spring.entity.Acquirente;
import com.develhope.spring.entity.Venditore;
import com.develhope.spring.repository.VenditoreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class VenditoreService {

    @Autowired
    private VenditoreRepository venditoreRepository;

    // Create
    public VenditoreDTO createVenditore(CreateVenditoreRequest createVenditoreRequest) {
        VenditoreModel venditoreModel = new VenditoreModel(createVenditoreRequest.getNome(), createVenditoreRequest.getCognome(), createVenditoreRequest.getTelefono(), createVenditoreRequest.getEmail(), createVenditoreRequest.getPassword());
        VenditoreModel venditoreModel1 =  VenditoreModel.entityToModel(venditoreRepository.save(VenditoreModel.modelToEntity(venditoreModel)));
        return VenditoreModel.modelToDto(venditoreModel1);
    }

    // Read
    public List<Venditore> getAllVenditori() {
        return venditoreRepository.findAll();
    }

    public Optional<Venditore> getVenditoreById(Long id) {
        return venditoreRepository.findById(id);
    }

    // Update
    public Venditore updateVenditore(Long id, Venditore venditoreMod) {
        Venditore venditore = venditoreRepository.findById(id).orElse(null);
        if(venditore != null) {
            venditore.setNome(venditoreMod.getNome());
            venditore.setCognome(venditoreMod.getCognome());
            venditore.setTelefono(venditoreMod.getTelefono());
            venditore.setEmail(venditoreMod.getEmail());
            venditore.setPassword(venditoreMod.getPassword());
            venditoreRepository.save(venditore);
            return venditore;
        }
        return null;
    }

    //rotta delete by id venditore
    public Optional<Venditore> deleteById(Long id) {
        Optional<Venditore> optionalVenditore = venditoreRepository.findById(id);
        if (optionalVenditore.isPresent()) {
            venditoreRepository.deleteById(id);
        }
        return optionalVenditore;
    }

}
