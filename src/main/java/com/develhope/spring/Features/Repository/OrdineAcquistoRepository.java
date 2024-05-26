package com.develhope.spring.Features.Repository;
import com.develhope.spring.Features.Entity.OrdineAcquisto.OrdineAcquisto;
import com.develhope.spring.Features.Entity.OrdineAcquisto.StatoOrdineAcquisto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface OrdineAcquistoRepository extends JpaRepository<OrdineAcquisto, Long> {

    List<OrdineAcquisto> findByStatoOrdineAcquisto(StatoOrdineAcquisto stato);

}
