package com.grupo7.application.entity;

import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Objects;

import org.springframework.stereotype.Component;

@Component
public class Estado {
    private static final Map<String, Estado> estados = new LinkedHashMap<>();

    private final String ambito;
    private final String nombreEstado;

    public Estado() {
        this.ambito = "";
        this.nombreEstado = "";
    }

    // Constructor privado para forzar uso de obtener(...)
    public Estado(String ambito, String nombreEstado) {
        this.ambito = ambito;
        this.nombreEstado = nombreEstado;
    }

    // El método obtener implementa un patrón singleton por clave compuesta (ambito+nombreEstado).
    public static Estado obtener(String ambito, String nombreEstado) {
        String clave = ambito + "::" + nombreEstado;
        return estados.computeIfAbsent(clave, k -> new Estado(ambito, nombreEstado));
    }

     // getTodos retorna todos los estados creados hasta el momento.
     public static Collection<Estado> getTodos() {
        return estados.values();
     }

    public String getAmbito() {
        return ambito;
    }

    public String getNombreEstado() {
        return nombreEstado;
    }

    // Métodos sos* permiten verificar el tipo de estado de forma semántica.
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

    public boolean esRechazado() {
        return this.nombreEstado.equals("Rechazado");
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
