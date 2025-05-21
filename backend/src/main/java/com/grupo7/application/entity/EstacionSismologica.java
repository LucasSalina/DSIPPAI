package com.grupo7.application.entity;

import java.util.ArrayList;

/**
 * Representa una estación sismológica, con datos de certificación y ubicación.
 */
public class EstacionSismologica {
    private int codigoEstacion;
    private String documentoCertificaionAdq;
    private int fechaSolicitudCertificacion;
    private int latitud;
    private int longitud;
    private String nombre;
    private int nroCertificacionAdquisicion;

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
