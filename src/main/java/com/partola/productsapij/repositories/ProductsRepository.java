package com.partola.productsapij.repositories;

import com.partola.productsapij.entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author Ivan Partola
 */
@Repository
public interface ProductsRepository extends JpaRepository<Product, Long> {
    List<Product> findByUserWalletId(String walletId);
    Product findByProductId(Long productId);
}
