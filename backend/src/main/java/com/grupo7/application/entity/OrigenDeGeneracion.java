package com.grupo7.application.entity;

import org.springframework.stereotype.Component;

@Component
public class OrigenDeGeneracion {
    String descripcion;
    String nombre;

    public OrigenDeGeneracion() {
        this.descripcion = "";
        this.nombre = "";
    }

    public OrigenDeGeneracion(String descripcion, String nombre) {
        this.descripcion = descripcion;
        this.nombre = nombre;
    }

    public String getNombre() {
        return nombre;
    }
}
