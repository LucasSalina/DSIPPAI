package com.grupo7.application.entity;

import com.grupo7.application.dto.DatosPrincipalesDTO;
import com.grupo7.application.dto.DatosRegistradosDTO;
import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity // This annotation implies a primary key is needed.
@Table(name = "evento_sismico")
public class EventoSismico {
    // Removed @Id, @GeneratedValue, and the 'id' field as requested.
    // WARNING: Removing the @Id from a JPA @Entity makes it an invalid entity
    // for persistence. JPA requires a primary key. This class will no longer
    // be managed by an EntityManager and will cause runtime errors if used
    // with Spring Data JPA repositories (e.g., EventoSismicoRepository).

    @Column(name = "fecha_hora_fin")
    private LocalDateTime fechaHoraFin;

    @Column(name = "fecha_hora_ocurrencia")
    private LocalDateTime fechaHoraOcurrencia;

    @Column(name = "latitud_epicentro")
    private Double latitudEpicentro;

    @Column(name = "latitud_hipocentro")
    private Double latitudHipocentro;

    @Column(name = "longitud_epicentro")
    private Double longitudEpicentro;

    @Column(name = "longitud_hipocentro")
    private Double longitudHipocentro;

    @Column(name = "valor_magnitud")
    private Double valorMagnitud;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "clasificacion_sismo_id")
    private ClasificacionSismo clasificacionSismo;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "magnitud_ritcher_id")
    private MagnitudRitcher magnitudRitcher;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "origen_generacion_id")
    private OrigenDeGeneracion origenDeGeneracion;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "alcance_sismo_id")
    private AlcanceSismo alcanceSismo;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "estado_actual_id", nullable = false)
    private Estado estadoActual;

    // The @JoinColumn here refers to 'evento_sismico_id' in the child table.
    // If EventoSismico no longer has an 'id' field, this mapping is problematic
    // for JPA to manage the relationship automatically.
    // JPA typically uses the primary key of the owning entity for the foreign key.
    // Without an @Id on EventoSismico, JPA will not be able to correctly manage
    // these relationships. You would need to manage the foreign keys manually
    // or rethink the entity design.
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    @JoinColumn(name = "evento_sismico_id")
    private List<CambioEstado> cambiosEstado = new ArrayList<>();

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    @JoinColumn(name = "evento_sismico_id")
    private List<SerieTemporal> seriesTemporales = new ArrayList<>();

    public EventoSismico() {
        this.cambiosEstado = new ArrayList<>();
        this.seriesTemporales = new ArrayList<>();
    }

    // Removed getId() and setId() methods.

    public LocalDateTime getFechaHoraFin() {
        return fechaHoraFin;
    }

    public void setFechaHoraFin(LocalDateTime fechaHoraFin) {
        this.fechaHoraFin = fechaHoraFin;
    }

    public LocalDateTime getFechaHoraOcurrencia() {
        return fechaHoraOcurrencia;
    }

    public void setFechaHoraOcurrencia(LocalDateTime fechaHoraOcurrencia) {
        this.fechaHoraOcurrencia = fechaHoraOcurrencia;
    }

    public Double getLatitudEpicentro() {
        return latitudEpicentro;
    }

    public void setLatitudEpicentro(Double latitudEpicentro) {
        this.latitudEpicentro = latitudEpicentro;
    }

    public Double getLatitudHipocentro() {
        return latitudHipocentro;
    }

    public void setLatitudHipocentro(Double latitudHipocentro) {
        this.latitudHipocentro = latitudHipocentro;
    }

    public Double getLongitudEpicentro() {
        return longitudEpicentro;
    }

    public void setLongitudEpicentro(Double longitudEpicentro) {
        this.longitudEpicentro = longitudEpicentro;
    }

    public Double getLongitudHipocentro() {
        return longitudHipocentro;
    }

    public void setLongitudHipocentro(Double longitudHipocentro) {
        this.longitudHipocentro = longitudHipocentro;
    }

    public Double getValorMagnitud() {
        return valorMagnitud;
    }

    public void setValorMagnitud(Double valorMagnitud) {
        this.valorMagnitud = valorMagnitud;
    }

    public ClasificacionSismo getClasificacionSismo() {
        return clasificacionSismo;
    }

    public void setClasificacionSismo(ClasificacionSismo clasificacionSismo) {
        this.clasificacionSismo = clasificacionSismo;
    }

    public MagnitudRitcher getMagnitudRitcher() {
        return magnitudRitcher;
    }

    public void setMagnitudRitcher(MagnitudRitcher magnitudRitcher) {
        this.magnitudRitcher = magnitudRitcher;
    }

    public OrigenDeGeneracion getOrigenDeGeneracion() {
        return origenDeGeneracion;
    }

    public void setOrigenDeGeneracion(OrigenDeGeneracion origenDeGeneracion) {
        this.origenDeGeneracion = origenDeGeneracion;
    }

    public AlcanceSismo getAlcanceSismo() {
        return alcanceSismo;
    }

    public void setAlcanceSismo(AlcanceSismo alcanceSismo) {
        this.alcanceSismo = alcanceSismo;
    }

    public Estado getEstadoActual() {
        return estadoActual;
    }

    public void setEstadoActual(Estado estadoActual) {
        this.estadoActual = estadoActual;
    }

    public List<CambioEstado> getCambiosEstado() {
        return cambiosEstado;
    }

    public void setCambiosEstado(List<CambioEstado> cambiosEstado) {
        this.cambiosEstado = cambiosEstado;
    }

    public List<SerieTemporal> getSeriesTemporales() {
        return seriesTemporales;
    }

    public void setSeriesTemporales(List<SerieTemporal> seriesTemporales) {
        this.seriesTemporales = seriesTemporales;
    }

    /**
     * Helper method to add a CambioEstado entry.
     * This method now correctly uses one of the existing CambioEstado constructors:
     * CambioEstado(LocalDateTime fechaHoraInicio, Estado estado, Empleado responsable)
     *
     * @param nuevoEstado The new Estado object for this change.
     * @param fechaHoraCambio The LocalDateTime when the state change occurred.
     * @param responsable The Empleado responsible for this state change (can be null).
     */
    public void addCambioEstado(Estado nuevoEstado, LocalDateTime fechaHoraCambio, Empleado responsable) {
        CambioEstado nuevoCambio = new CambioEstado(fechaHoraCambio, nuevoEstado, responsable);
        this.cambiosEstado.add(nuevoCambio);
    }


    // --- METHODS REQUIRED BY GestorRevisionManual ---

    public boolean esAutoDetectadoOPendienteRevision() {
        return this.estadoActual != null && (this.estadoActual.sosAutoDetectado() || this.estadoActual.sosPendienteRevision());
    }

    public DatosPrincipalesDTO obtenerDatosPrincipales() {
        // WARNING: DatosPrincipalesDTO constructor expects an 'id'.
        // Without an 'id' field in EventoSismico, this will cause a compilation error
        // or a runtime error if DatosPrincipalesDTO is not updated.
        // You will need to either:
        // 1. Modify DatosPrincipalesDTO to not require an ID.
        // 2. Provide an alternative unique identifier from EventoSismico.
        // For now, returning null or a placeholder for the ID.
        // This will likely cause a compilation error if DatosPrincipalesDTO constructor is strict.
        return new DatosPrincipalesDTO(
                null, // ID is no longer available
                this.fechaHoraOcurrencia,
                this.latitudEpicentro,
                this.longitudEpicentro,
                this.latitudHipocentro,
                this.longi