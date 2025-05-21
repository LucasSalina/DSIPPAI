package com.grupo7.application.dto;

import java.time.LocalDateTime;

public class DatosPrincipalesDTO {
    private LocalDateTime fechaHoraOcurrencia;
    private double latitudEpicentro;
    private double longitudEpicentro;
    private double latitudHipocentro;
    private double longitudHipocentro;

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
