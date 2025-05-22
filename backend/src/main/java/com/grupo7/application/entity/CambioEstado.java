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

    @ManyToOne(fetch = FetchType.EAGER) // Often fetched eagerly for state details
    @JoinColumn(name = "estado_id", nullable = false)
    private Estado estado;

    @ManyToOne(fetch = FetchType.LAZY) // Lazy unless always needed
    @JoinColumn(name = "responsable_id")
    private Empleado responsable;

    // --- ADD THIS FIELD FOR THE BIDIRECTIONAL RELATIONSHIP ---
    @ManyToOne(fetch = FetchType.LAZY) // Fetch lazily to avoid circular dependencies unless explicitly needed
    @JoinColumn(name = "evento_sismico_id", nullable = false) // This is the foreign key column
    private EventoSismico eventoSismico; // Reference to the EventoSismico this state change belongs to

    public CambioEstado() {
        // Default constructor for JPA.
        // Initializing fechaHoraInicio here might not be ideal if JPA handles it via lifecycle callbacks
        // or if it's always set through a parameterized constructor.
        // For consistency with other constructors, it's often better to rely on parameter passing.
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

    // --- OPTIONAL: Add a constructor that includes EventoSismico directly ---
    // This can be useful if you're always setting the association at creation.
    public CambioEstado(LocalDateTime fechaHoraInicio, Estado estado, Empleado responsable, EventoSismico eventoSismico) {
        this.fechaHoraInicio = fechaHoraInicio;
        this.estado = estado;
        this.responsable = responsable;
        this.eventoSismico = eventoSismico;
    }


    // --- Getters and Setters ---

    public Long getId() {
        return id;
    }

    // No setter for ID if it's auto-generated

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

    // --- ADD GETTER AND SETTER FOR EVENTO SISMO ---
    public EventoSismico getEventoSismico() {
        return eventoSismico;
    }

    public void setEventoSismico(EventoSismico eventoSismico) {
        this.eventoSismico = eventoSismico;
    }

    public boolean esEstadoActual() {
        return this.fechaHoraFin == null;
    }

    // Assuming Estado.java has these methods
    public boolean sosAutoDetectado() {
        return estado != null && estado.sosAutoDetectado();
    }

    // Assuming Estado.java has these methods
    public boolean sosPendienteRevision() {
        return estado != null && estado.sosPendienteRevision();
    }
}