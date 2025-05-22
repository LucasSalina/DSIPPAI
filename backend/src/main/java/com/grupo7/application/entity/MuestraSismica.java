package com.grupo7.application.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "muestra_sismica")
public class MuestraSismica {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_muestra")
    private Long id;

    @Column(name = "fecha_hora_muestra", nullable = false)
    private LocalDateTime fechaHoraMuestra;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "id_detalle_muestra", nullable = false)
    private DetalleMuestraSismica detalleMuestra;

    public MuestraSismica() {
        this.fechaHoraMuestra = LocalDateTime.now();
    }

    public MuestraSismica(LocalDateTime fechaHoraMuestra, DetalleMuestraSismica detalleMuestra) {
        this.fechaHoraMuestra = fechaHoraMuestra;
        this.detalleMuestra = detalleMuestra;
    }

    public Long getId() {
        return id;
    }

    public LocalDateTime getFechaHoraMuestra() {
        return fechaHoraMuestra;
    }

    public void setFechaHoraMuestra(LocalDateTime fechaHoraMuestra) {
        this.fechaHoraMuestra = fechaHoraMuestra;
    }

    public DetalleMuestraSismica getDetalleMuestra() {
        return detalleMuestra;
    }

    public void setDetalleMuestra(DetalleMuestraSismica detalleMuestra) {
        this.detalleMuestra = detalleMuestra;
    }

    /**
     * getDatos: recorre el detalle y retorna solo la información relevante.
     */
    public List<Object> getDatos() {
        List<Object> detallesDeMuestras = new ArrayList<>();
        List<Object> datos = detalleMuestra.getDatos();
        if (!datos.isEmpty()) {
            detallesDeMuestras.add(datos);
        }
        return detallesDeMuestras;
    }
}

