package com.develhope.spring.Ordine;

import com.develhope.spring.Acquirente.Acquirente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrdineAcquistoRepository extends JpaRepository<OrdineAcquisto, Long> {
    List<OrdineAcquisto> findByAcquirente(Acquirente acquirente);
}
