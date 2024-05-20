package com.partola.productsapij.controllers;

import com.partola.productsapij.dto.PlainProductDTO;
import com.partola.productsapij.entities.Category;
import com.partola.productsapij.entities.Product;
import com.partola.productsapij.exceptions.ProductNotFoundException;
import com.partola.productsapij.exceptions.WalletNotFoundException;
import com.partola.productsapij.services.ProductsService;
import com.partola.productsapij.util.JwtUtil;
import com.partola.productsapij.util.ProductsUtil;
import io.jsonwebtoken.Claims;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

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

    @GetMapping("/product/{productId}")
    @ResponseBody
    public ResponseEntity<?> getProductById(@RequestHeader(value = "Authorization", required = false) String authorizationHeader,
                                            @PathVariable Long productId) {

        PlainProductDTO plainProductDTO;
        if (authorizationHeader == null || !authorizationHeader.startsWith("Bearer ")) {
            plainProductDTO = productsService.getPlainProductById(productId);
        } else {
            plainProductDTO = productsService.getFullProductById(productId);
        }

        ProductsUtil.exists(productId, plainProductDTO);

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(plainProductDTO);
    }

    @DeleteMapping("/product/{productId}")
    @ResponseBody
    public ResponseEntity<?> deleteProductById(@RequestHeader(value = "Authorization", required = false) String authorizationHeader,
                                            @PathVariable Long productId) {

        if (authorizationHeader == null || !authorizationHeader.startsWith("Bearer ")) {
            return ResponseEntity
                    .status(HttpStatus.UNAUTHORIZED)
                    .body("Product was not deleted");
        }

        productsService.deleteProductById(productId);

        return ResponseEntity
                .status(HttpStatus.OK)
                .body("Product deleted successfully");
    }

    @PostMapping("/new")
    @ResponseBody
    public ResponseEntity<?> newProduct(@RequestHeader("Authorization") String authorizationHeader,
                                        @RequestBody Product product) {

        if (authorizationHeader == null || !authorizationHeader.startsWith("Bearer ")) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid token");
        }

        if (product.getPrice() <= 0) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Incorrect price");
        }

        String token = authorizationHeader.substring(7);
        if (JwtUtil.validateToken(token)) {
            Claims claims = JwtUtil.getClaims(token);
            String userId = claims.get("http://schemas.microsoft.com/ws/2008/06/identity/claims/userdata", String.class);

            productsService.createProduct(ProductsUtil.preBuildProduct(product, Long.parseLong(userId)));
            return ResponseEntity.status(HttpStatus.CREATED).body("Product added successfully");
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid token");
        }
    }

    @GetMapping("/get-products")
    public ResponseEntity<?> getProducts(@RequestParam(defaultValue = "40") int limit,
                                         @RequestParam Optional<String> title,
                                         @RequestParam Optional<List<Integer>> categoryId,
                                         @RequestParam(defaultValue = "0") int offset) {

        String titleValue = title.orElse(null);
        List<Integer> categoryIds = categoryId.orElse(null);

        Page<PlainProductDTO> products = productsService.getFilteredProducts(titleValue, categoryIds, limit, offset);
        return ResponseEntity.ok(products.getContent());
    }

    @ExceptionHandler(WalletNotFoundException.class)
    public ResponseEntity<String> handleWalletNotFoundException(WalletNotFoundException ex) {
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(ex.getMessage());
    }

    @ExceptionHandler(ProductNotFoundException.class)
    public ResponseEntity<String> handleProductNotFoundException(ProductNotFoundException ex) {
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(ex.getMessage());
    }
}
