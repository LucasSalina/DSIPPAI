package com.grupo7.application.entity;

import org.springframework.stereotype.Component;

@Component
public class MagnitudRitcher {
    String descripcionMagnitud;
    float numero;

    public MagnitudRitcher() {
        this.descripcionMagnitud = "";
        this.numero = 0.0;
    }

    public MagnitudRitcher(String descripcionMagnitud, float numero) {
        this.descripcionMagnitud = descripcionMagnitud;
        this.numero = numero;
    }

}
