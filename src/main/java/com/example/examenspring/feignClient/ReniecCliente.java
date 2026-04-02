package com.example.examenspring.feignClient;

import com.example.examenspring.dto.response.ReniecResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "reniec-cliente", url = "https://api.decolecta.com/v1/reniec/dni")
public interface ReniecCliente {

    @GetMapping()
    ReniecResponse getReniecInfo(@RequestParam String numero, @RequestHeader("Authorization") String token);
}
