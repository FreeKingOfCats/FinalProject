package com.Carlos.FinalProject.ECommerce;

public class UsuarioNameRequest {

    private String nombre;

    public UsuarioNameRequest() {}

    public UsuarioNameRequest(String nombre) {
        this.nombre = nombre;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
}