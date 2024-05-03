package com.develhope.spring.repository;

import com.develhope.spring.entity.Noleggio;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface NoleggioRepository extends JpaRepository<Noleggio, Long> {

    List<Noleggio> findByAcquirenteId(Long acquirenteId);

    List<Noleggio> findByVehicle_VehicleId(Long veicoloId);

    List<Noleggio> findByVenditoreId(Long venditoreId);

}
