package com.develhope.spring.ServiceTest;

import com.develhope.spring.Features.DTOs.OrdineAcquisto.UpdateOrdineAcquistoRequest;
import com.develhope.spring.Features.Entity.OrdineAcquisto.OrdineAcquisto;
import com.develhope.spring.Features.Entity.Vehicle.Vehicle;
import com.develhope.spring.Features.Repository.OrdineAcquistoRepository;
import com.develhope.spring.Features.Repository.VehicleRepository;
import com.develhope.spring.Features.Service.OrdineAcquistoService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class OrdineAcquistoServiceTest {

    @Mock
    private OrdineAcquistoRepository ordineAcquistoRepository;

    @Mock
    private VehicleRepository vehicleRepository;

    @InjectMocks
    private OrdineAcquistoService ordineAcquistoService;

    @Test
    public void testCreateOrdineAcquisto() {
        Long vehicleId = 1L;
        OrdineAcquisto ordineAcquisto = new OrdineAcquisto();
        Vehicle vehicle = new Vehicle();

        when(vehicleRepository.findById(vehicleId)).thenReturn(java.util.Optional.of(vehicle));
        when(ordineAcquistoRepository.save(ordineAcquisto)).thenReturn(ordineAcquisto);

        OrdineAcquisto createdOrdineAcquisto = ordineAcquistoService.createOrdineAcquisto(vehicleId, ordineAcquisto);

        assertNotNull(createdOrdineAcquisto);
        verify(vehicleRepository, times(1)).findById(vehicleId);
        verify(ordineAcquistoRepository, times(1)).save(ordineAcquisto);
    }

    @Test
    public void testDeleteOrdineAcquisto() {
        Long ordineAcquistoId = 1L;
        OrdineAcquisto ordineAcquisto = new OrdineAcquisto();

        when(ordineAcquistoRepository.findById(ordineAcquistoId)).thenReturn(Optional.of(ordineAcquisto));

        ordineAcquistoService.deleteOrdineAcquisto(ordineAcquistoId);

        verify(ordineAcquistoRepository, times(1)).delete(ordineAcquisto);
    }

    @Test
    public void testDeleteOrdineAcquisto_NotFound() {
        Long ordineAcquistoId = 1L;

        when(ordineAcquistoRepository.findById(ordineAcquistoId)).thenReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class, () -> ordineAcquistoService.deleteOrdineAcquisto(ordineAcquistoId));
    }

    @Test
    public void testUpdateOrdineAcquisto() {
        Long ordineAcquistoId = 1L;
        UpdateOrdineAcquistoRequest request = new UpdateOrdineAcquistoRequest();
        request.setVehicleId(2L);

        OrdineAcquisto existingOrdineAcquisto = new OrdineAcquisto();
        Vehicle vehicle = new Vehicle();

        when(ordineAcquistoRepository.findById(ordineAcquistoId)).thenReturn(Optional.of(existingOrdineAcquisto));
        when(vehicleRepository.findById(request.getVehicleId())).thenReturn(Optional.of(vehicle));
        when(ordineAcquistoRepository.save(existingOrdineAcquisto)).thenReturn(existingOrdineAcquisto);

        OrdineAcquisto updatedOrdineAcquisto = ordineAcquistoService.updateOrdineAcquisto(ordineAcquistoId, request);

        assertEquals(request.getDataOrdineAcquisto(), updatedOrdineAcquisto.getDataOrdineAcquisto());
        assertEquals(request.getAnticipo(), updatedOrdineAcquisto.getAnticipo());
        assertEquals(request.getCostoTotale(), updatedOrdineAcquisto.getCostoTotale());
        assertEquals(request.getFlagPagato(), updatedOrdineAcquisto.getFlagPagato());
        assertEquals(request.getStatoOrdineAcquisto(), updatedOrdineAcquisto.getStatoOrdineAcquisto());
        assertEquals(vehicle, updatedOrdineAcquisto.getVehicle());
        verify(ordineAcquistoRepository, times(1)).findById(ordineAcquistoId);
        verify(vehicleRepository, times(1)).findById(request.getVehicleId());
        verify(ordineAcquistoRepository, times(1)).save(existingOrdineAcquisto);
    }
}
