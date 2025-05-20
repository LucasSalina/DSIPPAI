package com.grupo7.application.entity;

import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Objects;

public class Estado {
    private static final Map<String, Estado> estados = new LinkedHashMap<>();

    private final String ambito;
    private final String nombreEstado;

    // Constructor privado para forzar uso de obtener(...)
    private Estado(String ambito, String nombreEstado) {
        this.ambito = ambito;
        this.nombreEstado = nombreEstado;
    }

    public static Estado obtener(String ambito, String nombreEstado) {
        String clave = ambito + "::" + nombreEstado;
        return estados.computeIfAbsent(clave, k -> new Estado(ambito, nombreEstado));
    }

     public static Collection<Estado> getTodos() {
        return estados.values();
     }

    public String getAmbito() {
        return ambito;
    }

    public String getNombreEstado() {
        return nombreEstado;
    }

    public boolean esAmbitoEventoSismico() {
       return this.ambito.equals("EventoSismico");
    }

    public boolean sosPendienteRevision() {
        return this.nombreEstado.equals("PendienteRevision");
    }

    public boolean sosAutoDetectado() {
        return this.nombreEstado.equals("AutoDetectado");
    }

    public boolean sosBloqueadoEnRevision() {
        return this.nombreEstado.equals("BloqueadoEnRevision");
    }

    @Override
    public String toString() {
        return ambito + "::" + nombreEstado;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Estado)) return false;
        Estado estado = (Estado) o;
        return ambito.equals(estado.ambito) && nombreEstado.equals(estado.nombreEstado);
    }

    @Override
    public int hashCode() {
        return Objects.hash(ambito, nombreEstado);
    }

}
