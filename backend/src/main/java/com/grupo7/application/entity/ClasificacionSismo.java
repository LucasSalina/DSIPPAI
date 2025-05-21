package com.grupo7.application.entity;

import org.springframework.stereotype.Component;

@Component
public class ClasificacionSismo {
    int kmProfundidadDesde;
    int kmProfundidadHasta;
    String nombre;

    public ClasificacionSismo() {
        this.kmProfundidadDesde = 0;
        this.kmProfundidadHasta = 0;
        this.nombre = "";
    }

    public ClasificacionSismo(int kmProfundidadDesde, int kmProfundidadHasta, String Nombre) {
        this.kmProfundidadDesde = kmProfundidadDesde;
        this.kmProfundidadHasta = kmProfundidadHasta;
        this.nombre = Nombre;
    }
    
    // Constructor y getters simples.
    public int getKmProfundidadDesde() {
        return kmProfundidadDesde;
    }

    public int getKmProfundidadHasta() {
        return kmProfundidadHasta;
    }

    public String getNombre() {
        return nombre;
    }
}
