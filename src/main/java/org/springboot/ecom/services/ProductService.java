package org.springboot.ecom.services;

import org.springboot.ecom.DTO.ProductRequestDTO;
import org.springboot.ecom.entities.Category;
import org.springboot.ecom.entities.Product;
import org.springboot.ecom.exceptions.ResourceNotFoundException;
import org.springboot.ecom.repositories.CategoryRepository;
import org.springboot.ecom.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {
    private final ProductRepository productRepository;
    private final CategoryService categoryService;

    @Autowired
    public ProductService(ProductRepository productRepository, CategoryService categoryService) {
        this.productRepository = productRepository;
//        this.categoryService = categoryService;
        this.categoryService = categoryService;
    }
    public Page<Product> getAllProducts(Pageable pageable) {
        return productRepository.findAll(pageable);
    }

    public Product getProductById(Long id) {
        return productRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Product not found with id " + id));
    }

    public Product addProduct(ProductRequestDTO  productRequestDTO, Category category) {
        Product product = new Product();
        product.setName(productRequestDTO.getName());
        product.setPrice(productRequestDTO.getPrice());
        product.setDescription(productRequestDTO.getDescription());
        product.setCategory(category);
        return productRepository.save(product);
    }

    public Product updateProduct(Long id, Product productDetails) {
        Product product = getProductById(id);
        product.setName(productDetails.getName());
        product.setDescription(productDetails.getDescription());
        product.setPrice(productDetails.getPrice());
//        product.setCategory(productDetails.getCategory());
        return productRepository.save(product);
    }

    public void deleteProduct(Long id) {
        Product product = getProductById(id);
        productRepository.delete(product);
    }
    public Page<Product> searchProducts(String keyword, Pageable pageable) {
        // Implement search logic here, e.g., by name or any other criteria
        return productRepository.findByNameContainingIgnoreCase(keyword,pageable);
    }

}

