package com.backend.graphql.controller;

import com.backend.graphql.entity.Product;
import com.backend.graphql.resolver.ProductResolver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
public class ProductController {

    @Autowired
    private ProductResolver productResolver;

    @GetMapping("/products")
    public List<Product> getProducts() {
        return productResolver.getAllProducts();
    }
    // Query Resolvers
    @QueryMapping
    public Optional<Product> getProductById(@Argument Long id) {
        return productResolver.getProductById(id);
    }

    @QueryMapping
    public List<Product> getAllProducts() {
        return productResolver.getAllProducts();
    }

    // Mutation Resolvers
    @MutationMapping
    public Product createProduct(@Argument String name, @Argument Double price, @Argument String description) {
        return productResolver.createProduct(name, price, description);
    }

    @MutationMapping
    public Product updateProduct(@Argument Long id, @Argument String name, @Argument Double price, @Argument String description) {
        return productResolver.updateProduct(id, name, price, description);
    }

    @MutationMapping
    public boolean deleteProduct(@Argument Long id) {
        return productResolver.deleteProduct(id);
    }
}
