package com.partola.productsapij.repositories;

import com.partola.productsapij.entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * @author Ivan Partola
 */
public interface ProductsRepository extends JpaRepository<Product, Long> {
    List<Product> findByUserWalletId(String walletId);
}
