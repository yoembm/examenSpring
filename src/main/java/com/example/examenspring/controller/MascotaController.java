package com.example.examenspring.controller;

import com.example.examenspring.dto.request.MascotaCreateDto;
import com.example.examenspring.dto.response.ApiResponse;
import com.example.examenspring.dto.response.MascotaResponseDto;
import com.example.examenspring.repository.MascotaRepository;
import com.example.examenspring.service.MascotaService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/mascota")
public class MascotaController {

    private final MascotaService mascotaService;

    public MascotaController(MascotaService mascotaService) {
        this.mascotaService = mascotaService;
    }


    @PostMapping("/save")
    public ResponseEntity<ApiResponse<MascotaResponseDto>> save(@Valid @RequestBody MascotaCreateDto dto) {
        MascotaResponseDto responseDto = mascotaService.saveMascota(dto);

        return ResponseEntity.status(HttpStatus.CREATED).body(
                new ApiResponse<>(true, "Mascota creada", responseDto)
        );
    }

    @GetMapping("/find/especie/{especie}")
    public ResponseEntity<ApiResponse<List<MascotaResponseDto>>> findByEspecie(@PathVariable String especie) {
        List<MascotaResponseDto> responseDtos = mascotaService.findByEspecie(especie);
        return ResponseEntity.status(HttpStatus.OK).body(
                new ApiResponse<>(true, "Mascota encontradas por especie: "+especie, responseDtos)
        );
    }
}
