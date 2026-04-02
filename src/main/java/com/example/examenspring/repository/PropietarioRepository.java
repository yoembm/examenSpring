package com.example.examenspring.repository;

import com.example.examenspring.entity.PropietarioEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface PropietarioRepository extends JpaRepository<PropietarioEntity, UUID> {


    @Query(value = "SELECT * FROM propietarios WHERE email = :email",nativeQuery = true)
    Optional<PropietarioEntity> findByEmail(String email);

    @Query(value = "SELECT * FROM propietarios WHERE apellidos ILIKE CONCAT('%', :apellido, '%')",nativeQuery = true)
    List<PropietarioEntity> findBySurname(String apellido);

    @Query(value = "SELECT * FROM propietarios WHERE fecha_registro > :fecha",nativeQuery = true)
    List<PropietarioEntity> findByDate(Date fecha);

}
