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
    private String rol;

    protected Usuario() {}

    public Usuario(Long id, String nombre, int antiguedad) {
        this.id = id;
        this.nombre = nombre;
        this.antiguedad = antiguedad;
        this.rol = determinacionRol();
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

    public String getRol() {
        return rol;
    }

    public void setRol(String rol) {
        this.rol = rol;
    }

    private String determinacionRol() {
        return antiguedad >= 2 ? "Premium" : "Guest";
    }
}