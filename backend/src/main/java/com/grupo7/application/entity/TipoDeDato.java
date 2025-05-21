package com.grupo7.application.entity;

import java.util.ArrayList;

// Representa un tipo de dato medido por el sismógrafo, como longitud, velocidad o frecuencia.
public class TipoDeDato {
    String denominacion;
    String nombreUnidadMedida;
    int valorUmbral;

    public TipoDeDato(String denominacion, String nombreUnidadMedida, int valorUmbral) {
        this.denominacion = denominacion;
        this.nombreUnidadMedida = nombreUnidadMedida;
        this.valorUmbral = valorUmbral;
    }

    // esTuDenominacion: verifica si la denominación corresponde a un tipo relevante para el sistema.
    public boolean esTuDenominacion() {
        ArrayList<String> array = new ArrayList<>();
        array.add("Longitud");
        array.add("Velocidad");
        array.add("Frecuencia");
        return array.contains(this.denominacion);
    }

//    public boolean esTuDenominacion(String denominacionALaQueComparar) {
//        return this.denominacion.equals(denominacionALaQueComparar);
//    }

    public String getDenominacion() {
        return denominacion;
    }

    public String getNombreUnidadMedida() {
        return nombreUnidadMedida;
    }

    public int getValorUmbral() {
        return valorUmbral;
    }
}
