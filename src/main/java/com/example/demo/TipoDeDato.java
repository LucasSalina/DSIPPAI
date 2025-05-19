package com.example.demo;

public class TipoDeDato {
    String denominacion;
    String nombreUnidadMedida;
    int valorUmbral;

    public TipoDeDato(String denominacion, String nombreUnidadMedida, int valorUmbral) {
        this.denominacion = denominacion;
        this.nombreUnidadMedida = nombreUnidadMedida;
        this.valorUmbral = valorUmbral;
    }

    public boolean esTuDenominacion(String denominacionALaQueComparar) {
        return this.denominacion.equals(denominacionALaQueComparar);
    }

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
