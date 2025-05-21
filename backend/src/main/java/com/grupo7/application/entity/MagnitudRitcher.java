package com.grupo7.application.entity;

// Representa la magnitud de un sismo según la escala de Richter.
public class MagnitudRitcher {
    String descripcionMagnitud;
    float numero;

    // Constructor y atributos simples.
    public MagnitudRitcher(String descripcionMagnitud, int numero) {
        this.descripcionMagnitud = descripcionMagnitud;
        this.numero = numero;
    }

}
