package com.Carlos.FinalProject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/shop/e-commerce/cart")
public class ShoppingCartController {

    private final ShoppingCartService cartService;
    private final ProductRepository productRepository;

    @Autowired
    public ShoppingCartController(ShoppingCartService cartService, ProductRepository productRepository) {
        this.cartService = cartService;
        this.productRepository = productRepository;
    }

    @PostMapping("/add")
    public ResponseEntity<Product> addProduct(@RequestBody Product product) {
        // Guardar el producto en la base de datos y añadirlo al carrito
        Product savedProduct = productRepository.save(product);
        cartService.addProduct(savedProduct);
        return ResponseEntity.ok(savedProduct);
    }

    @PostMapping("/add/multiple")
    public ResponseEntity<String> addMultipleProducts(@RequestBody List<Product> products) {
        for (Product product : products) {
            Product savedProduct = productRepository.save(product);
            cartService.addProduct(savedProduct);
        }
        return ResponseEntity.ok("Productos añadidos correctamente al carrito.");
    }

    @DeleteMapping("/remove/{id}")
    public void removeProduct(@PathVariable Long id) {
        cartService.removeProduct(id);
    }

    @GetMapping("/total/{userName}")
    public double getTotalPrice(@PathVariable String userName) {
        return cartService.getTotalPriceWithDiscount(userName);
    }

    @GetMapping
    public List<Product> getCart() {
        return cartService.getCart();
    }
}