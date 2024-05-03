package com.develhope.spring.Noleggio;

import com.develhope.spring.Noleggio.Noleggio;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface NoleggioRepository extends JpaRepository<Noleggio, Long> {

    @Query("SELECT n FROM Noleggio n WHERE n.acquirente.id = :acquirenteId")
    List<Noleggio> findByAcquirenteId(Long acquirenteId);

    @Query("SELECT n FROM Noleggio n WHERE n.veicolo.id = :veicoloId")
    List<Noleggio> findByVeicoloId(Long veicoloId);

    @Query("SELECT n FROM Noleggio n WHERE n.venditore.id = :venditoreId")
    List<Noleggio> findByVenditoreId(Long venditoreId);

}
