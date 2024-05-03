package com.develhope.spring.Acquirente;

import com.develhope.spring.Acquirente.Acquirente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AcquirenteRepository extends JpaRepository<Acquirente, Long> {

}
