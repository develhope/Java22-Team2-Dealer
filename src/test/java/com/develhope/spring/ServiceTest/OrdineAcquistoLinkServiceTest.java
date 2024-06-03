package com.develhope.spring.ServiceTest;

import com.develhope.spring.Features.Entity.OrdineAcquisto.OrdineAcquistoLink;
import com.develhope.spring.Features.Repository.OrdineAcquistoLinkRepository;
import com.develhope.spring.Features.Service.OrdineAcquistoLinkService;
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
public class OrdineAcquistoLinkServiceTest {

    @Mock
    private OrdineAcquistoLinkRepository ordineAcquistoLinkRepository;

    @InjectMocks
    private OrdineAcquistoLinkService ordineAcquistoLinkService;

    @Test
    public void testCreateOrdineAcquistoLink() {
        OrdineAcquistoLink ordineAcquistoLink = new OrdineAcquistoLink();

        ordineAcquistoLinkService.createOrdineAcquistoLink(ordineAcquistoLink);

        verify(ordineAcquistoLinkRepository, times(1)).save(ordineAcquistoLink);
    }

    @Test
    public void testDeleteOrdineAcquistoLink() {
        Long acquirenteId = 1L;
        Long ordineAcquistoId = 1L;
        OrdineAcquistoLink ordineAcquistoLink = new OrdineAcquistoLink();

        when(ordineAcquistoLinkRepository.findByAcquirenteUserIdAndOrdineAcquistoOrdineAcquistoId(acquirenteId, ordineAcquistoId)).thenReturn(Optional.of(ordineAcquistoLink));

        ordineAcquistoLinkService.deleteOrdineAcquistoLink(acquirenteId, ordineAcquistoId);

        verify(ordineAcquistoLinkRepository, times(1)).delete(ordineAcquistoLink);
    }

    @Test
    public void testDeleteOrdineAcquistoLink_NotFound() {
        Long acquirenteId = 1L;
        Long ordineAcquistoId = 1L;

        when(ordineAcquistoLinkRepository.findByAcquirenteUserIdAndOrdineAcquistoOrdineAcquistoId(acquirenteId, ordineAcquistoId)).thenReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class, () -> ordineAcquistoLinkService.deleteOrdineAcquistoLink(acquirenteId, ordineAcquistoId));
    }
}
