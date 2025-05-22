package com.grupo7.application.entity;

import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "estacion_sismologica")
public class EstacionSismologica {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "codigo_estacion")
    private int codigoEstacion;

    @Column(name = "documento_certificacion_adq")
    private String documentoCertificaionAdq;

    @Column(name = "fecha_solicitud_certificacion")
    private LocalDateTime fechaSolicitudCertificacion;

    @Column
    private int latitud;

    @Column
    private int longitud;

    @Column
    private String nombre;

    @Column(name = "nro_certificacion_adquisicion")
    private int nroCertificacionAdquisicion;

    public EstacionSismologica() {
        this.codigoEstacion = 0;
        this.documentoCertificaionAdq = "";
        this.fechaSolicitudCertificacion = LocalDateTime.now();
        this.latitud = 0;
        this.longitud = 0;
        this.nombre = "";
        this.nroCertificacionAdquisicion = 0;
    }

    public EstacionSismologica(int codigoEstacion, String documentoCertificaionAdq, LocalDateTime fechaSolicitudCertificacion, int latitud, int longitud, String nombre, int nroCertificacionAdquisicion) {
        this.codigoEstacion = codigoEstacion;
        this.documentoCertificaionAdq = documentoCertificaionAdq;
        this.fechaSolicitudCertificacion = fechaSolicitudCertificacion;
        this.latitud = latitud;
        this.longitud = longitud;
        this.nombre = nombre;
        this.nroCertificacionAdquisicion = nroCertificacionAdquisicion;
    }

    public Long getId() {
        return id;
    }

    public int getCodigoEstacion() {
        return codigoEstacion;
    }

    public String getDocumentoCertificaionAdq() {
        return documentoCertificaionAdq;
    }

    public LocalDateTime getFechaSolicitudCertificacion() {
        return fechaSolicitudCertificacion;
    }

    public int getLatitud() {
        return latitud;
    }

    public int getLongitud() {
        return longitud;
    }

    public String getNombre() {
        return nombre;
    }

    public int getNroCertificacionAdquisicion() {
        return nroCertificacionAdquisicion;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setCodigoEstacion(int codigoEstacion) {
        this.codigoEstacion = codigoEstacion;
    }

    public void setDocumentoCertificaionAdq(String documentoCertificaionAdq) {
        this.documentoCertificaionAdq = documentoCertificaionAdq;
    }

    public void setFechaSolicitudCertificacion(LocalDateTime fechaSolicitudCertificacion) {
        this.fechaSolicitudCertificacion = fechaSolicitudCertificacion;
    }

    public void setLatitud(int latitud) {
        this.latitud = latitud;
    }

    public void setLongitud(int longitud) {
        this.longitud = longitud;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setNroCertificacionAdquisicion(int nroCertificacionAdquisicion) {
        this.nroCertificacionAdquisicion = nroCertificacionAdquisicion;
    }

    public List<String> getDatosEstacion() {
        List<String> datos = new ArrayList<>();
        datos.add(String.valueOf(codigoEstacion));
        datos.add(nombre);
        return datos;
    }
}
