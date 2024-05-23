package com.develhope.spring.Features.Repository;

import com.develhope.spring.Features.Entity.Venditore.Venditore;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VenditoreRepository extends JpaRepository<Venditore, Long> {
    Venditore findByEmail(String email);

    Venditore findByEmailAndPassword(String email, String password);

}
