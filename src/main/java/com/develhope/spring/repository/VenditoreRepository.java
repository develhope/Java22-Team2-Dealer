package com.develhope.spring.repository;

import com.develhope.spring.entity.Venditore;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VenditoreRepository extends JpaRepository<Venditore, Long> {
    Venditore findByEmail(String email);
}
