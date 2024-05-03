package com.develhope.spring.Models;

import com.develhope.spring.DTOs.Amministratore.AmministratoreDTO;
import com.develhope.spring.entity.Amministratore;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Setter
@Getter
public class AmministratoreModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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