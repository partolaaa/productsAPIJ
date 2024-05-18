package com.partola.productsapij.entities;

import jakarta.persistence.*;
import lombok.Data;

/**
 * @author Ivan Partola
 */
@Entity
@Table(name = "categories")
@Data
public class Category {
    @Id
    @Column(name = "category_id")
    @GeneratedValue
    private Long categoryId;
    @Column(name = "name")
    private String name;
    @Column(name = "description")
    private String description;
}
