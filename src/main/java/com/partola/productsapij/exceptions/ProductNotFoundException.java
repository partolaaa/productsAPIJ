package com.partola.productsapij.exceptions;

/**
 * @author Ivan Partola
 */
public class ProductNotFoundException extends RuntimeException {
    public ProductNotFoundException(String message) {
        super(message);
    }
}
