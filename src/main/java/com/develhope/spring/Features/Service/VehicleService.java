package com.develhope.spring.Features.Service;

import com.develhope.spring.Features.DTOs.Vehicle.CreateVehicleRequest;
import com.develhope.spring.Features.DTOs.Vehicle.VehicleDTO;
import com.develhope.spring.Features.Entity.User.Role;
import com.develhope.spring.Features.Entity.User.User;
import com.develhope.spring.Features.Models.VehicleModel;
import com.develhope.spring.Features.Entity.Vehicle.Vehicle;
import com.develhope.spring.Features.Entity.Vehicle.Allestimento;
import com.develhope.spring.Features.Entity.OrdineAcquisto.TipoOrdineAcquisto;
import com.develhope.spring.Features.Entity.Vehicle.TipoVeicolo;
import com.develhope.spring.Features.Entity.Vehicle.VehicleCondition;
import com.develhope.spring.Features.Repository.NoleggioRepository;
import com.develhope.spring.Features.Repository.OrdineAcquistoRepository;
import com.develhope.spring.Features.Repository.VehicleRepository;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class VehicleService {

    @Autowired
    private VehicleRepository vehicleRepository;

    @Autowired
    private NoleggioRepository noleggioRepository;

    @Autowired
    private OrdineAcquistoRepository ordineAcquistoRepository;

    public VehicleDTO createVehicle(CreateVehicleRequest vehicleRequest) {
        VehicleModel vehicle = new VehicleModel(vehicleRequest.getMarca(), vehicleRequest.getTipoVeicolo(), vehicleRequest.getModello(), vehicleRequest.getCilindrata(), vehicleRequest.getColore(), vehicleRequest.getPotenza(), vehicleRequest.getTipoDiCambio(), vehicleRequest.getAnnoImmatricolazione(), vehicleRequest.getAlimentazione(), vehicleRequest.getPrezzo(), vehicleRequest.getAllestimento(), vehicleRequest.getAccessori(), vehicleRequest.getVehicleCondition(), vehicleRequest.getTipoOrdineAcquisto());
        VehicleModel vehicleModel1 = VehicleModel.entityToModel(vehicleRepository.save(VehicleModel.modelToEntity(vehicle)));
        return VehicleModel.modelToDto(vehicleModel1);
    }

    public ResponseEntity<?> updateVehicle(Long id, User user, Vehicle vehicleMod) {
        if (user.getRole() == Role.AMMINISTRATORE) {
            Optional<Vehicle> optionalVehicle = vehicleRepository.findById(id);
            if (optionalVehicle.isEmpty()) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Vehicle with id " + id + " not found");
            } else {
                Vehicle vehicle = optionalVehicle.get();
                if (vehicleMod.getMarca() != null) {
                    vehicle.setMarca(vehicleMod.getMarca());
                }
                if (vehicleMod.getTipoVeicolo() != null) {
                    vehicle.setTipoVeicolo(vehicleMod.getTipoVeicolo());
                }
                if (vehicleMod.getModello() != null) {
                    vehicle.setModello(vehicleMod.getModello());
                }
                if (vehicleMod.getCilindrata() != 0) {
                    vehicle.setCilindrata(vehicleMod.getCilindrata());
                }
                if (vehicleMod.getColore() != null) {
                    vehicle.setColore(vehicleMod.getColore());
                }
                if (vehicleMod.getPotenza() != 0) {
                    vehicle.setPotenza(vehicleMod.getPotenza());
                }
                if (vehicleMod.getTipoDiCambio() != null) {
                    vehicle.setTipoDiCambio(vehicleMod.getTipoDiCambio());
                }
                if (vehicleMod.getAnnoImmatricolazione() != 0) {
                    vehicle.setAnnoImmatricolazione(vehicleMod.getAnnoImmatricolazione());
                }
                if (vehicleMod.getAlimentazione() != null) {
                    vehicle.setAlimentazione(vehicleMod.getAlimentazione());
                }
                if (vehicleMod.getPrezzo() != null) {
                    vehicle.setPrezzo(vehicleMod.getPrezzo());
                }
                if (vehicleMod.getAllestimento() != null) {
                    vehicle.setAllestimento(vehicleMod.getAllestimento());
                }
                if (vehicleMod.getAccessori() != null) {
                    vehicle.setAccessori(vehicleMod.getAccessori());
                }
                if (vehicleMod.getVehicleCondition() != null) {
                    vehicle.setVehicleCondition(vehicleMod.getVehicleCondition());
                }
                if (vehicleMod.getTipoOrdineAcquisto() != null) {
                    vehicle.setTipoOrdineAcquisto(vehicleMod.getTipoOrdineAcquisto());
                }
                vehicleRepository.save(vehicle);
                return ResponseEntity.ok().body("Vehicle successfully updated");
            }
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Unauthorized access");
        }
    }

    public ResponseEntity<?> deleteVehicle(Long id, User user) {
        if (user.getRole() == Role.AMMINISTRATORE) {
            if (vehicleRepository.existsById(id)) {
                vehicleRepository.deleteById(id);
                return ResponseEntity.ok().body("Vehicle successfully deleted");
            } else {
                return ResponseEntity.status(404).body("Vehicle not found");
            }
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Unauthorized access");
        }
    }

    public ResponseEntity<?> changeVehicleCondition(Long id, VehicleCondition newCondition, User user) {
        if (user.getRole() == Role.AMMINISTRATORE) {
            Optional<Vehicle> optionalVehicle = vehicleRepository.findById(id);
            if (optionalVehicle.isPresent()) {
                Vehicle vehicle = optionalVehicle.get();
                vehicle.setVehicleCondition(newCondition);
                vehicleRepository.save(vehicle);
                return ResponseEntity.ok().body("Vehicle condition successfully changed");
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Vehicle not found");
            }
        }
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Unauthorized access");
    }

    public List<Vehicle> searchVehicles(String marca, String modello, TipoVeicolo tipoVeicolo, Integer cilindrata, String colore, Integer potenza, String tipoDiCambio, Integer annoImmatricolazione, String alimentazione, BigDecimal prezzo, Allestimento allestimento, String accessori, VehicleCondition vehicleCondition, TipoOrdineAcquisto tipoOrdineAcquisto) {
        if (marca != null) {
            return vehicleRepository.searchByMarca(marca);
        } else if (modello != null) {
            return vehicleRepository.searchByModello(modello);
        } else if (tipoVeicolo != null) {
            return vehicleRepository.searchByTipoVeicolo(tipoVeicolo);
        } else if (cilindrata != null) {
            return vehicleRepository.searchByCilindrata(cilindrata);
        } else if (colore != null) {
            return vehicleRepository.searchByColore(colore);
        } else if (potenza != null) {
            return vehicleRepository.searchByPotenza(potenza);
        } else if (tipoDiCambio != null) {
            return vehicleRepository.searchByTipoDiCambio(tipoDiCambio);
        } else if (annoImmatricolazione != null) {
            return vehicleRepository.searchByAnnoImmatricolazione(annoImmatricolazione);
        } else if (alimentazione != null) {
            return vehicleRepository.searchByAlimentazione(alimentazione);
        } else if (prezzo != null) {
            return vehicleRepository.searchByPrezzo(prezzo);
        } else if (allestimento!= null) {
            return vehicleRepository.searchByAllestimento(allestimento);
        } else if (accessori!= null) {
            return vehicleRepository.searchByAccessori(accessori);
        } else if (vehicleCondition!= null) {
            return vehicleRepository.searchByVehicleCondition(vehicleCondition);
        } else if (tipoOrdineAcquisto!= null) {
            return vehicleRepository.searchByTipoOrdineAcquisto(tipoOrdineAcquisto);
        }
        return vehicleRepository.findAll();
    }

    public List<Vehicle> searchByVehicleCondition(VehicleCondition vehicleCondition) {
        return vehicleRepository.searchByVehicleCondition(vehicleCondition);
    }

    public List<Vehicle> searchByTipoOrdine(TipoOrdineAcquisto tipoOrdineAcquisto) {
        return vehicleRepository.searchByTipoOrdineAcquisto(tipoOrdineAcquisto);
    }

    public List<Vehicle> searchByTipoVeicolo(TipoVeicolo tipoVeicolo) {
        return vehicleRepository.searchByTipoVeicolo(tipoVeicolo);
    }

    public List<Object[]> getMostSoldVehicleInPeriod(OffsetDateTime startDate, OffsetDateTime endDate) {
        Pageable pageable = PageRequest.of(0, 1);
        return vehicleRepository.findMostSoldVehicleInPeriod(startDate, endDate, pageable);
    }

    public Object[] getMostOrderedVehicle() {
        Pageable pageable = PageRequest.of(0, 1);
        List<Object[]> results = vehicleRepository.findMostOrderedVehicle(pageable);
        return results.stream().findFirst().orElse(null);
    }

    public Vehicle getHighestPricedVehicleSoldUntil(OffsetDateTime date) {
        Pageable pageable = PageRequest.of(0, 1);
        List<Vehicle> results = vehicleRepository.findHighestPricedVehicleSoldUntil(date, pageable);
        return results.stream().findFirst().orElse(null);
    }

    public BigDecimal calculateDealershipProfit(OffsetDateTime startDate, OffsetDateTime endDate) {
        BigDecimal totalSalesProfit = ordineAcquistoRepository.calculateTotalSalesProfit(startDate, endDate);
        BigDecimal totalRentalProfit = noleggioRepository.calculateTotalRentalProfit(startDate, endDate);

        if (totalSalesProfit == null && totalRentalProfit == null) {
            return null;
        }

        totalSalesProfit = (totalSalesProfit == null ? BigDecimal.ZERO : totalSalesProfit);
        totalRentalProfit = (totalRentalProfit == null ? BigDecimal.ZERO : totalRentalProfit);

        return totalSalesProfit.add(totalRentalProfit);
    }
}
