package com.develhope.spring.Veicolo;

import com.develhope.spring.Veicolo.Veicolo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface VeicoloRepository extends JpaRepository<Veicolo, Long> {
    @Query("SELECT v FROM Veicolo v WHERE v.marca LIKE %:partialMarca%")
    List<Veicolo> searchByMarca(@Param("partialMarca") String partialMarca);

}
