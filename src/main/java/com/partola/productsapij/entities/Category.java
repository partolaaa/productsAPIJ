package com.partola.productsapij.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

/**
 * @author Ivan Partola
 */
@Entity
@Table(name = "categories")
public class Category {
    @Id
    @Column(name = "category_id")
    private Long categoryId;
    @Column(name = "name")
    private String name;
    @Column(name = "description")
    private String description;
}