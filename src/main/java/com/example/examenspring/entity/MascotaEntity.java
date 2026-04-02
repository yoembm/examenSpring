package com.example.examenspring.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;
import java.util.List;
import java.util.UUID;

@Data
@Entity(name = "mascotas")
public class MascotaEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    private String nombre;
    private String especie; // perro, gato, ave, etc.
    private String raza;
    @Column(name = "fecha_nacimiento")
    private Date fechaNacimiento;
    private Double peso;
    @Column(name = "fecha_registro")
    private Date fechaRegistro;
    //@CreationTimestamp
    private Date createdAt;
    //@UpdateTimestamp
    private Date updatedAt;

    @JsonBackReference
    @ManyToOne()
    @JoinColumn(name = "propietario_id_fk")
    private PropietarioEntity propietario;

    @ManyToMany
    @JoinTable(
            name = "mascota_vacuna",
            joinColumns = @JoinColumn(name = "mascota_id_fk"),
            inverseJoinColumns = @JoinColumn(name = "vacuna_id_fk")
    )
    private List<VacunaEntity> vacunaEntities;

}
