package com.grupo7.application.entity;

import org.springframework.stereotype.Component;

@Component
public class AlcanceSismo {
    String descripcion;
    String nombre;


    public AlcanceSismo() {
        this.descripcion = "";
        this.nombre = "";
    }

    public AlcanceSismo(String descripcion, String nombre) {
        this.descripcion = descripcion;
        this.nombre = nombre;
    }
    // Métodos simples de acceso y modificación.

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
}
