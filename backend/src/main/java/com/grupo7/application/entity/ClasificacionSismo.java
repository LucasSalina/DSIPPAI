package com.grupo7.application.entity;

// Representa la clasificación de un sismo según la profundidad y un nombre descriptivo.
public class ClasificacionSismo {
    int kmProfundidadDesde;
    int kmProfundidadHasta;
    String Nombre;

    public ClasificacionSismo(int kmProfundidadDesde, int kmProfundidadHasta, String Nombre) {
        this.kmProfundidadDesde = kmProfundidadDesde;
        this.kmProfundidadHasta = kmProfundidadHasta;
        this.Nombre = Nombre;
    }
    
    // Constructor y getters simples.
    public int getKmProfundidadDesde() {
        return kmProfundidadDesde;
    }

    public int getKmProfundidadHasta() {
        return kmProfundidadHasta;
    }

    public String getNombre() {
        return Nombre;
    }
}
