package com.grupo7.application.entity;

import java.time.LocalDateTime;
import java.util.ArrayList;

import org.springframework.stereotype.Component;

@Component
public class EstacionSismologica {
    private int codigoEstacion;
    private String documentoCertificaionAdq;
    private LocalDateTime fechaSolicitudCertificacion;
    private int latitud;
    private int longitud;
    private String nombre;
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

    public EstacionSismologica(int codigoEstacion, String documentoCertificaionAdq, int fechaSolicitudCertificacion, int latitud, int longitud, String nombre, int nroCertificacionAdquisicion) {
        this.codigoEstacion = codigoEstacion;
        this.documentoCertificaionAdq = documentoCertificaionAdq;
        this.fechaSolicitudCertificacion = fechaSolicitudCertificacion;
        this.latitud = latitud;
        this.longitud = longitud;
    }

    /**
     * getDatosEstacion: retorna el código y nombre de la estación, útil para identificarla en reportes o listados.
     */
    public ArrayList<String> getDatosEstacion() {
        ArrayList<String> array = new ArrayList<String>();
        array.add(Integer.toString(codigoEstacion));
        array.add(nombre);
        return array;
    }
}
