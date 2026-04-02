package com.example.examenspring.dto.response;

import lombok.Data;

import java.util.Date;
import java.util.List;
import java.util.UUID;

@Data
public class PropietarioResponseDto {

    private UUID id;
    private String nombres;
    private String apellidos;
    private String email;
    private String telefono;
    private String direccion;
    private Date fechaRegistro;
    private List<MascotaResponseDto> mascotas;

}
