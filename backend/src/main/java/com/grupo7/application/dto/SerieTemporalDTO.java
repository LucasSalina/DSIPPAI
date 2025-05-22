package com.grupo7.application.dto;

import java.util.ArrayList; // Don't forget to import ArrayList
import java.util.List;     // It's often better to use the List interface

import com.grupo7.application.dto.DetalleMuestraDTO;

public class SerieTemporalDTO {
    // It's generally better to use the List interface rather than ArrayList directly.
    // This provides more flexibility if you decide to change the underlying implementation later.
    private List<DetalleMuestraDTO> detalleMuestra; // Changed to List and used DetalleMuestraDTO

    private int valor; // The purpose of this 'valor' field is unclear given the context of DetalleMuestraDTO.
                       // You might want to reconsider if it's truly needed here or if it's a remnant.

    // Default constructor is good practice
    public SerieTemporalDTO() {
        this.detalleMuestra = new ArrayList<>(); // Initialize the list to prevent NullPointerExceptions
    }

    // Constructor with parameters
    public SerieTemporalDTO(List<DetalleMuestraDTO> detalleMuestra, int valor) {
        this.detalleMuestra = detalleMuestra;
        this.valor = valor;
    }

    // --- Getters and Setters ---

    public List<DetalleMuestraDTO> getDetalleMuestra() {
        return detalleMuestra;
    }

    public void setDetalleMuestra(List<DetalleMuestraDTO> detalleMuestra) {
        this.detalleMuestra = detalleMuestra;
    }

    public int getValor() {
        return valor;
    }

    public void setValor(int valor) {
        this.valor = valor;
    }

    // You might also consider methods to add individual DetalleMuestraDTO objects
    public void addDetalleMuestra(DetalleMuestraDTO muestra) {
        if (this.detalleMuestra == null) {
            this.detalleMuestra = new ArrayList<>();
        }
        this.detalleMuestra.add(muestra);
    }
}