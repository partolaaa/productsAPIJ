package com.partola.productsapij.mapper;

import static org.mapstruct.MappingConstants.ComponentModel.SPRING;

import com.partola.productsapij.model.dto.CreateProductDto;
import com.partola.productsapij.model.dto.FullProductDto;
import com.partola.productsapij.model.dto.PlainProductDto;
import com.partola.productsapij.model.entity.Product;
import com.partola.productsapij.model.entity.User;
import java.util.UUID;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

/**
 * @author Ivan Partola
 */
@Mapper(componentModel = SPRING)
public abstract class ProductMapper {

    @Mapping(target = "productId", source = "id")
    @Mapping(target = "userId", source = "user.id")
    public abstract PlainProductDto toPlainProductDTO(Product product);

    @Mapping(target = "productId", source = "id")
    @Mapping(target = "userId", source = "user.id")
    public abstract FullProductDto toFullProductDTO(Product product);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "created", ignore = true)
    @Mapping(target = "updated", ignore = true)
    @Mapping(target = "user", source = "userId", qualifiedByName = "toUser")
    public abstract Product toProduct(CreateProductDto createProductDto, UUID userId);

    @Named("toUser")
    protected User toUser(UUID userId) {
        return User.builder().id(userId).build();
    }
}
