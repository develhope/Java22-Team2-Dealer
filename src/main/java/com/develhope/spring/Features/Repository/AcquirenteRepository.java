package com.develhope.spring.Features.Repository;

import com.develhope.spring.Features.Entity.Acquirente.Acquirente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AcquirenteRepository extends JpaRepository<Acquirente, Long> {

}
