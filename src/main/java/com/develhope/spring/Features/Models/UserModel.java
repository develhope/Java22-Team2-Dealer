package com.develhope.spring.Features.Models;

import com.develhope.spring.Features.DTOs.User.UserDTO;
import com.develhope.spring.Features.Entity.User.Role;
import com.develhope.spring.Features.Entity.User.User;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class UserModel {

    private Long userId;

    private String nome;
    private String cognome;
    private String telefono;
    private String email;
    private String password;

    private Role role;

    public UserModel(Long userId, String nome, String cognome, String telefono, String email, String password, Role role) {
        this.userId = userId;
        this.nome = nome;
        this.cognome = cognome;
        this.telefono = telefono;
        this.email = email;
        this.password = password;
        this.role = role;
    }

    public UserModel(String nome, String cognome, String telefono, String email, String password, Role role) {
        this.nome = nome;
        this.cognome = cognome;
        this.telefono = telefono;
        this.email = email;
        this.password = password;
        this.role = role;
    }

    public static User modelToEntity(UserModel userModel) {
        return new User(userModel.getUserId(), userModel.getNome(), userModel.getCognome(), userModel.getTelefono(), userModel.getEmail(), userModel.getPassword(), userModel.getRole());
    }

    public static UserDTO modelToDto(UserModel userModel) {
        return new UserDTO(userModel.getUserId(), userModel.getNome(), userModel.getCognome(), userModel.getTelefono(), userModel.getEmail(), userModel.getPassword(), userModel.getRole());
    }

    public static UserModel entityToModel(User user) {
        return new UserModel(user.getUserId(), user.getNome(), user.getCognome(), user.getTelefono(), user.getEmail(), user.getPassword(), user.getRole());
    }

    public static UserModel dtoToModel(UserDTO userDTO) {
        return new UserModel(userDTO.getUserId(), userDTO.getNome(), userDTO.getCognome(), userDTO.getTelefono(), userDTO.getEmail(), userDTO.getPassword(), userDTO.getRole());
    }

}
