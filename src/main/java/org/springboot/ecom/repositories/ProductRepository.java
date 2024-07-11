package org.springboot.ecom.repositories;

import org.springboot.ecom.entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {
}
