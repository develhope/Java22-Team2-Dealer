package com.develhope.spring.Features.User.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.develhope.spring.Features.User.Entity.User;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByEmail(String email);

}