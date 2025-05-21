package com.grupo7.application.repository;

import com.grupo7.application.entity.Sismografo;

import java.util.ArrayList;
import java.util.List;

public class RepositorioDeSismografos {
    private static final List<Sismografo> sismografos = new ArrayList<>();

    public static void agregar(Sismografo s) {
        sismografos.add(s);
    }

    public static List<Sismografo> obtenerTodos() {
        return new ArrayList<>(sismografos);
    }
}
