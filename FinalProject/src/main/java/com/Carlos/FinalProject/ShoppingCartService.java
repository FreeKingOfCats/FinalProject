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

    public void removeProduct(String productId) {
        cart.removeIf(product -> product.getId().equals(productId));
    }

    public double getTotalPrice() {
        return cart.stream().mapToDouble(Product::getPrice).sum();
    }

    // Modificación aquí: el método ahora acepta el nombre del usuario para verificar su antigüedad
    public double getTotalPriceWithDiscount(String userName) {
        if (usuarioService.verificarDescuento(userName) && cart.size() >= 2) {
            return getTotalPrice() * 0.9; // Aplicar un descuento del 10%
        } else {
            return getTotalPrice();
        }
    }

    public List<Product> getCart() {
        return new ArrayList<>(cart); // Devuelve una copia del carrito
    }
}