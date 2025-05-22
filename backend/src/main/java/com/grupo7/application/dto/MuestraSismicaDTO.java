package com.grupo7.application.dto;

import com.grupo7.application.dto.DetalleMuestraDTO;

import java.util.ArrayList;
import java.util.List;

public class MuestraSismicaDTO {
    private List<DetalleMuestraDTO> detalleMuestraDTO;

    public MuestraSismicaDTO() {
        this.detalleMuestraDTO = new ArrayList<>();
    }

    public MuestraSismicaDTO(List<DetalleMuestraDTO> detalleMuestraDTO) {
        this.detalleMuestraDTO = detalleMuestraDTO;
    }

    public List<DetalleMuestraDTO> getDetalleMuestraDTO() {
        return detalleMuestraDTO;
    }

    public void setDetalleMuestraDTO(List<DetalleMuestraDTO> detalleMuestraDTO) {
        this.detalleMuestraDTO = detalleMuestraDTO;
    }

    public void addDetalleMuestra(DetalleMuestraDTO detalle) {
        if (this.detalleMuestraDTO == null) {
            this.detalleMuestraDTO = new ArrayList<>();
        }
        this.detalleMuestraDTO.add(detalle);
    }
}
