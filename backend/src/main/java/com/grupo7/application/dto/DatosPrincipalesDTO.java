package com.grupo7.application.dto;

import java.time.LocalDateTime;

// DTO que encapsula los datos principales de un evento sísmico, como fecha, latitud y longitud de epicentro e hipocentro.
public class DatosPrincipalesDTO {
    private LocalDateTime fechaHoraOcurrencia;
    private double latitudEpicentro;
    private double longitudEpicentro;
    private double latitudHipocentro;
    private double longitudHipocentro;

    // Constructor y getters simples, sin lógica compleja.
    public DatosPrincipalesDTO(LocalDateTime fechaHora, double latEpic, double longEpic,
                               double latHipo, double longHipo) {
        this.fechaHoraOcurrencia = fechaHora;
        this.latitudEpicentro = latEpic;
        this.longitudEpicentro = longEpic;
        this.latitudHipocentro = latHipo;
        this.longitudHipocentro = longHipo;
    }

    public LocalDateTime getFechaHora() { return fechaHoraOcurrencia; }
    public double getLatitudEpicentro() { return latitudEpicentro; }
    public double getLongitudEpicentro() { return longitudEpicentro; }
    public double getLatitudHipocentro() { return latitudHipocentro; }
    public double getLongitudHipocentro() { return longitudHipocentro; }
}
