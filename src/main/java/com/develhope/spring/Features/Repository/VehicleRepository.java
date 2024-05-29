package com.develhope.spring.Features.Repository;

import com.develhope.spring.Features.Entity.Vehicle.Vehicle;
import com.develhope.spring.Features.Entity.Vehicle.Allestimento;
import com.develhope.spring.Features.Entity.OrdineAcquisto.TipoOrdineAcquisto;
import com.develhope.spring.Features.Entity.Vehicle.TipoVeicolo;
import com.develhope.spring.Features.Entity.Vehicle.VehicleCondition;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.List;

@Repository
public interface VehicleRepository extends JpaRepository<Vehicle, Long> {
    @Query("SELECT v FROM Vehicle v WHERE LOWER(v.marca) LIKE LOWER(CONCAT('%', :partialMarca, '%'))")
    List<Vehicle> searchByMarca(@Param("partialMarca") String partialMarca);

    @Query("SELECT v FROM Vehicle v WHERE LOWER(v.modello) LIKE LOWER(CONCAT('%', :partialModello, '%'))")
    List<Vehicle> searchByModello(@Param("partialModello") String partialModello);

    @Query("SELECT v FROM Vehicle v WHERE v.tipoVeicolo = :tipoVeicolo")
    List<Vehicle> searchByTipoVeicolo(@Param("tipoVeicolo") TipoVeicolo tipoVeicolo);

    @Query("SELECT v FROM Vehicle v WHERE v.cilindrata = :cilindrata")
    List<Vehicle> searchByCilindrata(@Param("cilindrata") int cilindrata);

    @Query("SELECT v FROM Vehicle v WHERE LOWER(v.colore) LIKE LOWER(CONCAT('%', :partialColore, '%'))")
    List<Vehicle> searchByColore(@Param("partialColore") String partialColore);

    @Query("SELECT v FROM Vehicle v WHERE v.potenza = :potenza")
    List<Vehicle> searchByPotenza(@Param("potenza") int potenza);

    @Query("SELECT v FROM Vehicle v WHERE LOWER(v.tipoDiCambio) LIKE LOWER(CONCAT('%', :tipoDiCambio, '%'))")
    List<Vehicle> searchByTipoDiCambio(@Param("tipoDiCambio") String tipoDiCambio);

    @Query("SELECT v FROM Vehicle v WHERE v.annoImmatricolazione = :annoImmatricolazione")
    List<Vehicle> searchByAnnoImmatricolazione(@Param("annoImmatricolazione") int annoImmatricolazione);

    @Query("SELECT v FROM Vehicle v WHERE LOWER(v.alimentazione) LIKE LOWER(CONCAT('%', :alimentazione, '%'))")
    List<Vehicle> searchByAlimentazione(@Param("alimentazione") String alimentazione);

    @Query("SELECT v FROM Vehicle v WHERE v.prezzo = :prezzo")
    List<Vehicle> searchByPrezzo(@Param("prezzo") BigDecimal prezzo);

    @Query("SELECT v FROM Vehicle v WHERE v.allestimento = :allestimento")
    List<Vehicle> searchByAllestimento(@Param("allestimento") Allestimento allestimento);

    @Query("SELECT v FROM Vehicle v WHERE LOWER(v.accessori) LIKE LOWER(CONCAT('%', :accessori, '%'))")
    List<Vehicle> searchByAccessori(@Param("accessori") String accessori);

    @Query("SELECT v FROM Vehicle v WHERE v.vehicleCondition = :vehicleCondition")
    List<Vehicle> searchByVehicleCondition(@Param("vehicleCondition") VehicleCondition vehicleCondition);

    @Query("SELECT v FROM Vehicle v WHERE v.tipoOrdineAcquisto = :tipoOrdineAcquisto")
    List<Vehicle> searchByTipoOrdineAcquisto(@Param("tipoOrdineAcquisto") TipoOrdineAcquisto tipoOrdineAcquisto);

    @Query("SELECT v.marca, v.modello, COUNT(o) as sales " +
            "FROM OrdineAcquisto o " +
            "JOIN o.vehicle v " +
            "WHERE o.dataOrdineAcquisto BETWEEN :startDate AND :endDate " +
            "GROUP BY v.marca, v.modello " +
            "ORDER BY sales DESC")
    List<Object[]> findMostSoldVehicleInPeriod(@Param("startDate") OffsetDateTime startDate, @Param("endDate") OffsetDateTime endDate, Pageable pageable);

    @Query("SELECT v.marca, v.modello, COUNT(v) AS total FROM OrdineAcquisto o " +
            "JOIN o.vehicle v " +
            "GROUP BY v.marca, v.modello " +
            "ORDER BY total DESC")
    List<Object[]> findMostOrderedVehicle(Pageable pageable);

    @Query("SELECT v FROM OrdineAcquisto o " +
            "JOIN o.vehicle v " +
            "WHERE o.dataOrdineAcquisto <= :date " +
            "ORDER BY o.costoTotale DESC")
    List<Vehicle> findHighestPricedVehicleSoldUntil(@Param("date") OffsetDateTime date, Pageable pageable);
}


