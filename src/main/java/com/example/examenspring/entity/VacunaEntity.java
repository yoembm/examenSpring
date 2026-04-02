package com.example.examenspring.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;
import java.util.UUID;

@Data
@Entity(name = "vacunas")
public class VacunaEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    private String nombre;
    private String descripcion;
    private String laboratorio;
    private int dosis;
    //@CreationTimestamp
    private Date createdAt;
    //@UpdateTimestamp
    private Date updatedAt;

}
