package com.develhope.spring.Features.Service;

import com.develhope.spring.Features.DTOs.Venditore.CreateVenditoreRequest;
import com.develhope.spring.Features.DTOs.Venditore.UpdateVenditoreRequest;
import com.develhope.spring.Features.DTOs.Venditore.VenditoreDTO;
import com.develhope.spring.Features.Models.VenditoreModel;
import com.develhope.spring.Features.Entity.Venditore.Venditore;
import com.develhope.spring.Features.Repository.VenditoreRepository;
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

    // TODO possibile doppione
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

    public Venditore register(CreateVenditoreRequest venditoreRequest) {
        Venditore venditore = new Venditore();
        venditore.setNome(venditoreRequest.getNome());
        venditore.setCognome(venditoreRequest.getCognome());
        venditore.setEmail(venditoreRequest.getEmail());
        venditore.setPassword(venditoreRequest.getPassword());  // dovresti criptare la password
        return venditoreRepository.save(venditore);
    }

    public Venditore updateVenditore(Long id, UpdateVenditoreRequest updateVenditoreRequest) {
        Venditore venditore = venditoreRepository.findById(id).orElse(null);
        if(venditore != null) {
            venditore.setNome(updateVenditoreRequest.getNome());
            venditore.setCognome(updateVenditoreRequest.getCognome());
            venditore.setTelefono(updateVenditoreRequest.getTelefono());
            venditore.setEmail(updateVenditoreRequest.getEmail());
            venditore.setPassword(updateVenditoreRequest.getPassword());
            return venditoreRepository.save(venditore);
        }
        return null;
    }

}
