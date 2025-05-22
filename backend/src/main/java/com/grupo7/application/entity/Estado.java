package com.grupo7.application.entity;

import jakarta.persistence.*;

import java.util.ArrayList; // Import ArrayList
import java.util.List;      // Import List
import java.util.Objects;

@Entity
@Table(name = "estado")
public class Estado {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String ambito;

    @Column(name = "nombre_estado", nullable = false)
    private String nombreEstado; // Matches your field name

    public Estado() {
        this.ambito = "";
        this.nombreEstado = "";
    }

    public Estado(String ambito, String nombreEstado) {
        this.ambito = ambito;
        this.nombreEstado = nombreEstado;
    }

    public Long getId() {
        return id;
    }

    public String getAmbito() {
        return ambito;
    }

    public String getNombreEstado() {
        return nombreEstado;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setAmbito(String ambito) {
        this.ambito = ambito;
    }

    public void setNombreEstado(String nombreEstado) {
        this.nombreEstado = nombreEstado;
    }

    // --- CRITICAL MISSING METHOD FOR GestorRevisionManual ---
    public static List<Estado> getTodos() {
        // IMPORTANT: In a real application, these states should be fetched from the database
        // via an EstadoRepository, or loaded from configuration, not hardcoded like this.
        // This implementation is solely to resolve compilation errors.
        List<Estado> allStates = new ArrayList<>();
        allStates.add(new Estado("EventoSismico", "AutoDetectado"));
        allStates.add(new Estado("EventoSismico", "PendienteRevision"));
        allStates.add(new Estado("EventoSismico", "BloqueadoEnRevision"));
        allStates.add(new Estado("EventoSismico", "Rechazado"));
        // Add other states as needed, ensuring 'ambito' and 'nombreEstado' match your actual data
        return allStates;
    }

    // Métodos semánticos - Corrected to use 'nombreEstado'
    public boolean esAmbitoEventoSismico() {
        return "EventoSismico".equals(this.ambito);
    }

    public boolean sosPendienteRevision() {
        return "PendienteRevision".equals(this.nombreEstado);
    }

    public boolean sosAutoDetectado() {
        return "AutoDetectado".equals(this.nombreEstado);
    }

    public boolean sosBloqueadoEnRevision() {
        return "BloqueadoEnRevision".equals(this.nombreEstado);
    }

    public boolean esRechazado() {
        return "Rechazado".equals(this.nombreEstado);
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
        return Objects.equals(ambito, estado.ambito) &&
               Objects.equals(nombreEstado, estado.nombreEstado);
    }

    @Override
    public int hashCode() {
        return Objects.hash(ambito, nombreEstado);
    }
}