package com.develhope.spring.Features.Repository;

import com.develhope.spring.Features.Entity.Noleggio.Noleggio;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.time.OffsetDateTime;

@Repository
public interface NoleggioRepository extends JpaRepository<Noleggio, Long> {

    @Query("SELECT SUM(n.costoTotale) FROM Noleggio n " + "WHERE n.dataInizio BETWEEN :startDate AND :endDate " + "AND n.flagPagato = true")
    BigDecimal calculateTotalRentalProfit(@Param("startDate") OffsetDateTime startDate, @Param("endDate") OffsetDateTime endDate);

    @Query("SELECT COUNT(n) FROM Noleggio n WHERE n.venditore.id = :venditoreId AND n.dataInizio BETWEEN :startDate AND :endDate AND n.flagPagato = true")
    long countSalesByVenditoreAndPeriod(@Param("venditoreId") Long venditoreId, @Param("startDate") OffsetDateTime startDate, @Param("endDate") OffsetDateTime endDate);

    @Query("SELECT SUM(n.costoTotale) FROM Noleggio n WHERE n.venditore.id = :venditoreId AND n.dataInizio BETWEEN :startDate AND :endDate AND n.flagPagato = true")
    BigDecimal calculateProfitByVenditoreAndPeriod(@Param("venditoreId") Long venditoreId, @Param("startDate") OffsetDateTime startDate, @Param("endDate") OffsetDateTime endDate);

}

