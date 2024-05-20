package com.partola.productsapij.repositories;

import com.partola.productsapij.entities.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author Ivan Partola
 */
@Repository
public interface ProductsRepository extends JpaRepository<Product, Long> {
    List<Product> findByUserWalletId(String walletId);
    Product findByProductId(Long productId);
    @Query("SELECT p FROM Product p WHERE "
            + "(:title IS NULL OR :title = '' OR LOWER(p.name) LIKE LOWER(CONCAT('%', :title, '%'))) AND "
            + "(:categoryIds IS NULL OR p.categoryId IN :categoryIds) "
            + "ORDER BY p.datetime DESC")
    Page<Product> findAllByFilters(@Param("title") String title,
                                   @Param("categoryIds") List<Integer> categoryIds,
                                   Pageable pageable);
}
