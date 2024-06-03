package com.develhope.spring.ServiceTest;

import com.develhope.spring.Features.Entity.User.User;
import com.develhope.spring.Features.Repository.UserRepository;
import com.develhope.spring.Features.Service.VenditoreService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class VenditoreServiceTest {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private VenditoreService venditoreService;

    @Test
    public void testDeleteVenditore() {
        Long venditoreId = 1L;

        doNothing().when(userRepository).deleteById(venditoreId);

        venditoreService.deleteVenditore(venditoreId);

        verify(userRepository, times(1)).deleteById(venditoreId);
    }

    @Test
    public void testUpdateVenditore() {
        Long venditoreId = 1L;

        User venditore = new User();
        venditore.setUserId(venditoreId);
        venditore.setNome("Nome");
        venditore.setCognome("Cognome");
        venditore.setTelefono("1234567890");
        venditore.setEmail("venditore@example.com");
        venditore.setPassword("password");

        when(userRepository.findById(venditoreId)).thenReturn(Optional.of(venditore));
        when(userRepository.save(any(User.class))).thenReturn(venditore);

        User venditoreDetails = new User();
        venditoreDetails.setNome("Nuovo nome");
        venditoreDetails.setCognome("Nuovo cognome");
        venditoreDetails.setTelefono("9876543210");
        venditoreDetails.setEmail("nuovo_venditore@example.com");
        venditoreDetails.setPassword("nuova_password");

        User updatedVenditore = venditoreService.updateVenditore(venditoreId, venditoreDetails);

        verify(userRepository, times(1)).findById(venditoreId);

        verify(userRepository, times(1)).save(any(User.class));

        assertEquals(venditoreId, updatedVenditore.getUserId());
        assertEquals(venditoreDetails.getNome(), updatedVenditore.getNome());
        assertEquals(venditoreDetails.getCognome(), updatedVenditore.getCognome());
        assertEquals(venditoreDetails.getTelefono(), updatedVenditore.getTelefono());
        assertEquals(venditoreDetails.getEmail(), updatedVenditore.getEmail());
        assertEquals(venditoreDetails.getPassword(), updatedVenditore.getPassword());
    }
}
