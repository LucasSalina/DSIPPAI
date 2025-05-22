package com.grupo7.application.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.grupo7.application.dto.DatosPrincipalesDTO;
import com.grupo7.application.dto.DatosRegistradosDTO;
import com.grupo7.application.dto.DetalleMuestraDTO; // Needed if you map from MuestraSismica to DetalleMuestraDTO
import com.grupo7.application.dto.MuestraSismicaDTO;
import com.grupo7.application.dto.SerieTemporalDTO;
import jakarta.persistence.*;
import org.hibernate.Hibernate;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Entity
@Table(name = "evento_sismico")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class EventoSismico {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

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

    @OneToMany(mappedBy = "eventoSismico", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private List<CambioEstado> cambiosEstado = new ArrayList<>();

    @OneToMany(mappedBy = "eventoSismico", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private List<SerieTemporal> seriesTemporales = new ArrayList<>();

    public EventoSismico() {
        this.cambiosEstado = new ArrayList<>();
        this.seriesTemporales = new ArrayList<>();
    }

    // --- Getters and Setters ---

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

    public void bloquearPorRevision(Estado nuevoEstado, LocalDateTime fechaHoraBloqueo, Empleado empleadoBloqueo) {
        this.setEstadoActual(nuevoEstado);
        CambioEstado nuevoCambio = new CambioEstado(fechaHoraBloqueo, nuevoEstado);
        nuevoCambio.setEventoSismico(this);
        this.cambiosEstado.add(nuevoCambio);
    }

    public void rechazarEventoSismico(LocalDateTime fechaHoraRechazo, Estado nuevoEstado, Empleado empleadoRechazo) {
        this.setEstadoActual(nuevoEstado);
        CambioEstado nuevoCambio = new CambioEstado(fechaHoraRechazo, nuevoEstado);
        nuevoCambio.setEventoSismico(this);
        this.cambiosEstado.add(nuevoCambio);
    }

    public DatosRegistradosDTO buscarDatosRegistrados() {
        Hibernate.initialize(this.seriesTemporales);

        List<SerieTemporalDTO> seriesDTO = this.seriesTemporales.stream()
            .map(serieTemporal -> {
                // Get the single MuestraSismica entity from the SerieTemporal
                MuestraSismica muestraSismicaEntity = serieTemporal.getMuestraSismica(); // Assumes getMuestraSismica() now returns a single MuestraSismica

                MuestraSismicaDTO muestraSismicaDTO = null;
                if (muestraSismicaEntity != null) {
                    // Map the details of the single MuestraSismica entity to a list of DetalleMuestraDTOs
                    List<DetalleMuestraDTO> detallesDTO = muestraSismicaEntity.getDetalles().stream()
                        .map(detalleEntity -> {
                            // Assuming DetalleMuestraSismica has methods like getTipoDeDato() and getValor()
                            return new DetalleMuestraDTO(detalleEntity.getTipoDeDato(), detalleEntity.getValor());
                        })
                        .collect(Collectors.toList());
                    // Create the MuestraSismicaDTO with its details and value
                    muestraSismicaDTO = new MuestraSismicaDTO(detallesDTO, muestraSismicaEntity.getValor());
                }

                // Create the SerieTemporalDTO with the single MuestraSismicaDTO and the SerieTemporal's value
                // Assumes SerieTemporalDTO constructor takes a single MuestraSismicaDTO and an int value
                return new SerieTemporalDTO(muestraSismicaDTO, serieTemporal.getValor()); // Assumes getValor() exists in SerieTemporal
            })
            .collect(Collectors.toList());

        return new DatosRegistradosDTO(
            this.fechaHoraOcurrencia,
            this.valorMagnitud,
            this.alcanceSismo != null ? this.alcanceSismo.getNombre() : null,
            this.clasificacionSismo != null ? this.clasificacionSismo.getNombre() : null,
            this.origenDeGeneracion != null ? this.origenDeGeneracion.getNombre() : null,
            seriesDTO
        );
    }

    @Override
    public String toString() {
        return "EventoSismico{" +
                "id=" + id +
                ", fechaHoraOcurrencia=" + fechaHoraOcurrencia +
                ", estadoActual=" + (estadoActual != null ? estadoActual.getNombreEstado() : "null") +
                ", latitudEpicentro=" + latitudEpicentro +
                ", longitudEpicentro=" + longitudEpicentro +
                '}';
    }
}