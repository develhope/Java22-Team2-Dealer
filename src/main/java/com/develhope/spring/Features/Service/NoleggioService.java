package com.develhope.spring.Features.Service;
import com.develhope.spring.Features.Entity.OrdineAcquisto.TipoOrdineAcquisto;
import com.develhope.spring.Features.Models.*;
import com.develhope.spring.Features.DTOs.Noleggio.*;
import com.develhope.spring.Features.Entity.Noleggio.*;
import com.develhope.spring.Features.Entity.User.*;
import com.develhope.spring.Features.Entity.Vehicle.Vehicle;
import com.develhope.spring.Features.Repository.*;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;


import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Optional;

@Service
public class NoleggioService {

    @Autowired
    private NoleggioRepository noleggioRepository;
    @Autowired
    private NoleggioLinkRepository noleggioLinkRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private VehicleRepository vehicleRepository;

    // Elimina noleggio
    @Transactional
    public void deleteNoleggio(Long noleggioId) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User currentUser = (User) authentication.getPrincipal();

        NoleggioLink noleggioLink = noleggioLinkRepository.findById(noleggioId) // Modificato
                .orElseThrow(() -> new IllegalArgumentException("Noleggio non trovato"));

        if (noleggioLink.getAcquirente().getUserId().equals(currentUser.getUserId()) ||
                noleggioLink.getVenditore().getUserId().equals(currentUser.getUserId()) ||
                currentUser.getRole() == Role.AMMINISTRATORE) {
            noleggioRepository.deleteById(noleggioLink.getNoleggio().getNoleggioId()); // Modificato
            noleggioLinkRepository.delete(noleggioLink);
        } else {
            throw new AccessDeniedException("Non autorizzato a eliminare questo noleggio");
        }
    }

    // Trova tutti i noleggi
    public List<Noleggio> findAll() {
        return noleggioRepository.findAll();
    }

    // Trova per acquirente
    public List<NoleggioLink> findByAcquirente(Long acquirenteId) {
        return noleggioLinkRepository.findByAcquirenteUserId(acquirenteId);
    }

/*
    // Trova per veicolo
    public List<NoleggioLink> findByVeicolo(Long veicoloId) {
        return noleggioLinkRepository.findByNoleggio_VehicleVehicleId(veicoloId); // Usa il nome corretto del metodo
    }
*/

    // Trova per venditore
    public List<NoleggioLink> findByVenditore(Long venditoreId) {
        return noleggioLinkRepository.findByVenditoreUserId(venditoreId);
    }

    // Trova per ID noleggio (restituisce NoleggioLink)
    public Optional<NoleggioLink> findById(Long noleggioLinkId) { // Modificato
        return noleggioLinkRepository.findById(noleggioLinkId);
    }

    // Crea noleggio (generico o per acquirente specifico)
    public NoleggioDTO createNoleggio(CreateNoleggioRequest createNoleggioRequest, Long acquirenteId) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User currentUser = (User) authentication.getPrincipal();

        // Se acquirenteId è null, l'utente deve essere un amministratore
        if (acquirenteId == null && currentUser.getRole() != Role.AMMINISTRATORE) {
            throw new AccessDeniedException("Solo gli amministratori possono creare noleggi senza specificare l'acquirente");
        }

        // Se acquirenteId è fornito, verifica che l'utente sia l'acquirente stesso o un amministratore
        if (acquirenteId != null && (currentUser.getRole() != Role.AMMINISTRATORE && !currentUser.getUserId().equals(acquirenteId))) {
            throw new AccessDeniedException("Non autorizzato a creare un noleggio per questo acquirente");
        }

        OffsetDateTime dataInizio = createNoleggioRequest.getDataInizio() != null ? createNoleggioRequest.getDataInizio() : OffsetDateTime.now();
        OffsetDateTime dataFine = createNoleggioRequest.getDataFine();
        Vehicle veicolo = vehicleRepository.findById(createNoleggioRequest.getVehicleId())
                .orElseThrow(() -> new IllegalArgumentException("Veicolo non trovato")); // Gestione dell'errore

        if (veicolo.getTipoOrdineAcquisto() == TipoOrdineAcquisto.NON_DISPONIBILE) {
            throw new IllegalArgumentException("Veicolo non disponibile per il noleggio");
        }

        long numeroGiorni = ChronoUnit.DAYS.between(dataInizio, dataFine);
        BigDecimal costoTotale = createNoleggioRequest.getCostoGiornaliero().multiply(BigDecimal.valueOf(numeroGiorni));

        // Recupera acquirente e venditore se l'ID è fornito
        User acquirente = acquirenteId != null ? userRepository.findById(acquirenteId).orElse(null) : null;
        User venditore = createNoleggioRequest.getVenditoreId() != null ? userRepository.findById(createNoleggioRequest.getVenditoreId()).orElse(null) : null;

        Noleggio noleggio = NoleggioModel.modelToEntity(
                new NoleggioModel(dataInizio, dataFine, createNoleggioRequest.getCostoGiornaliero(), costoTotale, createNoleggioRequest.getFlagPagato(), veicolo) // Passa veicolo al costruttore
        );
        noleggio = noleggioRepository.save(noleggio);

        // Crea il NoleggioLink dopo aver salvato il Noleggio
        NoleggioLink noleggioLink = new NoleggioLink(acquirente, noleggio, venditore);
        noleggioLinkRepository.save(noleggioLink);

        return NoleggioModel.modelToDto(NoleggioModel.entityToModel(noleggio));
    }

    // Aggiorna noleggio
    public Noleggio updateNoleggio(Long id, UpdateNoleggioRequest updateNoleggioRequest) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User currentUser = (User) authentication.getPrincipal();

        NoleggioLink noleggioLink = noleggioLinkRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("NoleggioLink non trovato"));

        Noleggio noleggio = noleggioLink.getNoleggio();

        if (!noleggioLink.getAcquirente().getUserId().equals(currentUser.getUserId()) &&
                !noleggioLink.getVenditore().getUserId().equals(currentUser.getUserId()) &&
                currentUser.getRole() != Role.AMMINISTRATORE) {
            throw new AccessDeniedException("Non autorizzato ad aggiornare questo noleggio");
        }

        if (updateNoleggioRequest.getDataInizio() != null) {
            noleggio.setDataInizio(updateNoleggioRequest.getDataInizio());
        }
        if (updateNoleggioRequest.getDataFine() != null) {
            noleggio.setDataFine(updateNoleggioRequest.getDataFine());
        }
        if (updateNoleggioRequest.getCostoGiornaliero() != null) {
            noleggio.setCostoGiornaliero(updateNoleggioRequest.getCostoGiornaliero());
        }
        if (updateNoleggioRequest.getFlagPagato() != null) {
            noleggio.setFlagPagato(updateNoleggioRequest.getFlagPagato());
        }

        long numeroGiorni = ChronoUnit.DAYS.between(noleggio.getDataInizio(), noleggio.getDataFine());
        BigDecimal costoTotale = noleggio.getCostoGiornaliero().multiply(BigDecimal.valueOf(numeroGiorni));
        noleggio.setCostoTotale(costoTotale);

        return noleggioRepository.save(noleggio);
    }
}
