package com.example.examenspring.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;
import java.util.List;
import java.util.UUID;

@Data
@Entity(name = "propietarios")
public class PropietarioEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    @Column(nullable = false)
    private String nombres;
    @Column(nullable = false)
    private String apellidos;
    private String email;
    private String telefono;
    private String dni;
    private String direccion;
    @Column(name = "fecha_registro")
    private Date fechaRegistro;
    //@CreationTimestamp
    private Date createdAt;
    //@UpdateTimestamp
    private Date updatedAt;

    @JsonManagedReference
    @OneToMany(mappedBy = "propietario",fetch = FetchType.LAZY)
    private List<MascotaEntity> mascotas;
}
