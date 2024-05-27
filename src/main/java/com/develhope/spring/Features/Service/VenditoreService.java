package com.develhope.spring.Features.Service;

import com.develhope.spring.Features.DTOs.Venditore.CreateVenditoreRequest;
import com.develhope.spring.Features.DTOs.Venditore.UpdateVenditoreRequest;
import com.develhope.spring.Features.DTOs.Venditore.VenditoreDTO;
import com.develhope.spring.Features.Entity.User.Role;
import com.develhope.spring.Features.Entity.User.User;
import com.develhope.spring.Features.Models.VenditoreModel;
import com.develhope.spring.Features.Entity.Venditore.Venditore;
import com.develhope.spring.Features.Repository.UserRepository;
import com.develhope.spring.Features.Repository.VenditoreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class VenditoreService {

    @Autowired
    private VenditoreRepository venditoreRepository;

    @Autowired
    private UserRepository userRepository;

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
    public Optional<User> deleteById(Long id, User user) {
        if (user == null || id == null) {
            throw new IllegalArgumentException("User and ID must not be null");
        }
        Optional<User> optionalUser = userRepository.findById(id);
        if (optionalUser.isEmpty()) {
            throw new IllegalArgumentException("User with id " + id + " not found");
        }
        User userToDelete = optionalUser.get();
        if (user.getRole() == Role.VENDITORE && Objects.equals(user.getUserId(), id)) {
            clearUserData(userToDelete);
            userRepository.save(userToDelete);
            return optionalUser;
        } else if (user.getRole() == Role.AMMINISTRATORE) {
            clearUserData(userToDelete);
            userRepository.save(userToDelete);
            return optionalUser;
        } else {
            throw new IllegalArgumentException("Only seller can delete their own information or admin can delete any user's information");
        }
    }

    private void clearUserData(User user) {
        user.setNome("deleted_" + user.getUserId());
        user.setCognome("deleted_" + user.getUserId());
        user.setEmail("deleted_" + user.getUserId());
        user.setTelefono("deleted_" + user.getUserId());
        user.setPassword("deleted_" + user.getUserId());
        user.setRole(Role.NON_DEFINITO);
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
