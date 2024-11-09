package com.Carlos.FinalProject;

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
                String[] details = line.split(",");
                Usuario usuario = new Usuario(details[0], Integer.parseInt(details[1]));
                usuarios.add(usuario);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public boolean verificarDescuento(String nombre) {
        for (Usuario u : usuarios) {
            if (u.getNombre().equalsIgnoreCase(nombre) && u.getAntiguedad() > 3) {
                return true;
            }
        }
        return false;
    }
}