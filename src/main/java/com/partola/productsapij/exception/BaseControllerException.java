package com.partola.productsapij.exception;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

@Slf4j
@JsonIgnoreProperties({"mostSpecificCause"})
public class BaseControllerException extends ResponseStatusException {

    public BaseControllerException(HttpStatus httpStatus, String message) {
        super(httpStatus, message);
        log.warn(this.getClass() + " - " + message);
    }
}
