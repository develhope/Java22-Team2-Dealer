package com.develhope.spring.ServiceTest;

import com.develhope.spring.Features.Entity.User.Role;
import com.develhope.spring.Features.Entity.User.User;
import com.develhope.spring.Features.Repository.UserRepository;
import com.develhope.spring.Features.Service.AcquirenteService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class AcquirenteServiceTest {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private AcquirenteService acquirenteService;

    private User admin;
    private User acquirente;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);

        admin = new User();
        admin.setUserId(1L);
        admin.setRole(Role.AMMINISTRATORE);

        acquirente = new User();
        acquirente.setUserId(2L);
        acquirente.setRole(Role.ACQUIRENTE);
    }

    @Test
    public void testDeleteById_AdminDeletesUser_Success() {
        when(userRepository.findById(2L)).thenReturn(Optional.of(acquirente));

        Optional<User> deletedUser = acquirenteService.deleteById(2L, admin);

        assertTrue(deletedUser.isPresent());
        assertEquals("deleted_2", deletedUser.get().getNome());
        verify(userRepository, times(1)).save(any(User.class));
    }

    @Test
    public void testDeleteById_UserDeletesSelf_Success() {
        when(userRepository.findById(2L)).thenReturn(Optional.of(acquirente));

        Optional<User> deletedUser = acquirenteService.deleteById(2L, acquirente);

        assertTrue(deletedUser.isPresent());
        assertEquals("deleted_2", deletedUser.get().getNome());
        verify(userRepository, times(1)).save(any(User.class));
    }

    @Test
    public void testDeleteById_UserDeletesAnotherUser_Failure() {
        User anotherUser = new User();
        anotherUser.setUserId(3L);
        anotherUser.setRole(Role.ACQUIRENTE);

        when(userRepository.findById(3L)).thenReturn(Optional.of(anotherUser));

        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            acquirenteService.deleteById(3L, acquirente);
        });

        assertEquals("Only customer can delete their own information or admin can delete any user's information", exception.getMessage());
        verify(userRepository, times(0)).save(any(User.class));
    }

    @Test
    public void testDeleteById_UserNotFound_Failure() {
        when(userRepository.findById(3L)).thenReturn(Optional.empty());

        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            acquirenteService.deleteById(3L, admin);
        });

        assertEquals("User with id 3 not found", exception.getMessage());
        verify(userRepository, times(0)).save(any(User.class));
    }

    @Test
    public void testUpdateAcquirente_AdminUpdatesUser_Success() throws Exception {
        User userMod = new User();
        userMod.setNome("UpdatedName");
        userMod.setCognome("UpdatedCognome");
        userMod.setTelefono("1234567890");
        userMod.setEmail("updated@example.com");
        userMod.setPassword("updatedpassword");

        when(userRepository.findById(2L)).thenReturn(Optional.of(acquirente));

        User updatedUser = acquirenteService.updateAcquirente(2L, userMod, admin);

        assertNotNull(updatedUser);
        assertEquals("UpdatedName", updatedUser.getNome());
        verify(userRepository, times(1)).save(any(User.class));
    }

    @Test
    public void testUpdateAcquirente_UserUpdatesSelf_Success() throws Exception {
        User userMod = new User();
        userMod.setNome("UpdatedName");
        userMod.setCognome("UpdatedCognome");
        userMod.setTelefono("1234567890");
        userMod.setEmail("updated@example.com");
        userMod.setPassword("updatedpassword");

        when(userRepository.findById(2L)).thenReturn(Optional.of(acquirente));

        User updatedUser = acquirenteService.updateAcquirente(2L, userMod, acquirente);

        assertNotNull(updatedUser);
        assertEquals("UpdatedName", updatedUser.getNome());
        verify(userRepository, times(1)).save(any(User.class));
    }

    @Test
    public void testUpdateAcquirente_UserUpdatesAnotherUser_Failure() {
        User anotherUser = new User();
        anotherUser.setUserId(3L);
        anotherUser.setRole(Role.ACQUIRENTE);

        User userMod = new User();
        userMod.setNome("UpdatedName");
        userMod.setCognome("UpdatedCognome");
        userMod.setTelefono("1234567890");
        userMod.setEmail("updated@example.com");
        userMod.setPassword("updatedpassword");

        when(userRepository.findById(3L)).thenReturn(Optional.of(anotherUser));

        Exception exception = assertThrows(Exception.class, () -> {
            acquirenteService.updateAcquirente(3L, userMod, acquirente);
        });

        assertEquals("Buyer and administrators can edit information", exception.getMessage());
        verify(userRepository, times(0)).save(any(User.class));
    }

    @Test
    public void testUpdateAcquirente_UserNotFound_Failure() throws Exception {
        User userMod = new User();
        userMod.setNome("UpdatedName");
        userMod.setCognome("UpdatedCognome");
        userMod.setTelefono("1234567890");
        userMod.setEmail("updated@example.com");
        userMod.setPassword("updatedpassword");

        when(userRepository.findById(3L)).thenReturn(Optional.empty());

        User updatedUser = acquirenteService.updateAcquirente(3L, userMod, admin);

        assertNull(updatedUser);
        verify(userRepository, times(0)).save(any(User.class));
    }
}
