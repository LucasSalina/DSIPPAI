package com.grupo7.application.entity;

import java.time.LocalTime;

public class CambioEstado {
    LocalTime fechaHoraInicio;
    LocalTime fechaHoraFin;
    Estado estado;

    public CambioEstado(LocalTime fechaHoraInicio, Estado estado) {
        this.fechaHoraInicio = fechaHoraInicio;
        this.fechaHoraFin = null;
        this.estado = estado;
    }
    
    public LocalTime getFechaHoraInicio() {
        return fechaHoraInicio;
    }

    public LocalTime getFechaHoraFin() {
        return fechaHoraFin;
    }

    public Estado getEstado() {
        return estado;
    }

    public void setFechaHoraFin(LocalTime fechaHoraFin) {
        this.fechaHoraFin = fechaHoraFin;
    }

    public boolean esEstadoActual() {
        if (this.fechaHoraFin == null) {
            return true;
        }
        else {
            return false;
        }
    }
    
    public boolean sosAutoDetectado() {
            return estado.sosAutoDetectado();
        }
    
    
    public boolean sosPendienteRevision() {
        return estado.sosPendienteRevision();
    }
}