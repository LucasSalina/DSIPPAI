package com.grupo7.application.controller;

import com.grupo7.application.entity.SerieTemporal;
import com.grupo7.application.service.SerieTemporalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@RequestMapping("/serie-temporal")
public class SerieTemporalController {

    @Autowired
    private SerieTemporalService serieTemporalService;

    // Este endpoint es un ejemplo. Ajusta según cómo obtendrás una instancia de SerieTemporal.
    // @PostMapping("/datos")
    // public ArrayList<Object> obtenerDatos(@RequestBody SerieTemporal serieTemporal) {
    //     return serieTemporalService.obtenerDatosDeSerie(serieTemporal);
    // }
}
