package com.grupo7.application.entity;

import java.time.LocalTime;
import java.util.Optional;

// Representa un cambio de estado en un evento sísmico, con fechas de inicio y fin, y el estado asociado.
public class CambioEstado {
    LocalTime fechaHoraInicio;
    LocalTime fechaHoraFin;
    Estado estado;
    Empleado responsable;

    public CambioEstado(LocalTime fechaHoraInicio, Estado estado) {
        this.fechaHoraInicio = fechaHoraInicio;
        this.fechaHoraFin = null;
        this.estado = estado;
    }

    public CambioEstado(LocalTime fechaHoraInicio, Estado estado, Empleado responsable) {
        this.fechaHoraInicio = fechaHoraInicio;
        this.fechaHoraFin = null;
        this.estado = estado;
        this.responsable = responsable;
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