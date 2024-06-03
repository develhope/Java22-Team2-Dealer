package com.develhope.spring.ServiceTest;

import com.develhope.spring.Features.Entity.Noleggio.Noleggio;
import com.develhope.spring.Features.Entity.Noleggio.NoleggioLink;
import com.develhope.spring.Features.Repository.NoleggioLinkRepository;
import com.develhope.spring.Features.Service.NoleggioLinkService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
public class NoleggioLinkServiceTest {

    @Mock
    private NoleggioLinkRepository noleggioLinkRepository;

    @InjectMocks
    private NoleggioLinkService noleggioLinkService;

    @Test
    public void testCreateNoleggioLink() {
        NoleggioLink noleggioLink = new NoleggioLink();
        noleggioLinkService.createNoleggioLink(noleggioLink);
        verify(noleggioLinkRepository, times(1)).save(noleggioLink);
    }

    @Test
    public void testDeleteNoleggioLink() {
        Long acquirenteId = 1L;
        Long noleggioId = 1L;
        NoleggioLink noleggioLink = new NoleggioLink();

        when(noleggioLinkRepository.findByAcquirenteUserIdAndNoleggioNoleggioId(acquirenteId, noleggioId)).thenReturn(Optional.of(noleggioLink));

        noleggioLinkService.deleteNoleggioLink(acquirenteId, noleggioId);

        verify(noleggioLinkRepository, times(1)).delete(noleggioLink);
    }

    @Test
    public void testDeleteNoleggioLink_NotFound() {
        Long acquirenteId = 1L;
        Long noleggioId = 1L;

        when(noleggioLinkRepository.findByAcquirenteUserIdAndNoleggioNoleggioId(acquirenteId, noleggioId)).thenReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class, () -> noleggioLinkService.deleteNoleggioLink(acquirenteId, noleggioId));
    }

    @Test
    public void testFindNoleggiByAcquirente() {
        Long acquirenteId = 1L;
        List<Noleggio> noleggi = new ArrayList<>();
        noleggi.add(new Noleggio());
        noleggi.add(new Noleggio());

        when(noleggioLinkRepository.findNoleggiByAcquirenteId(acquirenteId)).thenReturn(noleggi);

        List<Noleggio> result = noleggioLinkService.findNoleggiByAcquirente(acquirenteId);

        assertEquals(noleggi.size(), result.size());
        verify(noleggioLinkRepository, times(1)).findNoleggiByAcquirenteId(acquirenteId);
    }
}
