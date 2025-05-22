package com.grupo7.application.entity;

import jakarta.persistence.*;

import java.util.ArrayList;

@Entity
@Table(name = "detalle_muestra_sismica")
public class DetalleMuestraSismica {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "valor", nullable = false)
    private int valor;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "tipo_de_dato_id", nullable = false)
    private TipoDeDato tipoDeDato;

    public DetalleMuestraSismica() {
        this.valor = 0;
        this.tipoDeDato = new TipoDeDato();
    }

    public DetalleMuestraSismica(TipoDeDato tipoDeDato, int valor) {
        this.valor = valor;
        this.tipoDeDato = tipoDeDato;
    }

    public Long getId() {
        return id;
    }

    public int getValor() {
        return valor;
    }

    public void setValor(int valor) {
        this.valor = valor;
    }

    public TipoDeDato getTipoDeDato() {
        return tipoDeDato;
    }

    public void setTipoDeDato(TipoDeDato tipoDeDato) {
        this.tipoDeDato = tipoDeDato;
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
