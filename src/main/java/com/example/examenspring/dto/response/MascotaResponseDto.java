package com.example.examenspring.dto.response;

import lombok.Data;

import java.util.Date;
import java.util.UUID;

@Data
public class MascotaResponseDto {

    private UUID id;
    private String nombre;
    private String especie;
    private String raza;
    private Double peso;
    private Date fechaNacimiento;

}
