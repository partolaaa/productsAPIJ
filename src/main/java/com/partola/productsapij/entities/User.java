package com.partola.productsapij.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

/**
 * @author Ivan Partola
 */
@Entity
@Table(name = "users")
public class User {
    @Id
    @Column(name = "user_id")
    private Long userId;
    @Column(name = "wallet_id")
    private String walletId;
    @Column(name = "username")
    private String username;
}
