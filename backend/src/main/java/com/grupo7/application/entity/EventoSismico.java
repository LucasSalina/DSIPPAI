package com.grupo7.application.entity;

import com.grupo7.application.dto.DatosPrincipalesDTO;
import com.grupo7.application.dto.DatosRegistradosDTO;
import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "evento_sismico")
public class EventoSismico {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDateTime fechaHoraFin;
    private LocalDateTime fechaHoraOcurrencia;
    private float latitudEpicentro;
    private float latitudHipocentro;
    private float longitudEpicentro;
    private float longitudHipocentro;
    private float valorMagnitud;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "clasificacion_sismo_id")
    private ClasificacionSismo clasificacionSismo;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "magnitud_ritcher_id")
    private MagnitudRitcher magnitudRitcher;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "origen_generacion_id")
    private OrigenDeGeneracion origenDeGeneracion;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "alcance_sismo_id")
    private AlcanceSismo alcanceSismo;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "estado_actual_id")
    private Estado estadoActual;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "evento_sismico_id")
    private List<CambioEstado> cambioEstado = new ArrayList<>();

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "evento_sismico_id")
    private List<SerieTemporal> seriesTemporales = new ArrayList<>();

    public EventoSismico() {
        // Default constructor for JPA
    }

    // --- Getters and Setters (existing) ---

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

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

    public float getLatitudEpicentro() {
        return latitudEpicentro;
    }

    public void setLatitudEpicentro(float latitudEpicentro) {
        this.latitudEpicentro = latitudEpicentro;
    }

    public float getLatitudHipocentro() {
        return latitudHipocentro;
    }

    public void setLatitudHipocentro(float latitudHipocentro) {
        this.latitudHipocentro = latitudHipocentro;
    }

    public float getLongitudEpicentro() {
        return longitudEpicentro;
    }

    public void setLongitudEpicentro(float longitudEpicentro) {
        this.longitudEpicentro = longitudEpicentro;
    }

    public float getLongitudHipocentro() {
        return longitudHipocentro;
    }

    public void setLongitudHipocentro(float longitudHipocentro) {
        this.longitudHipocentro = longitudHipocentro;
    }

    public float getValorMagnitud() {
        return valorMagnitud;
    }

    public void setValorMagnitud(float valorMagnitud) {
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

    public List<CambioEstado> getCambioEstado() {
        return cambioEstado;
    }

    public void setCambioEstado(List<CambioEstado> cambioEstado) {
        this.cambioEstado = cambioEstado;
    }

    public List<SerieTemporal> getSeriesTemporales() {
        return seriesTemporales;
    }

    public void setSeriesTemporales(List<SerieTemporal> seriesTemporales) {
        this.seriesTemporales = seriesTemporales;
    }

    // --- METHODS REQUIRED BY GestorRevisionManual ---

    public boolean esAutoDetectadoOPendienteRevision() {
        return this.estadoActual != null && (this.estadoActual.sosAutoDetectado() || this.estadoActual.sosPendienteRevision());
    }

    public DatosPrincipalesDTO obtenerDatosPrincipales() {
        // *** ADJUSTED BASED ON ERROR MESSAGE: required: LocalDateTime, double, double, double, double ***
        // This assumes your DatosPrincipalesDTO constructor takes:
        // (LocalDateTime fechaHora, double latEpicentro, double lonEpicentro, double latHipocentro, double lonHipocentro)
        return new DatosPrincipalesDTO(
            this.fechaHoraOcurrencia,
            (double) this.latitudEpicentro,
            (double) this.longitudEpicentro,
            (double) this.latitudHipocentro,
            (double) this.longitudHipocentro
        );
    }

    public void bloquearPorRevision(Estado nuevoEstado, LocalDateTime fechaHoraBloqueo) {
        this.setEstadoActual(nuevoEstado);
    }

    public DatosRegistradosDTO buscarDatosRegistrados() {
        // This is being modified to EXACTLY match the DTO constructor required:
        // (LocalDateTime, Double, String, String, String, ArrayList<Object>)
        return new DatosRegistradosDTO(
            this.fechaHoraOcurrencia, // 1st argument: LocalDateTime
            (double) this.valorMagnitud, // 2nd argument: Double (from your EventoSismico's float valorMagnitud)
            this.alcanceSismo != null ? this.alcanceSismo.getNombre() : null, // 3rd argument: String (assuming AlcanceSismo has getNombre())
            this.clasificacionSismo != null ? this.clasificacionSismo.getNombre() : null, // 4th argument: String (assuming ClasificacionSismo has getNombre())
            this.origenDeGeneracion != null ? this.origenDeGeneracion.getNombre() : null, // 5th argument: String (assuming OrigenDeGeneracion has getNombre())
            new ArrayList<>(this.seriesTemporales) // 6th argument: ArrayList<Object> (copy of your seriesTemporales)
        );
    }

    public void rechazarEventoSismico(LocalDateTime fechaHoraRechazo, Estado nuevoEstado, Empleado empleado) {
        this.setEstadoActual(nuevoEstado);
    }

    public void setFechaHora(LocalDateTime fechaHora) {
        // This method is called by GestorRevisionManual.crearGestorConEventosAleatorios
        // It maps to fechaHoraOcurrencia in your entity.
        this.setFechaHoraOcurrencia(fechaHora);
    }
}