package com.Carlos.FinalProject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;

@Component
public class ApplicationCommandLineRunner implements CommandLineRunner {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Override
    public void run(String... args) throws Exception {
        // Products
        List<Product> products = Arrays.asList(
                new Product("Camiseta Básica", "Camiseta de algodón blanco de manga corta para uso diario.", 40),
                //... otros productos
                new Product("Gafas de Sol", "Gafas de sol con montura metálica y cristales polarizados.", 110)
        );

        productRepository.saveAll(products);
        System.out.println("Products saved successfully.");

        // Usuarios
        List<Usuario> usuarios = Arrays.asList(
                new Usuario("Ana", 3),
                //... otros usuarios
                new Usuario("Gabriela", 2)
        );

        usuarioRepository.saveAll(usuarios);
        System.out.println("Usuarios saved successfully.");
    }
}