package com.partola.productsapij.repository;

import com.partola.productsapij.model.entity.Category;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author Ivan Partola
 */
public interface CategoryRepository extends JpaRepository<Category, UUID> {

}
