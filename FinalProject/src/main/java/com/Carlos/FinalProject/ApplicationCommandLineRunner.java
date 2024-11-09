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
        List<Product> products = Arrays.asList(
                new Product("Camiseta Básica", "Camiseta de algodón blanco de manga corta para uso diario.", 40),
                new Product("Jeans Clásicos", "Vaqueros azul oscuro de corte recto con cinco bolsillos.", 90),
                new Product("Chaqueta Deportiva", "Chaqueta impermeable ligera con capucha, ideal para correr.", 200),
                new Product("Vestido de Verano", "Vestido floreado de tirantes con falda suelta y colores vibrantes.", 30),
                new Product("Sudadera con Capucha", "Sudadera gris con capucha, suave y cómoda para climas fríos.", 120),
                new Product("Blusa Elegante", "Blusa de seda rosa con detalles de encaje para ocasiones especiales.", 95),
                new Product("Pantalón Formal", "Pantalón negro de vestir con un corte ajustado, ideal para la oficina.", 100),
                new Product("Falda Plisada", "Falda negra plisada de largo medio, perfecta para un look casual.", 50),
                new Product("Camisa a Cuadros", "Camisa de franela a cuadros rojos y negros, ideal para el otoño.", 65),
                new Product("Chaqueta de Cuero", "Chaqueta de cuero negro con cierre asimétrico y bolsillos.", 250),
                new Product("Abrigo Largo", "Abrigo de lana largo en color marrón, perfecto para el invierno.", 240),
                new Product("Short Deportivo", "Shorts de poliéster ligeros, ideales para actividades físicas.", 35),
                new Product("Bufanda de Lana", "Bufanda tejida a mano en tonos grises para mantener el calor.", 20),
                new Product("Polo Rayado", "Polo de algodón con rayas horizontales azul y blanco.", 70),
                new Product("Zapatos Deportivos", "Zapatillas ligeras con suela de goma, perfectas para correr.", 130),
                new Product("Sombrero de Paja", "Sombrero amplio de paja, ideal para protegerse del sol.", 15),
                new Product("Chaleco Acolchado", "Chaleco acolchado en tonos verdes con cierre frontal.", 45),
                new Product("Traje de Baño", "Traje de baño de una pieza en color azul marino.", 25),
                new Product("Reloj de Pulsera", "Reloj analógico elegante con correa de cuero.", 500),
                new Product("Gafas de Sol", "Gafas de sol con montura metálica y cristales polarizados.", 110)
        );

        productRepository.saveAll(products);
        System.out.println("Products saved successfully.");

        List<Usuario> usuarios = Arrays.asList(
                new Usuario("Ana", 3),
                new Usuario("Luis", 2),
                new Usuario("Carlos", 5),
                new Usuario("Marta", 0),
                new Usuario("Carla", 1),
                new Usuario("Juan", 4),
                new Usuario("Carmen", 2),
                new Usuario("Pedro", 5),
                new Usuario("Lucas", 3),
                new Usuario("Elena", 0),
                new Usuario("Pablo", 4),
                new Usuario("Clara", 1),
                new Usuario("Javier", 5),
                new Usuario("Beatriz", 2),
                new Usuario("Miguel", 1),
                new Usuario("Laura", 0),
                new Usuario("David", 3),
                new Usuario("Olga", 4),
                new Usuario("Alberto", 1),
                new Usuario("Gabriela", 2)
        );

        usuarioRepository.saveAll(usuarios);
        System.out.println("Usuarios saved successfully.");
    }
}