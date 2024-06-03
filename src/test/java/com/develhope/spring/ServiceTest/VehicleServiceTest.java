package com.develhope.spring.ServiceTest;

import com.develhope.spring.Features.DTOs.Vehicle.CreateVehicleRequest;
import com.develhope.spring.Features.DTOs.Vehicle.VehicleDTO;
import com.develhope.spring.Features.Entity.User.Role;
import com.develhope.spring.Features.Entity.User.User;
import com.develhope.spring.Features.Entity.Vehicle.Vehicle;
import com.develhope.spring.Features.Entity.Vehicle.VehicleCondition;
import com.develhope.spring.Features.Repository.VehicleRepository;
import com.develhope.spring.Features.Service.VehicleService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class VehicleServiceTest {

    @Mock
    private VehicleRepository vehicleRepository;

    @InjectMocks
    private VehicleService vehicleService;

    @Test
    public void testCreateVehicle() {
        CreateVehicleRequest vehicleRequest = new CreateVehicleRequest();
        Vehicle vehicle = new Vehicle();

        when(vehicleRepository.save(any(Vehicle.class))).thenReturn(vehicle);

        VehicleDTO vehicleDTO = vehicleService.createVehicle(vehicleRequest);
        assertEquals(vehicle.getMarca(), vehicleDTO.getMarca());

        verify(vehicleRepository, times(1)).save(any(Vehicle.class));
    }

    @Test
    public void testUpdateVehicle_Amministratore() {
        Long vehicleId = 1L;
        User user = new User();
        user.setRole(Role.AMMINISTRATORE);

        Vehicle vehicle = new Vehicle();
        Vehicle vehicleMod = new Vehicle();

        when(vehicleRepository.findById(vehicleId)).thenReturn(Optional.of(vehicle));
        when(vehicleRepository.save(any(Vehicle.class))).thenReturn(vehicle);

        ResponseEntity<?> response = vehicleService.updateVehicle(vehicleId, user, vehicleMod);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        verify(vehicleRepository, times(1)).findById(vehicleId);
        verify(vehicleRepository, times(1)).save(any(Vehicle.class));
    }

    @Test
    public void testUpdateVehicle_NotFound() {
        Long vehicleId = 1L;
        User user = new User();
        user.setRole(Role.AMMINISTRATORE);

        Vehicle vehicleMod = new Vehicle();

        when(vehicleRepository.findById(vehicleId)).thenReturn(Optional.empty());

        ResponseEntity<?> response = vehicleService.updateVehicle(vehicleId, user, vehicleMod);

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        verify(vehicleRepository, times(1)).findById(vehicleId);
    }

    @Test
    public void testDeleteVehicle_Amministratore() {
        Long vehicleId = 1L;
        User user = new User();
        user.setRole(Role.AMMINISTRATORE);

        when(vehicleRepository.existsById(vehicleId)).thenReturn(true);
        doNothing().when(vehicleRepository).deleteById(vehicleId);

        ResponseEntity<?> response = vehicleService.deleteVehicle(vehicleId, user);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        verify(vehicleRepository, times(1)).existsById(vehicleId);
        verify(vehicleRepository, times(1)).deleteById(vehicleId);
    }

    @Test
    public void testDeleteVehicle_NotFound() {
        Long vehicleId = 1L;
        User user = new User();
        user.setRole(Role.AMMINISTRATORE);

        when(vehicleRepository.existsById(vehicleId)).thenReturn(false);

        ResponseEntity<?> response = vehicleService.deleteVehicle(vehicleId, user);

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        verify(vehicleRepository, times(1)).existsById(vehicleId);
        verify(vehicleRepository, times(0)).deleteById(vehicleId);
    }

    @Test
    public void testChangeVehicleCondition_Amministratore() {
        Long vehicleId = 1L;
        User user = new User();
        user.setRole(Role.AMMINISTRATORE);
        VehicleCondition newCondition = VehicleCondition.USATO;

        Vehicle vehicle = new Vehicle();

        when(vehicleRepository.findById(vehicleId)).thenReturn(Optional.of(vehicle));
        when(vehicleRepository.save(any(Vehicle.class))).thenReturn(vehicle);

        ResponseEntity<?> response = vehicleService.changeVehicleCondition(vehicleId, newCondition, user);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        verify(vehicleRepository, times(1)).findById(vehicleId);
        verify(vehicleRepository, times(1)).save(vehicle);
    }

    @Test
    public void testChangeVehicleCondition_NotFound() {
        Long vehicleId = 1L;
        User user = new User();
        user.setRole(Role.AMMINISTRATORE);
        VehicleCondition newCondition = VehicleCondition.USATO;

        when(vehicleRepository.findById(vehicleId)).thenReturn(Optional.empty());

        ResponseEntity<?> response = vehicleService.changeVehicleCondition(vehicleId, newCondition, user);

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        verify(vehicleRepository, times(1)).findById(vehicleId);
        verify(vehicleRepository, times(0)).save(any(Vehicle.class));
    }
}
