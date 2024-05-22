package com.partola.productsapij.model.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Ivan Partola
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PlainProductDto {

    // TODO: why can't we have regular json property names like 'productId'?
    @JsonProperty("product_id")
    private UUID productId;
    private String name;
    private String description;
    private BigDecimal price;
    // TODO: why can't we have regular json property names like 'categoryId'?
    @JsonProperty("category_id")
    private UUID categoryId;
    // TODO: why can't we have regular json property names like 'userId'?
    @JsonProperty("user_id")
    private UUID userId;
    private LocalDateTime created;
    private LocalDateTime updated;
}
