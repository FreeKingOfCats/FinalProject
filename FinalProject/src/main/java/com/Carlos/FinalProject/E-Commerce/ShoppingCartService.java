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

    public boolean removeProductByName(String productName) {
        return cart.removeIf(product -> product.getName().equalsIgnoreCase(productName));
    }

    public double getTotalPrice() {
        return cart.stream().mapToDouble(Product::getPrice).sum();
    }

    public double getTotalPriceWithDiscount(String userName) {
        Usuario usuario = usuarioService.verificarUsuario(userName);
        double total = getTotalPrice();

        if (usuario.getAntiguedad() >= 2 && cart.size() >= 2) {
            total *= 0.9;
            System.out.println("Descuento del 10% aplicado para usuario con antigüedad suficiente: " + usuario.getNombre());
        } else {
            System.out.println("Sin descuento, usuario: " + usuario.getNombre());
        }

        return total;
    }
    public List<Product> getCart() {
        return new ArrayList<>(cart);
    }
}