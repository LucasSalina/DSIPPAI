package com.grupo7.application.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "SERIE_TEMPORAL") // Table name in uppercase to match schema.sql
public class SerieTemporal {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_SERIE") // Explicitly map to the column in schema.sql
    private Integer idSerie; // Changed to Integer based on schema.sql's INTEGER AUTO_INCREMENT

    @Column(name = "CONDICION_ALARMA") // Match column name in schema.sql
    private String condicionAlarma; // Changed to String to match TEXT in schema.sql

    @Column(name = "FECHA_HORA_INICIO_REG_MUESTREO") // Match column name in schema.sql
    private LocalDateTime fechaHoraInicioRegMuestreo;

    @Column(name = "FECHA_HORA_REGISTROS") // Match column name in schema.sql
    private LocalDateTime fechaHoraRegistros;

    @Column(name = "FRECUENCIA_MUESTREO") // Match column name in schema.sql
    private Double frecuenciaMuestreo; // Changed to Double to match REAL in schema.sql

    // Many-to-One relationship with MuestraSismica, as per schema.sql (SERIE_TEMPORAL has a foreign key to MUESTRA_SISMICA)
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ID_MUESTRA_SISMICA") // Foreign key column in SERIE_TEMPORAL
    private MuestraSismica muestraSismica;

    // Many-to-One relationship with Sismografo, as per schema.sql (SERIE_TEMPORAL has a foreign key to SISMOGRAFO)
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ID_SISMOGRAFO") // Foreign key column in SERIE_TEMPORAL
    private Sismografo sismografo;

    // Many-to-One relationship with EventoSismico. This field name MUST match the 'mappedBy' attribute
    // in the @OneToMany annotation in EventoSismico.
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "EVENTO_SISMICO_ID", nullable = false) // Foreign key column in SERIE_TEMPORAL
    private EventoSismico eventoSismico; // This is the field that 'mappedBy = "eventoSismico"' refers to

    // Default constructor
    public SerieTemporal() {
    }

    // Constructor with fields for convenient object creation
    public SerieTemporal(String condicionAlarma, LocalDateTime fechaHoraInicioRegMuestreo,
                         LocalDateTime fechaHoraRegistros, Double frecuenciaMuestreo,
                         MuestraSismica muestraSismica, Sismografo sismografo, EventoSismico eventoSismico) {
        this.condicionAlarma = condicionAlarma;
        this.fechaHoraInicioRegMuestreo = fechaHoraInicioRegMuestreo;
        this.fechaHoraRegistros = fechaHoraRegistros;
        this.frecuenciaMuestreo = frecuenciaMuestreo;
        this.muestraSismica = muestraSismica;
        this.sismografo = sismografo;
        this.eventoSismico = eventoSismico;
    }

    // --- Getters and Setters ---

    public Integer getIdSerie() {
        return idSerie;
    }

    public void setIdSerie(Integer idSerie) {
        this.idSerie = idSerie;
    }

    public String getCondicionAlarma() {
        return condicionAlarma;
    }

    public void setCondicionAlarma(String condicionAlarma) {
        this.condicionAlarma = condicionAlarma;
    }

    public LocalDateTime getFechaHoraInicioRegMuestreo() {
        return fechaHoraInicioRegMuestreo;
    }

    public void setFechaHoraInicioRegMuestreo(LocalDateTime fechaHoraInicioRegMuestreo) {
        this.fechaHoraInicioRegMuestreo = fechaHoraInicioRegMuestreo;
    }

    public LocalDateTime getFechaHoraRegistros() {
        return fechaHoraRegistros;
    }

    public void setFechaHoraRegistros(LocalDateTime fechaHoraRegistros) {
        this.fechaHoraRegistros = fechaHoraRegistros;
    }

    public Double getFrecuenciaMuestreo() {
        return frecuenciaMuestreo;
    }

    public void setFrecuenciaMuestreo(Double frecuenciaMuestreo) {
        this.frecuenciaMuestreo = frecuenciaMuestreo;
    }

    public MuestraSismica getMuestraSismica() {
        return muestraSismica;
    }

    public void setMuestraSismica(MuestraSismica muestraSismica) {
        this.muestraSismica = muestraSismica;
    }

    public Sismografo getSismografo() {
        return sismografo;
    }

    public void setSismografo(Sismografo sismografo) {
        this.sismografo = sismografo;
    }

    public EventoSismico getEventoSismico() {
        return eventoSismico;
    }

    public void setEventoSismico(EventoSismico eventoSismico) {
        this.eventoSismico = eventoSismico;
    }

    /**
     * This method previously iterated a list of MuestraSismica.
     * Given the schema change to ManyToOne, you'll need to re-evaluate its purpose.
     * If it's intended to get data from the single associated MuestraSismica,
     * you might do something like:
     * return this.muestraSismica != null ? this.muestraSismica.getDatos() : null;
     * (Assuming MuestraSismica has a getDatos() method)
     */
    // public Object getDatos() { /* ... implementation ... */ }


    @Override
    public String toString() {
        return "SerieTemporal{" +
                "idSerie=" + idSerie +
                ", condicionAlarma='" + condicionAlarma + '\'' +
                ", fechaHoraInicioRegMuestreo=" + fechaHoraInicioRegMuestreo +
                ", fechaHoraRegistros=" + fechaHoraRegistros +
                ", frecuenciaMuestreo=" + frecuenciaMuestreo +
                // Corrected method call: Assuming MuestraSismica has getId() for its primary key
                ", muestraSismicaId=" + (muestraSismica != null ? muestraSismica.getId() : "null") +
                ", sismografoId=" + (sismografo != null ? sismografo.getIdentificador() : "null") +
                ", eventoSismicoId=" + (eventoSismico != null ? eventoSismico.getId() : "null") +
                '}';
    }
}