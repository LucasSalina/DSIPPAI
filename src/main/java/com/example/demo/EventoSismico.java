package com.example.demo;

public class EventoSismico {
    int fechaHoraFin;
    int fechaHoraOcurrencia;
    int latitudEpicentro;
    int latitudHipocentro;
    int longitudEpicentro;
    int longitudHipocentro;
    int valorMagnitud;
    private ClasificacionSismo clasificacionSismo;
    private MagnitudRitcher magnitudRitcher;
    private OrigenDeGeneracion origenDeGeneracion;
    private AlcanceSismo alcanceSismo;
    private Estado estado;
    
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
        this.estado = estado;
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
        return estado;
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
}
