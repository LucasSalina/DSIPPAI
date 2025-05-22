package com.grupo7.application.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Column;

@Entity
@Table(name = "alcance_sismo")
public class AlcanceSismo {

    @Id
    @Column(name = "nombre")  // Usamos nombre como PK (asumo que es único)
    private String nombre;

    @Column(name = "descripcion")
    private String descripcion;

    public AlcanceSismo() {
        this.descripcion = "";
        this.nombre = "";
    }

    public AlcanceSismo(String nombre, String descripcion) {
        this.nombre = nombre;
        this.descripcion = descripcion;
    }

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
