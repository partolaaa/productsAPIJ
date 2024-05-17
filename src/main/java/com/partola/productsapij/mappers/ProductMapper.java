package com.partola.productsapij.mappers;

import com.partola.productsapij.dto.PlainProductDTO;
import com.partola.productsapij.entities.Product;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import static org.mapstruct.MappingConstants.ComponentModel.SPRING;

/**
 * @author Ivan Partola
 */
@Mapper(componentModel = SPRING)
public abstract class ProductMapper {
    @Mapping(source = "user.userId", target = "userId")
    public abstract PlainProductDTO toPlainProductDTO(Product product);
}
