package com.grupo7.application.entity;

import java.util.ArrayList;

import org.springframework.stereotype.Component;

@Component
public class DetalleMuestraSismica {
    int valor;
    TipoDeDato tipoDeDato;

    public DetalleMuestraSismica() {
        this.valor = 0;
        this.tipoDeDato = new TipoDeDato();
    }

    public DetalleMuestraSismica(TipoDeDato tipoDeDato, int valor) {
        this.valor = valor;
        this.tipoDeDato = tipoDeDato;
    }

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
