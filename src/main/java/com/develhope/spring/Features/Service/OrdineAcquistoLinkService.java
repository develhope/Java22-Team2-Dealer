package com.develhope.spring.Features.Service;

import com.develhope.spring.Features.Entity.OrdineAcquisto.OrdineAcquisto;
import com.develhope.spring.Features.Entity.OrdineAcquisto.OrdineAcquistoLink;
import com.develhope.spring.Features.Repository.OrdineAcquistoLinkRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrdineAcquistoLinkService {

    @Autowired
    private OrdineAcquistoLinkRepository ordineAcquistoLinkRepository;

    public void createOrdineAcquistoLink(OrdineAcquistoLink ordineAcquistoLink) {
        ordineAcquistoLinkRepository.save(ordineAcquistoLink);
    }

    public void deleteOrdineAcquistoLink(Long acquirenteId, Long ordineAcquistoId) throws ResourceNotFoundException {
        OrdineAcquistoLink ordineAcquistoLink = ordineAcquistoLinkRepository.findByAcquirenteUserIdAndOrdineAcquistoOrdineAcquistoId(acquirenteId, ordineAcquistoId)
                .orElseThrow(() -> new ResourceNotFoundException("Order not found with buyer id: " + acquirenteId + " and order id: " + ordineAcquistoId));

        ordineAcquistoLinkRepository.delete(ordineAcquistoLink);
    }

    public List<OrdineAcquisto> getOrdiniAcquistiByAcquirente(Long acquirenteId) {
        return ordineAcquistoLinkRepository.findOrdiniAcquistiByAcquirenteId(acquirenteId);
    }
}
