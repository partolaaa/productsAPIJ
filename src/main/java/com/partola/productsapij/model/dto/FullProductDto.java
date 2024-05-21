package com.partola.productsapij.model.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * @author Ivan Partola
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class FullProductDto extends PlainProductDto {

    // TODO: why can't we have regular json property names like 'resourceLink'?
    @JsonProperty("resource_link")
    private String resourceLink;
}