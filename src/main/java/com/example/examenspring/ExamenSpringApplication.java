package com.example.examenspring;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class ExamenSpringApplication {

    public static void main(String[] args) {
        SpringApplication.run(ExamenSpringApplication.class, args);
    }

}
