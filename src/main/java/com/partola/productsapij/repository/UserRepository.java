package com.partola.productsapij.repository;

import com.partola.productsapij.model.entity.User;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author Ivan Partola
 */
public interface UserRepository extends JpaRepository<User, UUID> {

    boolean existsByWalletId(String walletId);
}
