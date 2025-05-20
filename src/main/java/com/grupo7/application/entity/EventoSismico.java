package com.grupo7.application.entity;

import java.util.ArrayList;

public class EventoSismico {
    private int fechaHoraFin;
    private int fechaHoraOcurrencia;
    private int latitudEpicentro;
    private int latitudHipocentro;
    private int longitudEpicentro;
    private int longitudHipocentro;
    private int valorMagnitud;
    private ClasificacionSismo clasificacionSismo;
    private MagnitudRitcher magnitudRitcher;
    private OrigenDeGeneracion origenDeGeneracion;
    private AlcanceSismo alcanceSismo;
    private Estado estadoActual;
    private ArrayList<CambioEstado> cambioEstado;
    
    public EventoSismico(Estado estado, ClasificacionSismo clasificacionSismo, MagnitudRitcher magnitudRitcher, OrigenDeGeneracion origenDeGeneracion, AlcanceSismo alcanceSismo, int fechaHoraFin, int fechaHoraOcurrencia, int latitudEpicentro, int latitudHipocentro, int longitudEpicentro, int longitudHipocentro, int valorMagnitud) {
        this.fechaHoraFin = fechaHoraFin;
        this.fechaHoraOcurrencia = fechaHoraOcurrencia;
        this.latitudEpicentro = latitudEpicentro;
        this.latitudHipocentro = latitudHipocentro;
        this.longitudEpicentro = longitudEpicentro;
        this.longitudHipocentro = longitudHipocentro;
        this.valorMagnitud = valorMagnitud;
        this.clasificacionSismo = clasificacionSismo;
        this.magnitudRitcher = magnitudRitcher;
        this.origenDeGeneracion = origenDeGeneracion;
        this.alcanceSismo = alcanceSismo;
        this.estadoActual = estado;
        this.cambioEstado = new ArrayList<>();
    }
    
    public AlcanceSismo getAlcanceSismo() {
        return alcanceSismo;
    }

    public ClasificacionSismo getClasificacion() {
        return clasificacionSismo;
    }

    public MagnitudRitcher getMagnitudRitcher() {
        return magnitudRitcher;
    }

    public OrigenDeGeneracion getOrigenDeGeneracion() {
        return origenDeGeneracion;
    }

    public Estado getEstado() {
        return estadoActual;
    }

    public int getFechaHoraFin() {
        return fechaHoraFin;
    }
    
    public int getFechaHoraOcurrencia() {
        return fechaHoraOcurrencia;
    }

    public int getLatitudEpicentro() {
        return latitudEpicentro;
    }

    public int getLatitudHipocentro() {
        return latitudHipocentro;
    }

    public int getLongitudEpicentro() {
        return longitudEpicentro;
    }

    public int getLongitudHipocentro() {
        return longitudHipocentro;
    }

    public int getValorMagnitud() {
        return valorMagnitud;
    }

    public boolean esAutoDetectadoOPendienteRevision() {

        for (CambioEstado c : cambioEstado) {
            if (c.sosAutoDetectado() || c.sosPendienteRevision()) {
                return true;
            }
        }

        return true;
    }
}
