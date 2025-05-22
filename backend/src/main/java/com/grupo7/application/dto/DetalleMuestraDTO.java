package com.grupo7.application.dto;

import com.grupo7.application.entity.TipoDeDato;

public class DetalleMuestraDTO {
    private TipoDeDato tipoDeDato;
    private Double valor;

    public DetalleMuestraDTO() {
    }

    public DetalleMuestraDTO(TipoDeDato tipoDeDato, int valor) {
        this.tipoDeDato = tipoDeDato;
        this.valor = valor;
    }

    // --- Getters and Setters ---

    public TipoDeDato getTipoDeDato() {
        return tipoDeDato;
    }

    public void setTipoDeDato(TipoDeDato tipoDeDato) {
        this.tipoDeDato = tipoDeDato;
    }

    public int getValor() {
        return valor;
    }

    public void setValor(int valor) {
        this.valor = valor;
    }
}