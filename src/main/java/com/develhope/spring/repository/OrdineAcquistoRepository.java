package com.develhope.spring.repository;
import com.develhope.spring.entity.OrdineAcquisto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface OrdineAcquistoRepository extends JpaRepository<OrdineAcquisto, Long> {

    List<OrdineAcquisto> findByAcquirenteId(Long acquirenteId);

    Optional<OrdineAcquisto> findByOrdineAcquistoIdAndAcquirenteId(Long ordineAcquistoId, Long acquirenteId);

}
