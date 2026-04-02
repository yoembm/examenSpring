package com.example.examenspring.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

@Data
public class PropietarioCreateDto {

    @NotBlank(message = "El email es obligatorio")
    @Email(message = "El formato de email no es valido")
    private String email;
    @NotBlank(message = "El telefono es obligatorio")
    @Pattern(regexp = "^\\d{7,9}$",message = "Telefono solo dígitos, entre 7 y 9 caracteres")
    private String telefono;
    @NotBlank(message = "El DNI es obligatorio")
    @Pattern(regexp = "^\\d+$", message = "El DNI debe contener exactamente 8 digitos numericos")
    private String dni;
    //@NotBlank(message = "Nombre es obligatorio")
    //private String nombres;
    //@NotBlank(message = "Apellidos son obligatorio")
    //private String apellidos;
    @NotBlank(message = "Direccion es obligatorio")
    private String direccion;

}
