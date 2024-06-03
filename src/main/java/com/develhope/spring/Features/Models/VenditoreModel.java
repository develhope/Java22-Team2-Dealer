package com.develhope.spring.Features.Models;

import com.develhope.spring.Features.DTOs.Venditore.VenditoreDTO;
import com.develhope.spring.Features.Entity.Venditore.Venditore;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class VenditoreModel {

    private Long venditoreId;

    private String nome;
    private String cognome;
    private String telefono;
    private String email;
    private String password;

    public VenditoreModel(Long venditoreId, String nome, String cognome, String telefono, String email, String password) {
        this.venditoreId = venditoreId;
        this.nome = nome;
        this.cognome = cognome;
        this.telefono = telefono;
        this.email = email;
        this.password = password;
    }

    public VenditoreModel(String nome, String cognome, String telefono, String email, String password) {
        this.nome = nome;
        this.cognome = cognome;
        this.telefono = telefono;
        this.email = email;
        this.password = password;
    }

    public static VenditoreDTO modelToDto(VenditoreModel venditoreModel) {
        return new VenditoreDTO(venditoreModel.getVenditoreId(), venditoreModel.getNome(), venditoreModel.getCognome(), venditoreModel.getTelefono(), venditoreModel.getEmail(), venditoreModel.getPassword());
    }

    public static Venditore modelToEntity(VenditoreModel venditoreModel) {
        return new Venditore(venditoreModel.getVenditoreId(), venditoreModel.getNome(), venditoreModel.getCognome(), venditoreModel.getTelefono(), venditoreModel.getEmail(), venditoreModel.getPassword());
    }

    public static VenditoreModel entityToModel(Venditore venditore) {
        return new VenditoreModel(venditore.getVenditoreId(), venditore.getNome(), venditore.getCognome(), venditore.getTelefono(), venditore.getEmail(), venditore.getPassword());
    }

    public static VenditoreModel dtoToModel(VenditoreDTO venditoreDTO) {
        return new VenditoreModel(venditoreDTO.getVenditoreId(), venditoreDTO.getNome(), venditoreDTO.getCognome(), venditoreDTO.getTelefono(), venditoreDTO.getEmail(), venditoreDTO.getPassword());
    }
}
