package com.develhope.spring.Features.Service;

import com.develhope.spring.Features.Entity.User.Role;
import com.develhope.spring.Features.Entity.User.User;
import com.develhope.spring.Features.Repository.AcquirenteRepository;
import com.develhope.spring.Features.Repository.UserRepository;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Objects;
import java.util.Optional;

@Service
public class AcquirenteService {

    @Autowired
    private AcquirenteRepository acquirenteRepository;

    @Autowired
    private UserRepository userRepository;

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
            throw new Exception("Buyer and administrators can edit information");
        }
    }
}
