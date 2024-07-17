package org.springboot.ecom.controllers;

import org.springboot.ecom.entities.Order;
import org.springboot.ecom.entities.Product;
import org.springboot.ecom.services.OrderService;
import org.springboot.ecom.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.awt.*;
import java.util.List;

@RestController
@RequestMapping("/admin/orders")
public class AdminController {

    @Autowired
    private OrderService orderService;
    @Autowired
    private ProductService productService;

    @GetMapping
    public ResponseEntity<List<Order>> viewPendingOrders() {
        return ResponseEntity.ok(orderService.getOrdersByStatus("PENDING"));
    }

    @PostMapping("/approve/{orderId}")
    public ResponseEntity<Void> approveOrder(@PathVariable Long orderId) {
        orderService.approveOrder(orderId);
        return ResponseEntity.ok().build();
    }

//    @PostMapping("/menu")
//    public ResponseEntity<Product> addMenuItem(@RequestBody Product menuItem) {
//        return ResponseEntity.ok(productService.addProduct(menuItem));
//    }

    @DeleteMapping("/menu/{id}")
    public ResponseEntity<Void> deleteMenuItem(@PathVariable Long id) {
        productService.deleteProduct(id);
        return ResponseEntity.ok().build();
    }
}
