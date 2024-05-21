package com.partola.productsapij.exception;

import static java.lang.String.format;
import static org.springframework.http.HttpStatus.UNAUTHORIZED;

/**
 * @author Ivan Partola
 */
public class InvalidTokenException extends BaseControllerException {

    public InvalidTokenException(Exception e) {
        super(UNAUTHORIZED, format("Invalid token: %s", e.getMessage()));
    }
}
