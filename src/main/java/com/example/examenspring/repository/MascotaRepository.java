package com.example.examenspring.repository;

import com.example.examenspring.entity.MascotaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.UUID;

public interface MascotaRepository extends JpaRepository<MascotaEntity, UUID> {


    @Query(value = "SELECT * FROM mascotas WHERE especie ILIKE :especie",nativeQuery = true)
    List<MascotaEntity> findByEspecie (String especie);

}
