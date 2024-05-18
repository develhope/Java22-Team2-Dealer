package com.develhope.spring.Features.Autentication.Repository;

import com.develhope.spring.Features.Autentication.Entity.RefreshToken;
import com.develhope.spring.Features.User.Entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface RefreshTokenRepository extends JpaRepository<RefreshToken, Integer> {

    Optional<RefreshToken> findByToken(String token);

    List<RefreshToken> findByUserInfo(User user);

}
