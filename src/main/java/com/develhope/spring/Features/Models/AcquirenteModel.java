package com.develhope.spring.Features.Models;

import com.develhope.spring.Features.DTOs.Acquirente.AcquirenteDTO;
import com.develhope.spring.Features.Entity.Acquirente.Acquirente;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class AcquirenteModel {

    private Long acquirenteId;

    private String nome;
    private String cognome;
    private String telefono;
    private String email;
    private String password;

    public AcquirenteModel(Long acquirenteId, String nome, String cognome, String telefono, String email, String password) {
        this.acquirenteId = acquirenteId;
        this.nome = nome;
        this.cognome = cognome;
        this.telefono = telefono;
        this.email = email;
        this.password = password;
    }

    public AcquirenteModel(String nome, String cognome, String telefono, String email, String password) {

        this.nome = nome;
        this.cognome = cognome;
        this.telefono = telefono;
        this.email = email;
        this.password = password;
    }

    public static AcquirenteDTO modelToDto(AcquirenteModel acquirenteModel) {
        return new AcquirenteDTO(acquirenteModel.getAcquirenteId(), acquirenteModel.getNome(), acquirenteModel.getCognome(), acquirenteModel.getTelefono(), acquirenteModel.getEmail(), acquirenteModel.getPassword());
    }

    public static Acquirente modelToEntity(AcquirenteModel acquirenteModel) {
        return new Acquirente(acquirenteModel.getAcquirenteId(), acquirenteModel.getCognome(), acquirenteModel.getTelefono(), acquirenteModel.getEmail(), acquirenteModel.getPassword(), acquirenteModel.getNome());
    }

    public static AcquirenteModel entityToModel(Acquirente acquirente) {
        return new AcquirenteModel(acquirente.getAcquirenteId(), acquirente.getNome(), acquirente.getCognome(), acquirente.getTelefono(), acquirente.getEmail(), acquirente.getPassword());
    }

    public static AcquirenteModel dtoToModel(AcquirenteDTO acquirenteDTO) {
        return new AcquirenteModel(acquirenteDTO.getAcquirenteId(), acquirenteDTO.getNome(), acquirenteDTO.getCognome(), acquirenteDTO.getTelefono(), acquirenteDTO.getEmail(), acquirenteDTO.getPassword());
    }
}

