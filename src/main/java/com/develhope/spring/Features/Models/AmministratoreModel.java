package com.develhope.spring.Features.Models;

import com.develhope.spring.Features.DTOs.Amministratore.AmministratoreDTO;
import com.develhope.spring.Features.Entity.Amministratore.Amministratore;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class AmministratoreModel {

    private Long amministratoreId;

    private String nome;
    private String cognome;
    private String email;
    private String password;

    public AmministratoreModel(Long amministratoreId, String nome, String cognome, String email, String password) {
        this.amministratoreId = amministratoreId;
        this.nome = nome;
        this.cognome = cognome;
        this.email = email;
        this.password = password;
    }

    public AmministratoreModel(String nome, String cognome, String email, String password) {
        this.nome = nome;
        this.cognome = cognome;
        this.email = email;
        this.password = password;
    }

    public static AmministratoreDTO modelToDto(AmministratoreModel amministratoreModel) {
        return new AmministratoreDTO(amministratoreModel.getAmministratoreId(), amministratoreModel.getNome(), amministratoreModel.getCognome(), amministratoreModel.getEmail(), amministratoreModel.getPassword());
    }

    public static Amministratore modelToEntity(AmministratoreModel amministratoreModel) {
        return new Amministratore(amministratoreModel.getAmministratoreId(), amministratoreModel.getNome(), amministratoreModel.getCognome(), amministratoreModel.getEmail(), amministratoreModel.getPassword());
    }

    public static AmministratoreModel entityToModel(Amministratore amministratore) {
        return new AmministratoreModel(amministratore.getAmministratoreId(), amministratore.getNome(), amministratore.getCognome(), amministratore.getEmail(), amministratore.getPassword());
    }

    public static AmministratoreModel dtoToModel(AmministratoreDTO amministratoreDTO) {
        return new AmministratoreModel(amministratoreDTO.getAmministratoreId(), amministratoreDTO.getNome(), amministratoreDTO.getCognome(), amministratoreDTO.getEmail(), amministratoreDTO.getPassword());
    }
}
