package com.develhope.spring.Features.Repository;
import com.develhope.spring.Features.Entity.OrdineAcquisto.OrdineAcquisto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrdineAcquistoRepository extends JpaRepository<OrdineAcquisto, Long> {

//    List<OrdineAcquisto> findByAcquirenteId(Long acquirenteId);

//    Optional<OrdineAcquisto> findByOrdineAcquistoIdAndAcquirenteId(Long ordineAcquistoId, Long acquirenteId);

}
