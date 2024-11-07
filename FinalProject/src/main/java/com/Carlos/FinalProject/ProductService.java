package com.Carlos.FinalProject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

@Service
public class ProductService {

    private final ProductRepository productRepository;

    @Autowired
    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
        loadProductsFromTxt();
    }

    private void loadProductsFromTxt() {
        try {
            List<String> lines = Files.readAllLines(Paths.get(getClass().getClassLoader().getResource("Products.txt").toURI()));
            for (String line : lines) {
                String[] details = line.split(",");
                Product product = new Product(Long.parseLong(details[0]), details[1], details[2], Double.parseDouble(details[3]));
                productRepository.save(product);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public List<Product> getAvailableProducts() {
        return productRepository.findAll();
    }
}