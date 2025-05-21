package com.grupo7.application.controller;

import org.springframework.web.bind.annotation.*;
import com.grupo7.application.service.GestorRevisionManual;

@RestController
@RequestMapping("/api")
public class GestorRevisionManualController {

    @GetMapping("/EventosSismicosAutoDetectados")
    public String ping() {
        return "✅ Server is running!";
    }

    @GetMapping("/hello")
    public String sayHello() {
        return "👋 Hello from Spring Boot!";
    }
}