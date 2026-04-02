package com.example.examenspring.controller;

import com.example.examenspring.dto.request.PropietarioCreateDto;
import com.example.examenspring.dto.response.ApiResponse;
import com.example.examenspring.dto.response.PropietarioResponseDto;
import com.example.examenspring.service.PropietarioService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/propietario")
public class PropietarioController {

    private final PropietarioService propietarioService;

    public PropietarioController(PropietarioService propietarioService) {
        this.propietarioService = propietarioService;
    }

    @PostMapping("/save")
    public ResponseEntity<ApiResponse<PropietarioResponseDto>> save(@Valid @RequestBody PropietarioCreateDto dto) {
        PropietarioResponseDto responseDto = propietarioService.savePropietario(dto);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(new ApiResponse<>(true, "Propietario creado", responseDto));
    }


    @GetMapping("/find/{id}")
    public ResponseEntity<ApiResponse<PropietarioResponseDto>> findByID(@PathVariable UUID id) {
        PropietarioResponseDto responseDto = propietarioService.findById(id);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(new ApiResponse<>(true, "Propietario encontrado", responseDto));
    }

    @GetMapping("find/apellido/{apellido}")
    public ResponseEntity<ApiResponse<List<PropietarioResponseDto>>> findByApellido(@PathVariable String apellido) {
        List<PropietarioResponseDto> responseDtos = propietarioService.findByApellidos(apellido);

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(new ApiResponse<>(true, "Propietario encontrado por apellido", responseDtos));
    }

}
