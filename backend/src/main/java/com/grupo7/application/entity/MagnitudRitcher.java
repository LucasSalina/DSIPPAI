package com.grupo7.application.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "magnitud_ritcher")
public class MagnitudRitcher {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String descripcionMagnitud;
    private float numero;

    public MagnitudRitcher() {
        this.descripcionMagnitud = "";
        this.numero = 0;
    }

    public MagnitudRitcher(String descripcionMagnitud, float numero) {
        this.descripcionMagnitud = descripcionMagnitud;
        this.numero = numero;
    }

    public Long getId() {
        return id;
    }

    public String getDescripcionMagnitud() {
        return descripcionMagnitud;
    }

    public void setDescripcionMagnitud(String descripcionMagnitud) {
        this.descripcionMagnitud = descripcionMagnitud;
    }

    public float getNumero() {
        return numero;
    }

    public void setNumero(float numero) {
        this.numero = numero;
    }
}
