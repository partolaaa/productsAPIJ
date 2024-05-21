package com.partola.productsapij.exception;

import static java.lang.String.format;
import static org.springframework.http.HttpStatus.NOT_FOUND;

/**
 * @author Ivan Partola
 */
public class WalletNotFoundException extends BaseControllerException {

    public WalletNotFoundException(String walletId) {
        super(NOT_FOUND, format("Cannot find User by walletId %s", walletId));
    }
}
