package com.develhope.spring.Features.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.develhope.spring.Features.Entity.User.User;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByEmail(String email);

    boolean existsByEmail(String email);

    boolean existsByTelefono(String telefono);

}
