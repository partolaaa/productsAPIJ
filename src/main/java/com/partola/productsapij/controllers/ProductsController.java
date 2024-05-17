package com.partola.productsapij.controllers;

import com.partola.productsapij.dto.PlainProductDTO;
import com.partola.productsapij.entities.Category;
import com.partola.productsapij.exceptions.WalletNotFoundException;
import com.partola.productsapij.services.ProductsService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author Ivan Partola
 */
@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/api")
public class ProductsController {
    private final ProductsService productsService;
    @GetMapping("/wallet/{walletId}")
    @ResponseBody
    public ResponseEntity<List<PlainProductDTO>> getAllListingsByWallet(@PathVariable String walletId) {
        List<PlainProductDTO> products = productsService.getAllListingsByWallet(walletId);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(products);
    }

    @GetMapping("/category")
    @ResponseBody
    public ResponseEntity<List<Category>> getCategories() {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(productsService.getAllProductCategories());
    }

    @ExceptionHandler(WalletNotFoundException.class)
    public ResponseEntity<String> handleWalletNotFoundException(WalletNotFoundException ex) {
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(ex.getMessage());
    }
}
