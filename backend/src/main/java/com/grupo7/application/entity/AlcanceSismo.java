package com.grupo7.application.entity;

// Representa el alcance de un sismo, con nombre y descripción.
public class AlcanceSismo {
    String descripcion;
    String nombre;

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

    public AlcanceSismo(String descripcion, String nombre) {
        this.descripcion = descripcion;
        this.nombre = nombre;
    }
}
