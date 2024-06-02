package com.develhope.spring.Features.Service;

import com.develhope.spring.Features.Entity.User.User;
import com.develhope.spring.Features.Repository.NoleggioRepository;
import com.develhope.spring.Features.Repository.OrdineAcquistoRepository;
import com.develhope.spring.Features.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.OffsetDateTime;

@Service
public class VenditoreService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private OrdineAcquistoRepository ordineAcquistoRepository;

    @Autowired
    private NoleggioRepository noleggioRepository;

    public void deleteVenditore(Long id) {
        userRepository.deleteById(id);
    }

    public User updateVenditore(Long id, User venditoreDetails) {
        User venditore = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Seller not found"));
        venditore.setNome(venditoreDetails.getNome());
        venditore.setCognome(venditoreDetails.getCognome());
        venditore.setTelefono(venditoreDetails.getTelefono());
        venditore.setEmail(venditoreDetails.getEmail());
        venditore.setPassword(venditoreDetails.getPassword());
        return userRepository.save(venditore);
    }

    public long getSalesCount(Long id, OffsetDateTime startDate, OffsetDateTime endDate) {
        long salesFromOrders = ordineAcquistoRepository.countSalesByVenditoreAndPeriod(id, startDate, endDate);
        long salesFromRentals = noleggioRepository.countSalesByVenditoreAndPeriod(id, startDate, endDate);
        return salesFromOrders + salesFromRentals;
    }

    public BigDecimal getProfit(Long id, OffsetDateTime startDate, OffsetDateTime endDate) {
        BigDecimal profitFromOrders = ordineAcquistoRepository.calculateProfitByVenditoreAndPeriod(id, startDate, endDate);
        BigDecimal profitFromRentals = noleggioRepository.calculateProfitByVenditoreAndPeriod(id, startDate, endDate);
        if (profitFromOrders == null && profitFromRentals == null) {
            return BigDecimal.ZERO;
        }
        profitFromOrders = (profitFromOrders == null ? BigDecimal.ZERO : profitFromOrders);
        profitFromRentals = (profitFromRentals == null ? BigDecimal.ZERO : profitFromRentals);
        return profitFromOrders.add(profitFromRentals);
    }
}
