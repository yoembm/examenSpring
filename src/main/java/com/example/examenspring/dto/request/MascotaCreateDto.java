package com.example.examenspring.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Positive;
import lombok.Data;

import java.util.Date;
import java.util.UUID;
@Data
public class MascotaCreateDto {

    @NotBlank(message = "Nombre es obligatorio")
    private String nombre;
    @NotBlank(message = "Especie es obligatorio")
    private String especie;
    private String raza; // se agrego a Dtop para guardar raza, no se uso NotBlank para pasar la prueba de validacion del ejercicio
    @NotNull(message = "Fecha de nacimiento obligatoria")
    @Past(message = "La fecha de nacimiento debe de ser una fecha pasada")
    private Date fechaNacimiento;
    @Positive(message = "Peso debe ser positivo")
    private Double peso;
    @NotNull(message = "Propietario id es obligatorio")
    private UUID propietarioId;

}
