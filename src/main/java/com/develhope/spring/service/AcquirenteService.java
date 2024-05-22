package com.develhope.spring.service;

import com.develhope.spring.DTOs.Acquirente.AcquirenteDTO;
import com.develhope.spring.DTOs.Acquirente.CreateAcquirenteRequest;
import com.develhope.spring.DTOs.Acquirente.UpdateAcquirenteRequest;
import com.develhope.spring.Features.User.Entity.Role;
import com.develhope.spring.Features.User.Entity.User;
import com.develhope.spring.Features.User.Repository.UserRepository;
import com.develhope.spring.Models.AcquirenteModel;
import com.develhope.spring.entity.Acquirente;
import com.develhope.spring.entity.OrdineAcquisto;
import com.develhope.spring.repository.AcquirenteRepository;
import com.develhope.spring.repository.OrdineAcquistoRepository;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class AcquirenteService {

    @Autowired
    private AcquirenteRepository acquirenteRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private OrdineAcquistoRepository ordineAcquistoRepository;

    // create acquirente
    public AcquirenteDTO createAcquirente(CreateAcquirenteRequest acquirenteRequest) {
        AcquirenteModel acquirenteModel = new AcquirenteModel(acquirenteRequest.getNome(), acquirenteRequest.getCognome(), acquirenteRequest.getTelefono(), acquirenteRequest.getEmail(), acquirenteRequest.getPassword());
        AcquirenteModel acquirenteModel1 = AcquirenteModel.entityToModel(acquirenteRepository.save(AcquirenteModel.modelToEntity(acquirenteModel)));
        return AcquirenteModel.modelToDto(acquirenteModel1);
    }

    //delete customer
    @SneakyThrows
    public Optional<User> deleteById(Long id, User user) {
        if (user.getRole() == Role.ACQUIRENTE) {
            if (!Objects.equals(user.getUserId(), id)) {
                throw new Exception("Customers can only delete their own information");
            }
        } else if (user.getRole() != Role.AMMINISTRATORE) {
            throw new Exception("Only customers and administrators can delete user information");
        }
        Optional<User> optionalAcquirente = userRepository.findById(id);
        if (optionalAcquirente.isPresent()) {
            userRepository.deleteById(id);
        }
        return optionalAcquirente;
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
