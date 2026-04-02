package com.example.examenspring.mapper;

import com.example.examenspring.dto.request.MascotaCreateDto;
import com.example.examenspring.dto.request.PropietarioCreateDto;
import com.example.examenspring.dto.response.MascotaResponseDto;
import com.example.examenspring.dto.response.PropietarioResponseDto;
import com.example.examenspring.entity.MascotaEntity;
import com.example.examenspring.entity.PropietarioEntity;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ModelMapperConfig {

    @Bean
    public ModelMapper modelMapper(){
        ModelMapper modelMapper = new ModelMapper();

        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);

        modelMapper.createTypeMap(PropietarioEntity.class, PropietarioResponseDto.class);
        modelMapper.createTypeMap(PropietarioCreateDto.class, PropietarioEntity.class);

        modelMapper.createTypeMap(MascotaEntity.class,MascotaResponseDto.class);
        modelMapper.createTypeMap(MascotaCreateDto.class, MascotaEntity.class);

        return modelMapper;
    }

}
