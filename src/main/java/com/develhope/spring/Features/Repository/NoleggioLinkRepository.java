package com.develhope.spring.Features.Repository;

import com.develhope.spring.Features.Entity.Noleggio.Noleggio;
import com.develhope.spring.Features.Entity.Noleggio.NoleggioLink;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface NoleggioLinkRepository extends JpaRepository<NoleggioLink, Long> {

    Optional<NoleggioLink> findByNoleggio(Noleggio noleggio); // Metodo per trovare il link dato un noleggio
    List<NoleggioLink> findByAcquirenteUserId(Long acquirenteId);
//    List<NoleggioLink> findByNoleggio_Vehicle_VehicleId(Long veicoloId);    // Usa vehicleId invece di Vehicle
    List<NoleggioLink> findByVenditoreUserId(Long venditoreId);
}
