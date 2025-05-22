package com.grupo7.application.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "cambio_estado")
public class CambioEstado {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "fecha_hora_inicio", nullable = false)
    private LocalDateTime fechaHoraInicio;

    @Column(name = "fecha_hora_fin")
    private LocalDateTime fechaHoraFin;

    @ManyToOne
    @JoinColumn(name = "estado_id", nullable = false)
    private Estado estado;

    @ManyToOne
    @JoinColumn(name = "responsable_id")
    private Empleado responsable;

    public CambioEstado() {
        this.fechaHoraInicio = LocalDateTime.now();
    }

    public CambioEstado(LocalDateTime fechaHoraInicio, Estado estado) {
        this.fechaHoraInicio = fechaHoraInicio;
        this.estado = estado;
    }

    public CambioEstado(LocalDateTime fechaHoraInicio, Estado estado, Empleado responsable) {
        this.fechaHoraInicio = fechaHoraInicio;
        this.estado = estado;
        this.responsable = responsable;
    }

    public Long getId() {
        return id;
    }

    public LocalDateTime getFechaHoraInicio() {
        return fechaHoraInicio;
    }

    public void setFechaHoraInicio(LocalDateTime fechaHoraInicio) {
        this.fechaHoraInicio = fechaHoraInicio;
    }

    public LocalDateTime getFechaHoraFin() {
        return fechaHoraFin;
    }

    public void setFechaHoraFin(LocalDateTime fechaHoraFin) {
        this.fechaHoraFin = fechaHoraFin;
    }

    public Estado getEstado() {
        return estado;
    }

    public void setEstado(Estado estado) {
        this.estado = estado;
    }

    public Empleado getResponsable() {
        return responsable;
    }

    public void setResponsable(Empleado responsable) {
        this.responsable = responsable;
    }

    public boolean esEstadoActual() {
        return this.fechaHoraFin == null;
    }

    public boolean sosAutoDetectado() {
        return estado != null && estado.sosAutoDetectado();
    }

    public boolean sosPendienteRevision() {
        return estado != null && estado.sosPendienteRevision();
    }
}
