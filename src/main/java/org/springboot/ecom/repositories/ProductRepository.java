package org.springboot.ecom.repositories;


import org.springboot.ecom.entities.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface ProductRepository extends JpaRepository<Product, Long> {
    Page<Product> findAll(Pageable  pageable);
    Page<Product> findByNameContainingIgnoreCase(String keyword, Pageable pageable);
}

