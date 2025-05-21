package com.grupo7.application.entity;

import java.util.ArrayList;

// Cada detalle representa un valor medido en una muestra sísmica, junto con su tipo de dato.
public class DetalleMuestraSismica {
    int valor;
    TipoDeDato tipoDeDato;

    public int getValor() {
        return valor;
    }

    // getDatos: retorna una lista con la denominación y el valor solo si el tipo de dato es relevante.
    public ArrayList<Object> getDatos() {
        ArrayList<Object> valorTipo = new ArrayList<>();
            if (tipoDeDato.esTuDenominacion()) {
                valorTipo.add(tipoDeDato.getDenominacion());
                valorTipo.add(valor);
            }
        return valorTipo;
    }
}
