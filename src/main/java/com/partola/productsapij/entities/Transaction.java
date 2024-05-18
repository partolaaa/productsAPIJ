package com.partola.productsapij.entities;

import jakarta.persistence.*;
import lombok.Data;

import java.sql.Timestamp;

/**
 * @author Ivan Partola
 */
@Entity
@Table(name = "transactions")
@Data
public class Transaction {
    @Id
    @Column(name = "transaction_id")
    @GeneratedValue
    private Long transactionId;
    @Column(name = "txid")
    private Long txId;
    @Column(name = "datetime")
    private Timestamp datetime;
    @OneToOne
    @JoinColumn(name = "buyer_id")
    private User buyer;
    @OneToOne
    @JoinColumn(name = "seller_id")
    private User seller;
    @OneToOne
    @JoinColumn(name = "product_id")
    private Product product;
}
