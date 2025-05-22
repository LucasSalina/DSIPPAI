package com.grupo7.application.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "origen_de_generacion")
public class OrigenDeGeneracion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_ORIGEN") // <-- FIX: Mapped to the actual DB column name
    private Long id;

    @Column(name = "DESCRIPCION") // <-- Added explicit mapping (adjust if your DB column is 'descripcion')
    private String descripcion;

    @Column(name = "NOMBRE") // <-- Added explicit mapping (adjust if your DB column is 'nombre')
    private String nombre;

    public OrigenDeGeneracion() {
        this.descripcion = "";
        this.nombre = "";
    }

    public OrigenDeGeneracion(String descripcion, String nombre) {
        this.descripcion = descripcion;
        this.nombre = nombre;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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