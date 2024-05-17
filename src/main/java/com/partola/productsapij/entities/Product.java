package com.partola.productsapij.entities;

import jakarta.persistence.*;
import lombok.Data;

import java.sql.Timestamp;

/**
 * @author Ivan Partola
 */
@Entity
@Table(name = "products")
@Data
public class Product {
    @Id
    @Column(name = "product_id")
    private Long productId;
    @Column(name = "name")
    private String name;
    @Column(name = "description")
    private String description;
    @Column(name = "price")
    private Double price;
    @Column(name = "resource_link")
    private String resourceLink;
    @Column(name = "datetime")
    private Timestamp datetime;
    @Column(name = "category_id")
    private Long categoryId;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
}
