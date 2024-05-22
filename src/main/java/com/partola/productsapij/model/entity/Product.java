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
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.FieldNameConstants;
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
@FieldNameConstants
@Table(name = "products")
public class Product {

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

    private String name;
    private String description;
    private BigDecimal price;
    private String resourceLink;
    private UUID categoryId; // TODO: add relations?
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
}
