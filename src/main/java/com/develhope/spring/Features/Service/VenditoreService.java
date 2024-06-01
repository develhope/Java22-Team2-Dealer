package com.develhope.spring.Features.Service;

import com.develhope.spring.Features.Entity.User.Role;
import com.develhope.spring.Features.Entity.User.User;
import com.develhope.spring.Features.Repository.UserRepository;
import com.develhope.spring.Features.Repository.VenditoreRepository;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Objects;
import java.util.Optional;

@Service
public class VenditoreService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private VenditoreRepository venditoreRepository;

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

    @SneakyThrows
    public User updateVenditore(Long id, User userMod, User callingUser) {
        User venditore = userRepository.findById(id).orElse(null);
        if (venditore == null) {
            return null;
        }
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
