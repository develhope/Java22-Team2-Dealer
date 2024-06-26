package com.develhope.spring.Features.Repository;

import com.develhope.spring.Features.Entity.OrdineAcquisto.OrdineAcquisto;
import com.develhope.spring.Features.Entity.OrdineAcquisto.StatoOrdineAcquisto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.List;

@Repository
public interface OrdineAcquistoRepository extends JpaRepository<OrdineAcquisto, Long> {

    List<OrdineAcquisto> findByStatoOrdineAcquisto(StatoOrdineAcquisto stato);

    @Query("SELECT SUM(o.costoTotale) FROM OrdineAcquisto o " + "WHERE o.dataOrdineAcquisto BETWEEN :startDate AND :endDate " + "AND o.flagPagato = true")
    BigDecimal calculateTotalSalesProfit(@Param("startDate") OffsetDateTime startDate, @Param("endDate") OffsetDateTime endDate);

    @Query("SELECT COUNT(oa) FROM OrdineAcquistoLink oaLink " + "JOIN oaLink.ordineAcquisto oa " + "WHERE oaLink.venditore.id = :venditoreId " + "AND oa.dataOrdineAcquisto BETWEEN :startDate AND :endDate " + "AND oa.flagPagato = true")
    long countSalesByVenditoreAndPeriod(@Param("venditoreId") Long venditoreId, @Param("startDate") OffsetDateTime startDate, @Param("endDate") OffsetDateTime endDate);

    @Query("SELECT SUM(oa.costoTotale) FROM OrdineAcquistoLink oaLink " + "JOIN oaLink.ordineAcquisto oa " + "WHERE oaLink.venditore.id = :venditoreId " + "AND oa.dataOrdineAcquisto BETWEEN :startDate AND :endDate " + "AND oa.flagPagato = true")
    BigDecimal calculateProfitByVenditoreAndPeriod(@Param("venditoreId") Long venditoreId, @Param("startDate") OffsetDateTime startDate, @Param("endDate") OffsetDateTime endDate);

}
