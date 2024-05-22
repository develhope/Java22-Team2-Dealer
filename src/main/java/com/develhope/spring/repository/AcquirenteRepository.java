package com.develhope.spring.repository;

import com.develhope.spring.entity.Acquirente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface AcquirenteRepository extends JpaRepository<Acquirente, Long> {

    Acquirente findByEmail(String email);

    Acquirente findByTelefono(String telefono);
}
