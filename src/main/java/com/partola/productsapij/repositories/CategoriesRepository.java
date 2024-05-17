package com.partola.productsapij.repositories;

import com.partola.productsapij.entities.Category;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * @author Ivan Partola
 */
public interface CategoriesRepository extends JpaRepository<Category, Long> {
    List<Category> findAll();
}
