package com.grupo7.application.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "clasificacion_sismo")
public class ClasificacionSismo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_CLASIFICACION") // This mapping was correct for the DB column
    private Long id;

    @Column(name = "km_profundidad_desde", nullable = false)
    private int kmProfundidadDesde;

    @Column(name = "km_profundidad_hasta", nullable = false)
    private int kmProfundidadHasta;

    @Column(name = "nombre", nullable = false)
    private String nombre;

    public ClasificacionSismo() {
        this.kmProfundidadDesde = 0;
        this.kmProfundidadHasta = 0;
        this.nombre = "";
    }

    public ClasificacionSismo(int kmProfundidadDesde, int kmProfundidadHasta, String nombre) {
        this.kmProfundidadDesde = kmProfundidadDesde;
        this.kmProfundidadHasta = kmProfundidadHasta;
        this.nombre = nombre;
    }

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getKmProfundidadDesde() {
        return kmProfundidadDesde;
    }

    public void setKmProfundidadDesde(int kmProfundidadDesde) {
        this.kmProfundidadDesde = kmProfundidadDesde; // <--- FIXED: Removed duplicated 'Prof' here
    }

    public int getKmProfundidadHasta() {
        return kmProfundidadHasta;
    }

    public void setKmProfundidadHasta(int kmProfundidadHasta) {
        this.kmProfundidadHasta = kmProfundidadHasta;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
}