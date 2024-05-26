package com.develhope.spring.Features.Repository;

import com.develhope.spring.Features.Entity.OrdineAcquisto.OrdineAcquisto;
import com.develhope.spring.Features.Entity.OrdineAcquisto.OrdineAcquistoLink;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface OrdineAcquistoLinkRepository extends JpaRepository<OrdineAcquistoLink, Long> {

    Optional<OrdineAcquistoLink> findByAcquirenteUserIdAndOrdineAcquistoOrdineAcquistoId(Long acquirenteId, Long ordineAcquistoId);

    @Query("SELECT oal.ordineAcquisto FROM OrdineAcquistoLink oal WHERE oal.acquirente.userId = :acquirenteId")
    List<OrdineAcquisto> findOrdiniAcquistiByAcquirenteId(@Param("acquirenteId") Long acquirenteId);

}
