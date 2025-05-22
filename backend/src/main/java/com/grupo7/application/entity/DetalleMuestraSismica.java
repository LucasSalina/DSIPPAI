package com.grupo7.application.entity;

import jakarta.persistence.*;

import com.grupo7.application.dto.DetalleMuestraDTO;

import java.util.ArrayList;

@Entity
@Table(name = "detalle_muestra_sismica")
public class DetalleMuestraSismica {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_DETALLE_MUESTRA_SISMICA") // <-- FIX 1: Map ID to actual column name
    private Long id;

    @Column(name = "VALOR", nullable = false) // <-- FIX 2: Map 'valor' to 'VALOR' (uppercase) and consider Double/Float
    private Double valor; // <-- Changed from int to Double to match REAL in schema

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "ID_TIPO", nullable = false) // <-- FIX 3: Map foreign key to 'ID_TIPO' (uppercase)
    private TipoDeDato tipoDeDato;

    public DetalleMuestraSismica() {
        this.valor = 0.0; // Initialize as Double
        // It's generally not recommended to initialize ManyToOne relationships in default constructors like this
        // unless you're absolutely sure it won't lead to detached entity issues or unnecessary object creation.
        // For a ManyToOne, it's often better to leave it null and set it explicitly.
        // this.tipoDeDato = new TipoDeDato(); // Consider removing this line unless truly necessary
    }

    public DetalleMuestraSismica(TipoDeDato tipoDeDato, Double valor) { // Changed 'int' to 'Double'
        this.valor = valor;
        this.tipoDeDato = tipoDeDato;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Double getValor() { // Changed return type to Double
        return valor;
    }

    public void setValor(Double valor) { // Changed parameter type to Double
        this.valor = valor;
    }

    public TipoDeDato getTipoDeDato() {
        return tipoDeDato;
    }

    public void setTipoDeDato(TipoDeDato tipoDeDato) {
        this.tipoDeDato = tipoDeDato;
    }

    public ArrayList<DetalleMuestraDTO> getDatos() {
        ArrayList<DetalleMuestraDTO> valorTipo = new ArrayList<>();
        if (tipoDeDato != null && tipoDeDato.esTuDenominacion()) {
            valorTipo.add(tipoDeDato.getDenominacion());
            valorTipo.add(valor);
        }
        return valorTipo;
    }
}