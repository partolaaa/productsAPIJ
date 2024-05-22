package com.partola.productsapij.model.entity;

import static io.swagger.v3.oas.annotations.media.Schema.AccessMode.READ_ONLY;
import static jakarta.persistence.GenerationType.AUTO;
import static lombok.AccessLevel.NONE;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import java.time.LocalDateTime;
import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

/**
 * @author Ivan Partola
 */
@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "transactions")
public class Transaction {

    @Id
    @GeneratedValue(strategy = AUTO)
    private UUID id;

    @Schema(accessMode = READ_ONLY)
    @Setter(NONE)
    @CreationTimestamp
    @Column(updatable = false)
    protected LocalDateTime created;

    @Schema(accessMode = READ_ONLY)
    @Setter(NONE)
    @UpdateTimestamp
    private LocalDateTime updated;

    private Long txId;
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
