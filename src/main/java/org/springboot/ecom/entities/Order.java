package org.springboot.ecom.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "`order`")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String tableNumber;
    private String itemName;
    private String status; // e.g., PENDING, APPROVED

    // Getters and Setters

}
