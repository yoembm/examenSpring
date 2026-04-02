package com.example.examenspring.service;

import com.example.examenspring.dto.request.MascotaCreateDto;
import com.example.examenspring.dto.response.MascotaResponseDto;
import com.example.examenspring.entity.MascotaEntity;
import com.example.examenspring.entity.PropietarioEntity;
import com.example.examenspring.exception.ResourceNotFoundException;
import com.example.examenspring.repository.MascotaRepository;
import com.example.examenspring.repository.PropietarioRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class MascotaService {
    private final MascotaRepository mascotaRepository;
    private final PropietarioRepository propietarioRepository;
    private final ModelMapper modelMapper;

    public MascotaService(MascotaRepository mascotaRepository, PropietarioRepository propietarioRepository, ModelMapper modelMapper) {
        this.mascotaRepository = mascotaRepository;
        this.propietarioRepository = propietarioRepository;
        this.modelMapper = modelMapper;
    }


    public MascotaResponseDto saveMascota(MascotaCreateDto dto) {

        Optional<PropietarioEntity> propietarioOpt = propietarioRepository.findById(dto.getPropietarioId());
        if (propietarioOpt.isEmpty()) {
            throw new ResourceNotFoundException("Propietario no encontrado");
        }

        MascotaEntity mascotaEntity = new MascotaEntity();
        modelMapper.map(dto, mascotaEntity);

        mascotaEntity.setPropietario(propietarioOpt.get());
        mascotaEntity.setFechaRegistro(new Date());
        mascotaEntity.setCreatedAt(new Date());
        mascotaEntity.setUpdatedAt(new Date());

        mascotaRepository.save(mascotaEntity);

        MascotaResponseDto responseDto = new MascotaResponseDto();
        modelMapper.map(mascotaEntity, responseDto);


        return responseDto;
    }

    public List<MascotaResponseDto> findByEspecie(String especie) {

        List<MascotaEntity> mascotaEntities = mascotaRepository.findByEspecie(especie);

        List<MascotaResponseDto> responseDtos
                = mascotaEntities.stream()
                .map(mascotaEntity -> {
                    MascotaResponseDto mascotaResponseDto = new MascotaResponseDto();
                    modelMapper.map(mascotaEntity, mascotaResponseDto);
                    return mascotaResponseDto;
                }).toList();

        return responseDtos;
    }

}
