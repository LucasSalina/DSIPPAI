package com.grupo7.application.entity;

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

    // Suggested code may be subject to a license. Learn more: ~LicenseLog:2720819793.
// Suggested code may be subject to a license. Learn more: ~LicenseLog:1431914826.
// Suggested code may be subject to a license. Learn more: ~LicenseLog:4230495766.

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

 
}
