package com.partola.productsapij.exception;

import static java.lang.String.format;
import static org.springframework.http.HttpStatus.NOT_FOUND;

import java.util.UUID;

/**
 * @author Ivan Partola
 */
public class ProductNotFoundException extends BaseControllerException {

    public ProductNotFoundException(UUID id) {
        super(NOT_FOUND, format("Cannot find Product by id %s", id));
    }
}
