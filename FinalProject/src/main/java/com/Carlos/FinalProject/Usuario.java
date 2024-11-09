package com.Carlos.FinalProject;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nombre;
    private int antiguedad;

    protected Usuario() {}

    public Usuario(String nombre, int antiguedad) {
        this.nombre = nombre;
        this.antiguedad = antiguedad;
    }

    public Long getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public int getAntiguedad() {
        return antiguedad;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setAntiguedad(int antiguedad) {
        this.antiguedad = antiguedad;
    }
}