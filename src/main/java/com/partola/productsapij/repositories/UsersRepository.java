package com.partola.productsapij.repositories;

import com.partola.productsapij.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author Ivan Partola
 */
public interface UsersRepository extends JpaRepository<User, Long> {
    boolean existsByWalletId(String walletId);
}
