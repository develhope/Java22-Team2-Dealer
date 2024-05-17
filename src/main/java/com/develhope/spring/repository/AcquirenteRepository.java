package com.develhope.spring.repository;

import com.develhope.spring.entity.Acquirente;
import com.develhope.spring.entity.OrdineAcquisto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AcquirenteRepository extends JpaRepository<Acquirente, Long> {
    Acquirente findByEmail(String email);

    Acquirente findByEmailAndPassword(String email, String password);

}
