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
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.stereotype.Service;

import java.math.BigInteger;
import java.time.OffsetDateTime;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class VenditoreService {

    @Autowired
    private UserRepository userRepository;

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
            throw new IllegalArgumentException("Only customer can delete their own information or admin can delete any user's information");
        }
    }
    private void clearUserData(User user) {
        user.setNome("");
        user.setCognome("");
        user.setEmail("deleted_" + user.getUserId());
        user.setTelefono("deleted_" + user.getUserId());
        user.setPassword("");
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

    @SneakyThrows
    public User updateVenditore(Long id, User userMod, User callingUser) {
        User venditore = userRepository.findById(id).orElse(null);
        if (venditore == null) {
            return null;
        }
        // Check if the calling user is an venditore and is updating their own information
        if ((callingUser.getRole().equals(Role.VENDITORE) && venditore.getRole().equals(Role.VENDITORE) && Objects.equals(venditore.getUserId(), callingUser.getUserId()))
                || callingUser.getRole().equals(Role.AMMINISTRATORE)) {
            venditore.setNome(userMod.getNome());
            venditore.setCognome(userMod.getCognome());
            venditore.setTelefono(userMod.getTelefono());
            venditore.setEmail(userMod.getEmail());
            venditore.setPassword(userMod.getPassword());
            userRepository.save(venditore);
            return venditore;
        } else {
            throw new Exception("I venditori e gli amministratori possono modificare le informazioni");
        }
    }
}
