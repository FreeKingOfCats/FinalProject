package com.Carlos.FinalProject.Controllers;

import com.Carlos.FinalProject.ECommerce.UsuarioService;
import com.Carlos.FinalProject.Repositories.ProductPremiumRepository;
import com.Carlos.FinalProject.Repositories.ProductRepository;
import com.Carlos.FinalProject.ECommerce.Usuario;
import com.Carlos.FinalProject.ECommerce.Product;
import com.Carlos.FinalProject.ECommerce.ShoppingCartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/shop/e-commerce/cart")
public class ShoppingCartController {

    private final ShoppingCartService cartService;
    private final ProductRepository productRepository;
    private final ProductPremiumRepository productPremiumRepository;
    private final UsuarioService usuarioService;

    @Autowired
    public ShoppingCartController(
            ShoppingCartService cartService,
            ProductRepository productRepository,
            ProductPremiumRepository productPremiumRepository,
            UsuarioService usuarioService) {
        this.cartService = cartService;
        this.productRepository = productRepository;
        this.productPremiumRepository = productPremiumRepository;
        this.usuarioService = usuarioService;
    }

    @PostMapping("/add")
    public ResponseEntity<String> addProduct(@RequestParam String userName, @RequestBody Product product) {
        Usuario usuario = usuarioService.verificarUsuario(userName);

        boolean isPremiumProduct = productPremiumRepository.findByName(product.getName()).isPresent();

        if (isPremiumProduct && !"Premium".equals(usuario.getRol())) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Acceso denegado a productos premium.");
        }

        Product savedProduct = productRepository.save(product);
        cartService.addProduct(savedProduct);
        return ResponseEntity.ok("Producto añadido correctamente al carrito.");
    }

    @PostMapping("/add/multiple")
    public ResponseEntity<String> addMultipleProducts(@RequestParam String userName, @RequestBody List<Product> products) {
        Usuario usuario = usuarioService.verificarUsuario(userName);

        for (Product product : products) {
            boolean isPremiumProduct = productPremiumRepository.findByName(product.getName()).isPresent();

            if (isPremiumProduct && !"Premium".equals(usuario.getRol())) {
                return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Acceso denegado a productos premium.");
            }

            Product savedProduct = productRepository.save(product);
            cartService.addProduct(savedProduct);
        }

        return ResponseEntity.ok("Productos añadidos correctamente al carrito.");
    }

    @DeleteMapping("/remove/{name}")
    public ResponseEntity<String> removeProductByName(@PathVariable String name) {
        boolean isRemoved = cartService.removeProductByName(name);

        if (isRemoved) {
            return ResponseEntity.ok("Producto eliminado del carrito.");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Producto no encontrado en el carrito.");
        }
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