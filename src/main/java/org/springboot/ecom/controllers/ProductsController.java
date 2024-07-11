package org.springboot.ecom.controllers;

import org.springboot.ecom.entities.Product;
import org.springboot.ecom.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/products")
    public class ProductsController {
        private final ProductService productService;

        @Autowired
        public ProductsController(ProductService productService) {
            this.productService = productService;
        }

        @GetMapping
        public ResponseEntity<List<Product>> getAllProducts() {
            List<Product> products = productService.getAllProducts();
            return ResponseEntity.ok(products);
        }

        @GetMapping("/{id}")
        public ResponseEntity<Product> getProductById(@PathVariable Long id) {
            Product product = productService.getProductById(id);
            return ResponseEntity.ok(product);
        }

        @PostMapping
        public ResponseEntity<Product> addProduct(@RequestBody Product product) {
            Product newProduct = productService.addProduct(product);
            return ResponseEntity.status(HttpStatus.CREATED).body(newProduct);
        }

        @PutMapping("/{id}")
        public ResponseEntity<Product> updateProduct(@PathVariable Long id, @RequestBody Product productDetails) {
            Product updatedProduct = productService.updateProduct(id, productDetails);
            return ResponseEntity.ok(updatedProduct);
        }

        @DeleteMapping("/{id}")
        public ResponseEntity<Void> deleteProduct(@PathVariable Long id) {
            productService.deleteProduct(id);
            return ResponseEntity.noContent().build();
        }
    }


