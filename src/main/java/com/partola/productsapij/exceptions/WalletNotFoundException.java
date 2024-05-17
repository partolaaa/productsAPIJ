package com.partola.productsapij.exceptions;

/**
 * @author Ivan Partola
 */
public class WalletNotFoundException extends RuntimeException {
    public WalletNotFoundException(String message) {
        super(message);
    }
}
