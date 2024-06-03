package com.develhope.spring.Features.Service;

import com.develhope.spring.Features.DTOs.Noleggio.CreateNoleggioRequest;
import com.develhope.spring.Features.DTOs.Noleggio.UpdateNoleggioRequest;
import com.develhope.spring.Features.Entity.Noleggio.Noleggio;
import com.develhope.spring.Features.Entity.Noleggio.NoleggioLink;
import com.develhope.spring.Features.Entity.User.User;
import com.develhope.spring.Features.Entity.Vehicle.Vehicle;
import com.develhope.spring.Features.Repository.NoleggioLinkRepository;
import com.develhope.spring.Features.Repository.NoleggioRepository;
import com.develhope.spring.Features.Repository.VehicleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.temporal.ChronoUnit;
import java.util.Optional;

@Service
public class NoleggioService {

    @Autowired
    private NoleggioRepository noleggioRepository;

    @Autowired
    private NoleggioLinkRepository noleggioLinkRepository;

    @Autowired
    private VehicleRepository vehicleRepository;

    @Autowired
    private UserService userService;

    public Noleggio createNoleggio(Long acquirenteId, CreateNoleggioRequest createNoleggioRequest, User user) {
        User acquirente = userService.getUserById(acquirenteId);
        Vehicle vehicle = vehicleRepository.findById(createNoleggioRequest.getVehicleId())
                .orElseThrow(() -> new IllegalArgumentException("Vehicle not found: " + createNoleggioRequest.getVehicleId()));

        Noleggio nuovoNoleggio = new Noleggio();
        nuovoNoleggio.setDataInizio(createNoleggioRequest.getDataInizio());
        nuovoNoleggio.setDataFine(createNoleggioRequest.getDataFine());
        nuovoNoleggio.setCostoGiornaliero(createNoleggioRequest.getCostoGiornaliero());
        nuovoNoleggio.setCostoTotale(createNoleggioRequest.getCostoTotale());
        nuovoNoleggio.setFlagPagato(createNoleggioRequest.getFlagPagato());
        nuovoNoleggio.setVehicleId(vehicle);

        long numeroGiorni = ChronoUnit.DAYS.between(createNoleggioRequest.getDataInizio(), createNoleggioRequest.getDataFine());
        BigDecimal costoTotale = createNoleggioRequest.getCostoGiornaliero().multiply(BigDecimal.valueOf(numeroGiorni));
        nuovoNoleggio.setCostoTotale(costoTotale);

        return noleggioRepository.save(nuovoNoleggio);
    }

    public void deleteNoleggio(Long noleggioId) throws ResourceNotFoundException {
        Noleggio noleggio = noleggioRepository.findById(noleggioId)
                .orElseThrow(() -> new ResourceNotFoundException("Order not found with id: " + noleggioId));

        noleggioRepository.delete(noleggio);
    }

    public Noleggio updateNoleggio(Long id, UpdateNoleggioRequest updateNoleggioRequest) {
        NoleggioLink noleggioLink = noleggioLinkRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Rental not found"));

        Noleggio noleggio = noleggioLink.getNoleggio();

        Optional.ofNullable(updateNoleggioRequest.getDataInizio()).ifPresent(noleggio::setDataInizio);
        Optional.ofNullable(updateNoleggioRequest.getDataFine()).ifPresent(noleggio::setDataFine);
        Optional.ofNullable(updateNoleggioRequest.getCostoGiornaliero()).ifPresent(noleggio::setCostoGiornaliero);
        Optional.ofNullable(updateNoleggioRequest.getFlagPagato()).ifPresent(noleggio::setFlagPagato);

        long numeroGiorni = ChronoUnit.DAYS.between(noleggio.getDataInizio(), noleggio.getDataFine());
        BigDecimal costoTotale = noleggio.getCostoGiornaliero().multiply(BigDecimal.valueOf(numeroGiorni));
        noleggio.setCostoTotale(costoTotale);

        return noleggioRepository.save(noleggio);
    }
}
