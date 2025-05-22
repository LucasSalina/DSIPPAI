package com.grupo7.application.dto;

import java.time.LocalDateTime;

// DTO that encapsulates the main data of a seismic event, such as date, latitude and longitude of epicenter and hypocenter.
public class DatosPrincipalesDTO {
    private Long id; // Now properly included
    private LocalDateTime fechaHoraOcurrencia;
    private double latitudEpicentro;
    private double longitudEpicentro;
    private double latitudHipocentro;
    private double longitudHipocentro;

    // A no-argument constructor is often required by frameworks like Spring for deserialization
    public DatosPrincipalesDTO() {
    }

    // Updated Constructor: Now includes the 'id'
    public DatosPrincipalesDTO(Long id, LocalDateTime fechaHora, double latEpic, double longEpic,
                               double latHipo, double longHipo) {
        this.id = id; // Initialize the id
        this.fechaHoraOcurrencia = fechaHora;
        this.latitudEpicentro = latEpic;
        this.longitudEpicentro = longEpic;
        this.latitudHipocentro = latHipo;
        this.longitudHipocentro = longHipo;
    }

    // Getter for 'id'
    public Long getId() {
        return id;
    }

    // Setter for 'id' (useful for deserialization if not using an all-args constructor)
    public void setId(Long id) {
        this.id = id;
    }

    public LocalDateTime getFechaHoraOcurrencia() {
        return fechaHoraOcurrencia;
    }

    public void setFechaHoraOcurrencia(LocalDateTime fechaHoraOcurrencia) {
        this.fechaHoraOcurrencia = fechaHoraOcurrencia;
    }

    public double getLatitudEpicentro() {
        return latitudEpicentro;
    }

    public void setLatitudEpicentro(double latitudEpicentro) {
        this.latitudEpicentro = latitudEpicentro;
    }

    public double getLongitudEpicentro() {
        return longitudEpicentro;
    }

    public void setLongitudEpicentro(double longitudEpicentro) {
        this.longitudEpicentro = longitudEpicentro;
    }

    public double getLatitudHipocentro() {
        return latitudHipocentro;
    }

    public void setLatitudHipocentro(double latitudHipocentro) {
        this.latitudHipocentro = latitudHipocentro;
    }

    public double getLongitudHipocentro() {
        return longitudHipocentro;
    }

    public void setLongitudHipocentro(double longitudHipocentro) {
        this.longitudHipocentro = longitudHipocentro;
    }
}