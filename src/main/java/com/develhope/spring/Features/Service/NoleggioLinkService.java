package com.develhope.spring.Features.Service;

import com.develhope.spring.Features.Entity.Noleggio.Noleggio;
import com.develhope.spring.Features.Entity.Noleggio.NoleggioLink;
import com.develhope.spring.Features.Entity.OrdineAcquisto.OrdineAcquistoLink;
import com.develhope.spring.Features.Repository.NoleggioLinkRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NoleggioLinkService {

    @Autowired
    private NoleggioLinkRepository noleggioLinkRepository;

    public void createNoleggioLink(NoleggioLink noleggioLink) {
        noleggioLinkRepository.save(noleggioLink);
    }

    public void deleteNoleggioLink(Long acquirenteId, Long noleggioId) throws ResourceNotFoundException {
        NoleggioLink noleggioLink = noleggioLinkRepository.findByAcquirenteUserIdAndNoleggioNoleggioId(acquirenteId, noleggioId)
                .orElseThrow(() -> new ResourceNotFoundException("Noleggio non trovato con acquirente id: " + acquirenteId + " e noleggio id: " + noleggioId));

        noleggioLinkRepository.delete(noleggioLink);
    }

    // Trova noleggi
    public List<Noleggio> findNoleggiByAcquirente(Long acquirenteId) {
        return noleggioLinkRepository.findNoleggiByAcquirenteId(acquirenteId);
    }

}