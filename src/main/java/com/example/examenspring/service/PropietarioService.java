package com.example.examenspring.service;

import com.example.examenspring.dto.request.PropietarioCreateDto;
import com.example.examenspring.dto.response.MascotaResponseDto;
import com.example.examenspring.dto.response.PropietarioResponseDto;
import com.example.examenspring.dto.response.ReniecResponse;
import com.example.examenspring.entity.MascotaEntity;
import com.example.examenspring.entity.PropietarioEntity;
import com.example.examenspring.exception.ExternalServiceException;
import com.example.examenspring.exception.ResourceNotFoundException;
import com.example.examenspring.feignClient.ReniecCliente;
import com.example.examenspring.repository.PropietarioRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class PropietarioService {

    private final PropietarioRepository propietarioRepository;
    private final ModelMapper modelMapper;
    private final ReniecCliente reniecCliente;

    @Value("${api.token}")
    private String apiToken;


    public PropietarioService(PropietarioRepository propietarioRepository, ModelMapper modelMapper, ReniecCliente reniecCliente) {
        this.propietarioRepository = propietarioRepository;
        this.modelMapper = modelMapper;
        this.reniecCliente = reniecCliente;
    }


    public PropietarioResponseDto savePropietario(PropietarioCreateDto dto) {
        // check by email
        Optional<PropietarioEntity> propietarioOpt = propietarioRepository.findByEmail(dto.getEmail());
        if (!propietarioOpt.isEmpty()) {
            throw new ResourceNotFoundException("Ya existe un propietario registrado con ese email");
        }


        PropietarioEntity propietarioEntity = new PropietarioEntity();
        modelMapper.map(dto, propietarioEntity);

        // get Sunat data
        ReniecResponse personaInfo = null;

        try {
            personaInfo = reniecCliente.getReniecInfo(propietarioEntity.getDni(), apiToken);


        } catch (Exception e) {
            throw new ExternalServiceException(e.getMessage(),e);
        }

        propietarioEntity.setNombres(personaInfo.getFirstName());
        propietarioEntity.setApellidos(personaInfo.getFirstLastName() + " " + personaInfo.getSecondLastName());
        propietarioEntity.setFechaRegistro(new Date());
        propietarioEntity.setCreatedAt(new Date());
        propietarioEntity.setUpdatedAt(new Date());

        propietarioRepository.save(propietarioEntity);

        PropietarioResponseDto responseDto = new PropietarioResponseDto();
        modelMapper.map(propietarioEntity, responseDto);

        return responseDto;
    }

    public PropietarioResponseDto findById(UUID id) {

        Optional<PropietarioEntity> propietarioOpt = propietarioRepository.findById(id);

        if (propietarioOpt.isEmpty()) {
            throw new ResourceNotFoundException("Propietario no encontrado");
        }

        PropietarioEntity propietario = propietarioOpt.get();

        List<MascotaEntity> mascotas = propietario.getMascotas();

        List<MascotaResponseDto> mascotaResponseDtos
                = mascotas.stream()
                .map(mascotaEntity -> {
                    MascotaResponseDto mascotaResponseDto = new MascotaResponseDto();
                    modelMapper.map(mascotaEntity, mascotaResponseDto);
                    return mascotaResponseDto;
                }).toList();

        // prepare response
        PropietarioResponseDto responseDto = new PropietarioResponseDto();
        modelMapper.map(propietario, responseDto);
        responseDto.setMascotas(mascotaResponseDtos);

        return responseDto;
    }


    public List<PropietarioResponseDto> findByApellidos(String apellido) {

        List<PropietarioEntity> propietarioEntities = propietarioRepository.findBySurname(apellido);

        List<PropietarioResponseDto> propietarioResponseDtos
                = propietarioEntities.stream()
                .map(propietarioEntity -> {
                    PropietarioResponseDto propietarioResponseDto = new PropietarioResponseDto();
                    modelMapper.map(propietarioEntity, propietarioResponseDto);
                    return propietarioResponseDto;
                }).toList();

        return propietarioResponseDtos;

    }


}
