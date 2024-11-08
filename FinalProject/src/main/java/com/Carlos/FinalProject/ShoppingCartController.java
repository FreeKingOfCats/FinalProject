package com.Carlos.FinalProject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/cart")
public class ShoppingCartController {

    private final ShoppingCartService cartService;

    @Autowired
    public ShoppingCartController(ShoppingCartService cartService) {
        this.cartService = cartService;
    }

    @PostMapping("/add")
    public void addProduct(@RequestBody Product product) {
        cartService.addProduct(product);
    }

    @DeleteMapping("/remove/{id}")
    public void removeProduct(@PathVariable String id) {
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