package com.grupo7.application.entity;

import com.grupo7.application.dto.DetalleMuestraDTO; // Required for toMuestraSismicaDTO
import com.grupo7.application.dto.MuestraSismicaDTO; // Required for toMuestraSismicaDTO
import jakarta.persistence.*;
import org.hibernate.Hibernate; // Required for Hibernate.initialize() in toMuestraSismicaDTO

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors; // Required for stream operations

@Entity
@Table(name = "muestra_sismica")
public class MuestraSismica {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_muestra")
    private Long id;

    @Column(name = "fecha_hora_muestra", nullable = false)
    private LocalDateTime fechaHoraMuestra;

    // Assuming MuestraSismica has a value associated with the sample itself
    @Column(name = "valor_muestra")
    private int valor; // Added this field based on toMuestraSismicaDTO's constructor

    // Corrected relationship: MuestraSismica has MANY DetalleMuestraSismica
    // mappedBy points to the field in DetalleMuestraSismica that owns the relationship (the ManyToOne side)
    @OneToMany(mappedBy = "muestraSismica", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private List<DetalleMuestraSismica> detalles = new ArrayList<>(); // Renamed for clarity and consistency

    // Assuming it links back to SerieTemporal, as per previous fix on SerieTemporal
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "serie_temporal_id") // Assuming this foreign key exists
    private SerieTemporal serieTemporal;

    public MuestraSismica() {
        this.fechaHoraMuestra = LocalDateTime.now();
        this.detalles = new ArrayList<>(); // Initialize the list
    }

    public MuestraSismica(LocalDateTime fechaHoraMuestra, int valor, List<DetalleMuestraSismica> detalles) {
        this.fechaHoraMuestra = fechaHoraMuestra;
        this.valor = valor;
        this.detalles = detalles != null ? detalles : new ArrayList<>();
    }

    // --- Getters and Setters ---
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDateTime getFechaHoraMuestra() {
        return fechaHoraMuestra;
    }

    public void setFechaHoraMuestra(LocalDateTime fechaHoraMuestra) {
        this.fechaHoraMuestra = fechaHoraMuestra;
    }

    public int getValor() {
        return valor;
    }

    public void setValor(int valor) {
        this.valor = valor;
    }

    // Renamed from getDetalleMuestra() to getDetalles() to reflect List type
    public List<DetalleMuestraSismica> getDetalles() {
        return detalles;
    }

    // Renamed from setDetalleMuestra() to setDetalles()
    public void setDetalles(List<DetalleMuestraSismica> detalles) {
        this.detalles = detalles;
    }

    public SerieTemporal getSerieTemporal() {
        return serieTemporal;
    }

    public void setSerieTemporal(SerieTemporal serieTemporal) {
        this.serieTemporal = serieTemporal;
    }

    // Method to add a single detail (useful for building the list)
    public void addDetalle(DetalleMuestraSismica detalle) {
        if (this.detalles == null) {
            this.detalles = new ArrayList<>();
        }
        this.detalles.add(detalle);
        detalle.setMuestraSismica(this); // Ensure the inverse side is set
    }

    /**
     * Converts this MuestraSismica entity into a MuestraSismicaDTO.
     * It maps all associated DetalleMuestraSismica entities to DetalleMuestraDTOs.
     * @return A MuestraSismicaDTO object populated with this entity's data.
     */
    public MuestraSismicaDTO toMuestraSismicaDTO() {
        // Ensure lazy-loaded 'detalles' are initialized before accessing
        Hibernate.initialize(this.detalles);

        List<DetalleMuestraDTO> detallesDTO = this.detalles.stream()
            .map(detalleEntity -> {
                // Assuming DetalleMuestraSismica has getTipoDeDato() and getValor()
                return new DetalleMuestraDTO(detalleEntity.getTipoDeDato(), detalleEntity.getValor());
            })
            .collect(Collectors.toList());

        // Assuming MuestraSismicaDTO constructor takes List<DetalleMuestraDTO> and an int valor
        return new MuestraSismicaDTO(detallesDTO, this.valor);
    }
}