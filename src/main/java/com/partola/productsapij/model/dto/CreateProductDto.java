package com.partola.productsapij.model.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import java.math.BigDecimal;
import java.util.UUID;

public record CreateProductDto(
        @NotNull String name,
        @NotNull String description,
        @Positive BigDecimal price,
        @NotNull String resourceLink,
        // TODO: do we need to check if the category with this id exists?
        @NotNull UUID categoryId) {

}
