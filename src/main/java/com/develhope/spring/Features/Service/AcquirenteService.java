package com.develhope.spring.Features.Service;

import com.develhope.spring.Features.Autentication.Entity.RefreshToken;
import com.develhope.spring.Features.Autentication.Repository.RefreshTokenRepository;
import com.develhope.spring.Features.DTOs.Acquirente.AcquirenteDTO;
import com.develhope.spring.Features.DTOs.Acquirente.CreateAcquirenteRequest;
import com.develhope.spring.Features.DTOs.Acquirente.UpdateAcquirenteRequest;
import com.develhope.spring.Features.Entity.User.Role;
import com.develhope.spring.Features.Entity.User.User;
import com.develhope.spring.Features.Repository.UserRepository;
import com.develhope.spring.Features.Models.AcquirenteModel;
import com.develhope.spring.Features.Entity.Acquirente.Acquirente;
import com.develhope.spring.Features.Repository.AcquirenteRepository;
import com.develhope.spring.Features.Repository.OrdineAcquistoRepository;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.UUID;

@Service
public class AcquirenteService {

    @Autowired
    private AcquirenteRepository acquirenteRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private OrdineAcquistoRepository ordineAcquistoRepository;

    //delete customer
    public Optional<User> deleteById(Long id, User user) {
        if (user == null || id == null) {
            throw new IllegalArgumentException("User and ID must not be null");
        }
        Optional<User> optionalUser = userRepository.findById(id);
        if (optionalUser.isEmpty()) {
            throw new IllegalArgumentException("User with id " + id + " not found");
        }
        User userToDelete = optionalUser.get();
        if (user.getRole() == Role.ACQUIRENTE && Objects.equals(user.getUserId(), id)) {
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
        user.setNome("deleted_" + user.getUserId());
        user.setCognome("deleted_" + user.getUserId());
        user.setEmail("deleted_" + user.getUserId());
        user.setTelefono("deleted_" + user.getUserId());
        user.setPassword("deleted_" + user.getUserId());
        user.setRole(Role.NON_DEFINITO);
    }

    @SneakyThrows
    public User updateAcquirente(Long id, User userMod, User callingUser) {
        User acquirente = userRepository.findById(id).orElse(null);
        if (acquirente == null) {
            return null;
        }
        // Check if the calling user is an acquirente and is updating their own information
        if ((callingUser.getRole().equals(Role.ACQUIRENTE) && acquirente.getRole().equals(Role.ACQUIRENTE) && Objects.equals(acquirente.getUserId(), callingUser.getUserId()))
                || callingUser.getRole().equals(Role.AMMINISTRATORE)) {
            acquirente.setNome(userMod.getNome());
            acquirente.setCognome(userMod.getCognome());
            acquirente.setTelefono(userMod.getTelefono());
            acquirente.setEmail(userMod.getEmail());
            acquirente.setPassword(userMod.getPassword());
            userRepository.save(acquirente);
            return acquirente;
        } else {
            throw new Exception("I clienti e gli amministratori possono modificare le informazioni");
        }
    }

    public List<Acquirente> getAllAcquirenti() {
        return acquirenteRepository.findAll();
    }


    public Optional<Acquirente> getById(Long id) {
        return acquirenteRepository.findById(id);
    }

    public Acquirente register(CreateAcquirenteRequest acquirenteRequest) {
        Acquirente acquirente = new Acquirente();
        acquirente.setNome(acquirenteRequest.getNome());
        acquirente.setCognome(acquirenteRequest.getCognome());
        acquirente.setEmail(acquirenteRequest.getEmail());
        acquirente.setPassword(acquirenteRequest.getPassword());  // dovresti criptare la password
        return acquirenteRepository.save(acquirente);
    }

    public Acquirente updateAcquirente(Long id, UpdateAcquirenteRequest updateAcquirenteRequest) {
        Acquirente acquirente = acquirenteRepository.findById(id).orElse(null);
        if (acquirente != null) {
            acquirente.setNome(updateAcquirenteRequest.getNome());
            acquirente.setCognome(updateAcquirenteRequest.getCognome());
            acquirente.setTelefono(updateAcquirenteRequest.getTelefono());
            acquirente.setEmail(updateAcquirenteRequest.getEmail());
            acquirente.setPassword(updateAcquirenteRequest.getPassword());
            return acquirenteRepository.save(acquirente);
        }
        return null;
    }

}
