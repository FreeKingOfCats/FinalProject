package com.Carlos.FinalProject.ECommerce;

import org.springframework.stereotype.Service;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

@Service
public class UsuarioService {
    private List<Usuario> usuarios = new ArrayList<>();

    public UsuarioService() {
        loadUsuarios();
    }

    private void loadUsuarios() {
        try {
            List<String> lines = Files.readAllLines(Paths.get(getClass().getClassLoader().getResource("Usuarios.txt").toURI()));
            for (String line : lines) {
                String[] details = line.replace("Id: ", "").replace("Nombre: ", "").replace("Antiguedad: ", "").split(", ");
                Long id = Long.parseLong(details[0]);
                String nombre = details[1];
                int antiguedad = Integer.parseInt(details[2]);

                Usuario usuario = new Usuario(id, nombre, antiguedad);
                if (antiguedad >= 2) {
                    usuario.setRol("Premium");
                } else {
                    usuario.setRol("Guest");
                }

                usuarios.add(usuario);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public Usuario verificarUsuario(String nombre) {
        Usuario usuario = usuarios.stream()
                .filter(u -> u.getNombre().equalsIgnoreCase(nombre))
                .findFirst()
                .orElseGet(() -> {
                    Usuario guestUser = new Usuario(0L, nombre, 0);
                    guestUser.setRol("Guest");
                    return guestUser;
                });

        System.out.println("Usuario encontrado: " + usuario.getNombre() + ", Rol: " + usuario.getRol() + ", Antigüedad: " + usuario.getAntiguedad());
        return usuario;
    }
}