package com.backend.graphql.resolver;

import com.backend.graphql.entity.Product;
import com.backend.graphql.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class ProductResolver {

    @Autowired
    private ProductRepository productRepository;

    // Query Resolvers
    public Optional<Product> getProductById(Long id) {
        return productRepository.findById(id);
    }

    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    // Mutation Resolvers
    public Product createProduct(String name, Double price, String description) {
        Product product = new Product();
        product.setName(name);
        product.setPrice(price);
        product.setDescription(description);
        return productRepository.save(product);
    }

    public Product updateProduct(Long id, String name, Double price, String description) {
        Product product = productRepository.findById(id).orElseThrow(() -> new RuntimeException("Product not found"));
        if (name != null) product.setName(name);
        if (price != null) product.setPrice(price);
        if (description != null) product.setDescription(description);
        return productRepository.save(product);
    }

    public boolean deleteProduct(Long id) {
        productRepository.deleteById(id);
        return true;
    }
}
