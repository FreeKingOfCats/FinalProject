package com.Carlos.FinalProject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/shop/e-commerce/products")
public class ProductController {

    private final ProductService productService;
    private final UsuarioService usuarioService;
    private final ProductPremiumRepository productPremiumRepository;

    @Autowired
    public ProductController(ProductService productService, UsuarioService usuarioService, ProductPremiumRepository productPremiumRepository) {
        this.productService = productService;
        this.usuarioService = usuarioService;
        this.productPremiumRepository = productPremiumRepository;
    }

    @GetMapping
    public List<Product> getAvailableProducts(@RequestParam String userName) {
        Usuario usuario = usuarioService.verificarUsuario(userName);

        List<Product> allProducts = new ArrayList<>(productService.getAvailableProducts());

        if ("Premium".equals(usuario.getRol())) {
            allProducts.addAll((List) productPremiumRepository.findAll());
        }

        return allProducts;
    }
}