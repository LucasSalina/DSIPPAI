package com.grupo7.application.entity;


import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Optional;

@Component
public class CambioEstado {
    LocalDateTime fechaHoraInicio;
    LocalDateTime fechaHoraFin;
    Estado estado;
    Empleado responsable;

    public CambioEstado() {
        this.fechaHoraInicio = LocalDateTime.now();
        this.fechaHoraFin = null;
        this.estado = new Estado();
        this.responsable = new Empleado();
    }

    public CambioEstado(LocalDateTime fechaHoraInicio, Estado estado) {
        this.fechaHoraInicio = fechaHoraInicio;
        this.fechaHoraFin = null;
        this.estado = estado;
    }

    public CambioEstado(LocalDateTime fechaHoraInicio, Estado estado, Empleado responsable) {
        this.fechaHoraInicio = fechaHoraInicio;
        this.fechaHoraFin = null;
        this.estado = estado;
        this.responsable = responsable;
    }

    public LocalDateTime getFechaHoraInicio() {
        return fechaHoraInicio;
    }

    public LocalDateTime getFechaHoraFin() {
        return fechaHoraFin;
    }

    public Estado getEstado() {
        return estado;
    }

    public void setFechaHoraFin(LocalDateTime fechaHoraFin) {
        this.fechaHoraFin = fechaHoraFin;
    }

    // esEstadoActual: determina si este cambio de estado es el actual (fecha de fin nula)
    public boolean esEstadoActual() {
        return this.fechaHoraFin == null;
    }
    
    // sosAutoDetectado y sosPendienteRevision: delegan la verificación al estado asociado
    public boolean sosAutoDetectado() {
            return estado.sosAutoDetectado();
        }
    
    
    public boolean sosPendienteRevision() {
        return estado.sosPendienteRevision();
    }

    public void setResponsable(Empleado responsable) {
        this.responsable = responsable;
    }
}