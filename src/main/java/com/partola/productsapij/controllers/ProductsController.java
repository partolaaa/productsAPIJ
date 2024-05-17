package com.partola.productsapij.controllers;

import com.partola.productsapij.services.ProductsService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Ivan Partola
 */
@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/api")
public class ProductsController {
    private ProductsService productsService;


}
