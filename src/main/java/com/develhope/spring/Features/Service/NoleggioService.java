package com.develhope.spring.Features.Service;

import org.springframework.stereotype.Service;

@Service
public class NoleggioService {

//    @Autowired
//    private NoleggioRepository noleggioRepository;
//
//    @Autowired
//    private AcquirenteRepository acquirenteRepository;
//
//    @Autowired
//    private VehicleRepository vehicleRepository;
//
//    @Autowired
//    private VenditoreRepository venditoreRepository;
//
//    // delete noleggio
//    public Optional<Noleggio> deleteNoleggio(Long noleggioId) {
//        Optional<Noleggio> optionalNoleggio = noleggioRepository.findById(noleggioId);
//        if (optionalNoleggio.isPresent()) {
//            noleggioRepository.deleteById(noleggioId);
//        }
//        return optionalNoleggio;
//    }
//
//    // find all
//    public List<Noleggio> findAll() {
//        return noleggioRepository.findAll();
//    }
//
//    // find by acquirente
//    public List<Noleggio> findByAcquirente(Long acquirenteId) {
//        return noleggioRepository.findByAcquirenteId(acquirenteId);
//    }
//
//    //find by veicolo
//    public List<Noleggio> findByVeicolo(Long veicoloId) {
//        return noleggioRepository.findByVehicle_VehicleId(veicoloId);
//    }
//
//    // find by venditore
//    public List<Noleggio> findByVenditore(Long venditoreId) {
//        return noleggioRepository.findByVenditoreVenditoreId(venditoreId);
//    }
//
//    // find by noleggio id
//    public Optional<Noleggio> findById(Long noleggioId) {
//        return noleggioRepository.findById(noleggioId);
//    }
//
//    // create noleggio
//    public NoleggioDTO createNoleggioForAcquirente(Long acquirenteId, CreateNoleggioRequest createNoleggioRequest) {
//        OffsetDateTime dateTime = createNoleggioRequest.getDataInizio() != null ? createNoleggioRequest.getDataInizio() : OffsetDateTime.now();
//        OffsetDateTime dateTime2 = createNoleggioRequest.getDataFine();
//
//        Acquirente acquirente = acquirenteRepository.findById(acquirenteId).orElse(null);
//        Vehicle vehicle = vehicleRepository.findById(createNoleggioRequest.getVehicleId()).orElse(null);
//        Venditore venditore = venditoreRepository.findById(createNoleggioRequest.getVenditoreId()).orElse(null);
//
//        if (acquirente == null || vehicle == null || venditore == null ) {
//            throw new IllegalArgumentException("Acquirente, Vehicle o Venditore non trovato");
//        } else if (vehicle.getTipoOrdine() == TipoOrdine.NON_DISPONIBILE) {
//            throw new IllegalArgumentException("Veicolo non ordinabile");
//        }
//
//        long numeroGiorni = ChronoUnit.DAYS.between(dateTime, dateTime2);
//
//        BigDecimal costoTotale = createNoleggioRequest.getCostoGiornaliero().multiply(BigDecimal.valueOf(numeroGiorni));
//
//        NoleggioModel noleggioModel = new NoleggioModel(dateTime, dateTime2, createNoleggioRequest.getCostoGiornaliero(), costoTotale, createNoleggioRequest.getFlagPagato(), acquirente, vehicle, venditore);
//        NoleggioModel noleggioModel1 = NoleggioModel.entityToModel(noleggioRepository.save(NoleggioModel.modelToEntity(noleggioModel)));
//        return NoleggioModel.modelToDto(noleggioModel1);
//    }
//
//    public NoleggioDTO createNoleggio(CreateNoleggioRequest createNoleggioRequest){
//        OffsetDateTime dateTime = createNoleggioRequest.getDataInizio() != null ? createNoleggioRequest.getDataInizio() : OffsetDateTime.now();
//        OffsetDateTime dateTime2 = createNoleggioRequest.getDataFine();
//        long numeroGiorni = ChronoUnit.DAYS.between(dateTime, dateTime2);
//
//        BigDecimal costoTotale = createNoleggioRequest.getCostoGiornaliero().multiply(BigDecimal.valueOf(numeroGiorni));
//
//        NoleggioModel noleggioModel = new NoleggioModel(dateTime, dateTime2, createNoleggioRequest.getCostoGiornaliero(), costoTotale, createNoleggioRequest.getFlagPagato());
//        NoleggioModel noleggioModel1 = NoleggioModel.entityToModel(noleggioRepository.save(NoleggioModel.modelToEntity(noleggioModel)));
//        return NoleggioModel.modelToDto(noleggioModel1);
//    }
//
//    public Optional<Noleggio> deleteNoleggioForAcquirente(Long acquirenteId, Long id) {
//        Optional<Noleggio> optionalNoleggio = noleggioRepository.findById(id);
//        if (optionalNoleggio.isPresent()) {
//            Noleggio noleggio = optionalNoleggio.get();
//            if (noleggio.getAcquirente().getAcquirenteId().equals(acquirenteId)) {
//                noleggioRepository.delete(noleggio);
//                return optionalNoleggio;
//            }
//        }
//        return Optional.empty();
//    }
//
//    public Optional<Noleggio> updateNoleggioForAcquirente(Long acquirenteId, Long id, UpdateNoleggioRequest updateNoleggioRequest) {
//        Optional<Noleggio> optionalNoleggio = noleggioRepository.findById(id);
//        if (optionalNoleggio.isPresent()) {
//            Noleggio noleggio = optionalNoleggio.get();
//            if (noleggio.getAcquirente().getAcquirenteId().equals(acquirenteId)) {
//                noleggio = noleggioRepository.save(noleggio);
//                return Optional.of(noleggio);
//            }
//        }
//        return Optional.empty();
//    }
//
//    public Noleggio updateNoleggioById(Long id, CreateNoleggioRequest createNoleggioRequest){
//        Noleggio noleggio = noleggioRepository.findById(id).orElse(null);
//        if(noleggio != null) {
//            noleggio.setDataInizio(createNoleggioRequest.getDataInizio());
//            noleggio.setDataFine(createNoleggioRequest.getDataFine());
//            noleggio.setCostoGiornaliero(createNoleggioRequest.getCostoGiornaliero());
//            noleggio.setCostoTotale(createNoleggioRequest.getCostoTotale());
//            noleggio.setFlagPagato(createNoleggioRequest.getFlagPagato());
//            return noleggioRepository.save(noleggio);
//        }
//        return null;
//    }

}




