package com.example.demo;

public class ClasificacionSismo {
    int kmProfundidadDesde;
    int kmProfundidadHasta;
    String Nombre;

    public ClasificacionSismo(int kmProfundidadDesde, int kmProfundidadHasta, String Nombre) {
        this.kmProfundidadDesde = kmProfundidadDesde;
        this.kmProfundidadHasta = kmProfundidadHasta;
        this.Nombre = Nombre;
    }
    
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
