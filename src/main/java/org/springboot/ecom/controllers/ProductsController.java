package org.springboot.ecom.controllers;

import org.springboot.ecom.DTO.ProductRequestDTO;
import org.springboot.ecom.entities.Category;
import org.springboot.ecom.entities.Product;
import org.springboot.ecom.services.CategoryService;
import org.springboot.ecom.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/products")
    public class ProductsController {
        private final ProductService productService;
        private final CategoryService categoryService;
        @Autowired
        public ProductsController(ProductService productService, CategoryService categoryService) {
            this.productService = productService;
            this.categoryService = categoryService;
        }
    @GetMapping
    public ResponseEntity<?> getAllProducts(
            @RequestParam int page,
            @RequestParam int size,
            @RequestParam(required = false) String search) {

        Pageable pageable = PageRequest.of(page, size);
        Page<Product> products;

        if (search == null || search.isEmpty()) {
            products = productService.getAllProducts(pageable);
        } else {
            products = productService.searchProducts(search, pageable);
        }

        return ResponseEntity.ok(products);
    }

        @GetMapping("/{id}")
        public ResponseEntity<Product> getProductById(@PathVariable Long id) {
            Product product = productService.getProductById(id);
            return ResponseEntity.ok(product);
        }

        @PostMapping
        public ResponseEntity<Product> addProduct( @RequestBody ProductRequestDTO productRequestDTO) {
//            Product newProduct = productService.addProduct(product);
            Category category = categoryService.findById(productRequestDTO.getCategoryId())
                    .orElseThrow(() -> new RuntimeException("Category not found"));
            Product newProduct = productService.addProduct(productRequestDTO, category);
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
//        @GetMapping("/search")
//        public ResponseEntity<List<Product>> searchProducts(@RequestParam String keyword) {
//            List<Product> products = productService.searchProducts(keyword);
//            return ResponseEntity.ok(products);
//        }
    }


