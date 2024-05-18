package com.partola.productsapij.entities;

import jakarta.persistence.*;
import lombok.Data;

/**
 * @author Ivan Partola
 */
@Entity
@Table(name = "users")
@Data
public class User {
    @Id
    @Column(name = "user_id")
    @GeneratedValue
    private Long userId;
    @Column(name = "wallet_id")
    private String walletId;
    @Column(name = "username")
    private String username;
}
