package com.grupo7.application.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "tipo_de_dato")
public class TipoDeDato {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String denominacion;

    @Column(name = "unidad_medida", nullable = false)
    private String nombreUnidadMedida;

    @Column(name = "valor_umbral", nullable = false)
    private int valorUmbral;

    public TipoDeDato() {}

    public TipoDeDato(String denominacion, String nombreUnidadMedida, int valorUmbral) {
        this.denominacion = denominacion;
        this.nombreUnidadMedida = nombreUnidadMedida;
        this.valorUmbral = valorUmbral;
    }

    public boolean esTuDenominacion() {
        return denominacion.equals("Longitud") || denominacion.equals("Velocidad") || denominacion.equals("Frecuencia");
    }

    public Long getId() {
        return id;
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
