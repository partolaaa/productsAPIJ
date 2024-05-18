package com.partola.productsapij.util;

import com.partola.productsapij.dto.PlainProductDTO;
import com.partola.productsapij.entities.Product;
import com.partola.productsapij.entities.User;
import com.partola.productsapij.exceptions.ProductNotFoundException;

import java.sql.Timestamp;
import java.time.Instant;

/**
 * @author Ivan Partola
 */
public class ProductsUtil {
    private ProductsUtil() {}

    public static void exists(Long productId, Product product) {
        if (product == null) {
            throw new ProductNotFoundException("Product with id " + productId + " was not found");
        }
    }
    public static void exists(Long productId, PlainProductDTO product) {
        if (product == null) {
            throw new ProductNotFoundException("Product with id " + productId + " was not found");
        }
    }

    public static Product preBuildProduct(Product product, Long userId) {
        User user = new User();
        user.setUserId(userId);
        product.setUser(user);
        product.setDatetime(Timestamp.from(Instant.now()));

        return product;
    }
}
