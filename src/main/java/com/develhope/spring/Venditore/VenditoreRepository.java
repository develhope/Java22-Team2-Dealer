package com.develhope.spring.Venditore;

import com.develhope.spring.Venditore.Venditore;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VenditoreRepository extends JpaRepository<Venditore, Long> {

}
