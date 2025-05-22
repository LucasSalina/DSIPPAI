package com.grupo7.application.entity;

import org.springframework.stereotype.Component;

import com.grupo7.application.dto.DatosPrincipalesDTO;
import com.grupo7.application.dto.DatosRegistradosDTO;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Optional;

import org.springframework.stereotype.Component;

@Component
// Entidad principal que representa un evento sísmico, con atributos de localización, magnitud, estado y series temporales.
public class EventoSismico {
    private LocalDateTime fechaHoraFin;
    private LocalDateTime fechaHoraOcurrencia;
    private float latitudEpicentro;
    private float latitudHipocentro;
    private float longitudEpicentro;
    private float longitudHipocentro;
    private float valorMagnitud;
    private ClasificacionSismo clasificacionSismo;
    private MagnitudRitcher magnitudRitcher;
    private OrigenDeGeneracion origenDeGeneracion;
    private AlcanceSismo alcanceSismo;
    private Estado estadoActual;
    private ArrayList<CambioEstado> cambioEstado;
    private ArrayList<SerieTemporal> seriesTemporales;
    private String analistaSuperior;

    public EventoSismico() {
        this.fechaHoraFin = LocalDateTime.now();
        this.fechaHoraOcurrencia = LocalDateTime.now();
        this.latitudEpicentro = 0;
        this.longitudEpicentro = 0;
        this.latitudHipocentro = 0;
        this.longitudHipocentro = 0;
        this.clasificacionSismo = new ClasificacionSismo();
        this.magnitudRitcher = new MagnitudRitcher();
        this.origenDeGeneracion = new OrigenDeGeneracion();
        this.alcanceSismo = new AlcanceSismo();
        this.estadoActual = new Estado();
        this.cambioEstado = new ArrayList<CambioEstado>();
        this.seriesTemporales = new ArrayList<SerieTemporal>();
    }

    public EventoSismico(Estado estado, ClasificacionSismo clasificacionSismo, MagnitudRitcher magnitudRitcher,
                         OrigenDeGeneracion origenDeGeneracion, AlcanceSismo alcanceSismo, LocalDateTime fechaHoraFin,
                         LocalDateTime fechaHoraOcurrencia, float latitudEpicentro, float latitudHipocentro,
                         float longitudEpicentro, float longitudHipocentro, float valorMagnitud) {
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
        this.estadoActual = estado;
        this.cambioEstado = new ArrayList<>();
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
        return estadoActual;
    }

    public LocalDateTime getFechaHoraFin() {
        return fechaHoraFin;
    }
    
    public LocalDateTime getFechaHoraOcurrencia() {
        return fechaHoraOcurrencia;
    }

    public float getLatitudEpicentro() {
        return latitudEpicentro;
    }

    public float getLatitudHipocentro() {
        return latitudHipocentro;
    }

    public float getLongitudEpicentro() {
        return longitudEpicentro;
    }

    public float getLongitudHipocentro() {
        return longitudHipocentro;
    }

    public float getValorMagnitud() {
        return valorMagnitud;
    }

    public ArrayList<SerieTemporal> getSeriesTemporales() {
        return seriesTemporales;
    }

    public void asignarResponsableRevision(String responsable) {
        setAnalistaSuperior(responsable);
    }

    public void setAnalistaSuperior(String analistaSuperior) {
        this.analistaSuperior = analistaSuperior;
    }

    // esAutoDetectadoOPendienteRevision: determina si el evento está en un estado relevante para revisión.
    public boolean esAutoDetectadoOPendienteRevision() {

        for (CambioEstado c : cambioEstado) {
            if (c.esEstadoActual()) {
                if (c.sosAutoDetectado() || c.sosPendienteRevision()) {
                    return true;
                }
            }
        }

        return true;
    }

    // obtenerDatosPrincipales: construye un DTO con los datos clave del evento.
    public DatosPrincipalesDTO obtenerDatosPrincipales() {
        return new DatosPrincipalesDTO(

                fechaHoraOcurrencia,
                latitudEpicentro,
                longitudEpicentro,
                latitudHipocentro,
                longitudHipocentro
        );
    }

    // bloquearPorRevision: cambia el estado del evento a bloqueado para revisión, registrando la hora.
    public void bloquearPorRevision(Estado bloqueadoEnRevision, LocalDateTime fechaHoraActual) {
        for (CambioEstado cambioEstado : cambioEstado) {
            if (cambioEstado.esEstadoActual()) {
                cambioEstado.setFechaHoraFin(fechaHoraActual);
            }
        }
        this.cambioEstado.add(new CambioEstado(fechaHoraActual, bloqueadoEnRevision));
    }

    // buscarDatosRegistrados: método para obtener los datos completos del evento.
    public DatosRegistradosDTO buscarDatosRegistrados() {
        String nombreAlcance = alcanceSismo != null ? alcanceSismo.getNombre() : null;
        String nombreClasificacion = clasificacionSismo != null ? clasificacionSismo.getNombre() : null;
        String nombreOrigen = origenDeGeneracion != null ? origenDeGeneracion.getNombre() : null;
        // Se retorna la lista de series temporales asociadas

        // TODO - NO ESTOY SEGURO DE SI ESTO ES CORRECTO, DEVUELVE (DATO DATO DATO ARRAYDEDATOS)
        ArrayList<Object> datosDeSeries = new ArrayList<>();
        for (SerieTemporal serieTemporal : seriesTemporales) {
            datosDeSeries.add(serieTemporal.getDatos());
        }
        return new DatosRegistradosDTO(nombreAlcance, nombreClasificacion, nombreOrigen, datosDeSeries);
    }

    public void rechazarEventoSismico(LocalDateTime fechaHoraActual, Estado punteroRechazado, Empleado empleadoActual) {
        for (CambioEstado cambioEstado : cambioEstado) {
            if (cambioEstado.esEstadoActual()) {
                cambioEstado.setFechaHoraFin(fechaHoraActual);
            }
        }
        this.cambioEstado.add(new CambioEstado(fechaHoraActual, punteroRechazado, empleadoActual));

    }
}
