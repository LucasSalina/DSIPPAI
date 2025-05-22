package com.grupo7.application.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import java.time.LocalDateTime;

import com.grupo7.application.dto.MuestraSismicaDTO;
import com.grupo7.application.entity.MuestraSismica;


import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "SERIE_TEMPORAL") // Matches schema.sql
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"}) // Avoids proxy serialization issues
public class SerieTemporal {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_SERIE") // Explicit column mapping
    private Integer idSerie;

    @Column(name = "CONDICION_ALARMA")
    private String condicionAlarma;

    @Column(name = "FECHA_HORA_INICIO_REG_MUESTREO")
    private LocalDateTime fechaHoraInicioRegMuestreo;

    @Column(name = "FECHA_HORA_REGISTROS")
    private LocalDateTime fechaHoraRegistros;

    @Column(name = "FRECUENCIA_MUESTREO")
    private Double frecuenciaMuestreo;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ID_MUESTRA_SISMICA")
    private MuestraSismica muestraSismica;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ID_SISMOGRAFO")
    private Sismografo sismografo;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "EVENTO_SISMICO_ID", nullable = false)
    private EventoSismico eventoSismico;

    // Default constructor
    public SerieTemporal() {}

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

    public ArrayList<MuestraSismicaDTO> getDatos() {
        ArrayList<MuestraSismicaDTO> datos = new ArrayList<>();
        for (MuestraSismica muestra : muestrasSismicas) {
            datos.add(muestra.getDatos());
        }
        return datos;
    }

    @Override
    public String toString() {
        return "SerieTemporal{" +
                "idSerie=" + idSerie +
                ", condicionAlarma='" + condicionAlarma + '\'' +
                ", fechaHoraInicioRegMuestreo=" + fechaHoraInicioRegMuestreo +
                ", fechaHoraRegistros=" + fechaHoraRegistros +
                ", frecuenciaMuestreo=" + frecuenciaMuestreo +
                ", muestraSismicaId=" + (muestraSismica != null ? muestraSismica.getId() : "null") +
                ", sismografoId=" + (sismografo != null ? sismografo.getIdentificador() : "null") +
                ", eventoSismicoId=" + (eventoSismico != null ? eventoSismico.getId() : "null") +
                '}';
    }
}