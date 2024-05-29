package com.develhope.spring.Features.Repository;

import com.develhope.spring.Features.Entity.Noleggio.Noleggio;
import com.develhope.spring.Features.Entity.Noleggio.NoleggioLink;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface NoleggioLinkRepository extends JpaRepository<NoleggioLink, Long> {

    Optional<NoleggioLink> findByAcquirenteUserIdAndNoleggioNoleggioId(Long acquirenteId, Long noleggioLink);

    @Query("SELECT nl.noleggio FROM NoleggioLink nl WHERE nl.acquirente.userId = :acquirenteId")
    List<Noleggio> findNoleggiByAcquirenteId(@Param("acquirenteId") Long acquirenteId);

}
