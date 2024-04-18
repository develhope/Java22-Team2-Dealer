package com.develhope.spring.repository;

import com.develhope.spring.entity.Veicolo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VeicoloRepository extends JpaRepository<Veicolo, Long>{
}
