package com.Carlos.FinalProject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ShoppingCartService {
    private List<Product> cart = new ArrayList<>();

    private final UsuarioService usuarioService;

    @Autowired
    public ShoppingCartService(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    public void addProduct(Product product) {
        cart.add(product);
    }

    public void removeProduct(Long productId) {
        cart.removeIf(product -> product.getId().equals(productId));
    }

    public double getTotalPrice() {
        return cart.stream().mapToDouble(Product::getPrice).sum();
    }

    public double getTotalPriceWithDiscount(String userName) {
        Usuario usuario = usuarioService.verificarUsuario(userName);
        double total = getTotalPrice();

        if ("Premium".equals(usuario.getRol())) {
            if (usuario.getAntiguedad() >= 2) {
                total *= 0.9;
                System.out.println("Descuento del 10% aplicado para usuario Premium con antigüedad: " + usuario.getNombre());
            } else {
                total *= 0.98;
                System.out.println("Descuento del 2% aplicado para usuario Premium sin antigüedad suficiente: " + usuario.getNombre());
            }
        } else {
            System.out.println("Sin descuento aplicado para usuario Guest: " + usuario.getNombre());
        }

        return total;
    }
    public List<Product> getCart() {
        return new ArrayList<>(cart);
    }
}