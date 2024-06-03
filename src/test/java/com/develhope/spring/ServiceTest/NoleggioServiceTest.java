package com.develhope.spring.ServiceTest;

import com.develhope.spring.Features.DTOs.Noleggio.CreateNoleggioRequest;
import com.develhope.spring.Features.DTOs.Noleggio.UpdateNoleggioRequest;
import com.develhope.spring.Features.Entity.Noleggio.Noleggio;
import com.develhope.spring.Features.Entity.Noleggio.NoleggioLink;
import com.develhope.spring.Features.Entity.User.User;
import com.develhope.spring.Features.Entity.Vehicle.Vehicle;
import com.develhope.spring.Features.Repository.NoleggioLinkRepository;
import com.develhope.spring.Features.Repository.NoleggioRepository;
import com.develhope.spring.Features.Repository.VehicleRepository;
import com.develhope.spring.Features.Service.NoleggioService;
import com.develhope.spring.Features.Service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.time.temporal.ChronoUnit;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@SpringBootTest
public class NoleggioServiceTest {

    @Mock
    private NoleggioRepository noleggioRepository;

    @Mock
    private NoleggioLinkRepository noleggioLinkRepository;

    @Mock
    private VehicleRepository vehicleRepository;

    @Mock
    private UserService userService;

    @InjectMocks
    private NoleggioService noleggioService;

    private User user;
    private Vehicle vehicle;
    private Noleggio noleggio;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        user = new User();
        user.setUserId(1L);

        vehicle = new Vehicle();
        vehicle.setVehicleId(1L);

        noleggio = new Noleggio();
        noleggio.setDataInizio(OffsetDateTime.now().truncatedTo(ChronoUnit.SECONDS));
        noleggio.setDataFine(OffsetDateTime.now().plusDays(5).truncatedTo(ChronoUnit.SECONDS));
        noleggio.setCostoGiornaliero(BigDecimal.valueOf(100));
        noleggio.setFlagPagato(true);
        noleggio.setCostoTotale(BigDecimal.valueOf(500));
    }

    @Test
    public void testCreateNoleggio() {
        CreateNoleggioRequest request = new CreateNoleggioRequest();
        request.setVehicleId(vehicle.getVehicleId());
        request.setDataInizio(OffsetDateTime.now().truncatedTo(ChronoUnit.SECONDS));
        request.setDataFine(OffsetDateTime.now().plusDays(5).truncatedTo(ChronoUnit.SECONDS));
        request.setCostoGiornaliero(BigDecimal.valueOf(100));
        request.setFlagPagato(true);

        when(userService.getUserById(user.getUserId())).thenReturn(user);
        when(vehicleRepository.findById(vehicle.getVehicleId())).thenReturn(Optional.of(vehicle));
        when(noleggioRepository.save(any(Noleggio.class))).thenReturn(noleggio);

        Noleggio createdNoleggio = noleggioService.createNoleggio(user.getUserId(), request, user);

        assertNotNull(createdNoleggio);
        assertEquals(request.getDataInizio().truncatedTo(ChronoUnit.SECONDS), createdNoleggio.getDataInizio().truncatedTo(ChronoUnit.SECONDS));
        assertEquals(request.getDataFine().truncatedTo(ChronoUnit.SECONDS), createdNoleggio.getDataFine().truncatedTo(ChronoUnit.SECONDS));
        assertEquals(request.getCostoGiornaliero(), createdNoleggio.getCostoGiornaliero());
        assertEquals(request.getFlagPagato(), createdNoleggio.getFlagPagato());
        assertEquals(request.getCostoGiornaliero().multiply(BigDecimal.valueOf(ChronoUnit.DAYS.between(request.getDataInizio(), request.getDataFine()))), createdNoleggio.getCostoTotale());
        verify(noleggioRepository, times(1)).save(any(Noleggio.class));
    }

    @Test
    public void testDeleteNoleggio() throws ResourceNotFoundException {
        when(noleggioRepository.findById(noleggio.getNoleggioId())).thenReturn(Optional.of(noleggio));

        noleggioService.deleteNoleggio(noleggio.getNoleggioId());

        verify(noleggioRepository, times(1)).delete(noleggio);
    }

    @Test
    public void testUpdateNoleggio() {
        UpdateNoleggioRequest updateRequest = new UpdateNoleggioRequest();
        updateRequest.setDataInizio(OffsetDateTime.now().plusDays(1));
        updateRequest.setDataFine(OffsetDateTime.now().plusDays(6));
        updateRequest.setCostoGiornaliero(BigDecimal.valueOf(110));
        updateRequest.setFlagPagato(false);

        NoleggioLink noleggioLink = new NoleggioLink();
        noleggioLink.setNoleggio(noleggio);

        when(noleggioLinkRepository.findById(noleggio.getNoleggioId())).thenReturn(Optional.of(noleggioLink));
        when(noleggioRepository.save(any(Noleggio.class))).thenReturn(noleggio);

        Noleggio updatedNoleggio = noleggioService.updateNoleggio(noleggio.getNoleggioId(), updateRequest);

        assertNotNull(updatedNoleggio);
        assertEquals(updateRequest.getDataInizio(), updatedNoleggio.getDataInizio());
        assertEquals(updateRequest.getDataFine(), updatedNoleggio.getDataFine());
        assertEquals(updateRequest.getCostoGiornaliero(), updatedNoleggio.getCostoGiornaliero());
        assertEquals(updateRequest.getFlagPagato(), updatedNoleggio.getFlagPagato());
        assertEquals(updateRequest.getCostoGiornaliero().multiply(BigDecimal.valueOf(ChronoUnit.DAYS.between(updateRequest.getDataInizio(), updateRequest.getDataFine()))), updatedNoleggio.getCostoTotale());
        verify(noleggioRepository, times(1)).save(any(Noleggio.class));
    }

    @Test
    public void testDeleteNoleggio_NotFound() {
        when(noleggioRepository.findById(noleggio.getNoleggioId())).thenReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class, () -> {
            noleggioService.deleteNoleggio(noleggio.getNoleggioId());
        });
    }

    @Test
    public void testCreateNoleggio_VehicleNotFound() {
        CreateNoleggioRequest request = new CreateNoleggioRequest();
        request.setVehicleId(vehicle.getVehicleId());
        request.setDataInizio(OffsetDateTime.now());
        request.setDataFine(OffsetDateTime.now().plusDays(5));
        request.setCostoGiornaliero(BigDecimal.valueOf(100));
        request.setFlagPagato(true);

        when(userService.getUserById(user.getUserId())).thenReturn(user);
        when(vehicleRepository.findById(vehicle.getVehicleId())).thenReturn(Optional.empty());

        assertThrows(IllegalArgumentException.class, () -> {
            noleggioService.createNoleggio(user.getUserId(), request, user);
        });
    }
}
