package com.grupo7.application.entity;

public class AlcanceSismo {
    String descripcion;
    String nombre;

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
