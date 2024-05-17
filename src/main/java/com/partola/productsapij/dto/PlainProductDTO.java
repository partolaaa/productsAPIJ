package com.partola.productsapij.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

/**
 * @author Ivan Partola
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PlainProductDTO {
    @JsonProperty("product_id")
    private Long productId;
    private String name;
    private String description;
    private Double price;
    @JsonProperty("category_id")
    private Long categoryId;
    @JsonProperty("user_id")
    private Long userId;
    private Timestamp datetime;
}
