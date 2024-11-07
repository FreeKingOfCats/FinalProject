package com.Carlos.FinalProject;

public class Usuario {
    private String nombre;
    private int antiguedad;

    public Usuario(String nombre, int antiguedad) {
        this.nombre = nombre;
        this.antiguedad = antiguedad;
    }

    public String getNombre() {
        return nombre;
    }

    public int getAntiguedad() {
        return antiguedad;
    }
}