package com.example.examenspring.repository;

import com.example.examenspring.entity.VacunaEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface VacunaRepository extends JpaRepository<VacunaEntity, UUID> {



}
