package com.partola.productsapij.controller;

import static com.partola.productsapij.util.JwtUtil.validateToken;
import static org.springframework.http.HttpHeaders.AUTHORIZATION;
import static org.springframework.http.HttpStatus.UNAUTHORIZED;

import com.partola.productsapij.model.criteria.ProductCriteria;
import com.partola.productsapij.model.dto.CreateProductDto;
import com.partola.productsapij.model.dto.PlainProductDto;
import com.partola.productsapij.model.entity.Product;
import com.partola.productsapij.service.ProductService;
import com.partola.productsapij.util.JwtUtil;
import io.jsonwebtoken.Claims;
import java.util.List;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

/**
 * @author Ivan Partola
 */
@Validated
@RestController
@RequiredArgsConstructor
@RequestMapping("api/products")
public class ProductController {

    private static final String BEARER_PREFIX = "Bearer ";

    private final ProductService productService;

    @Value("${user.claim.url}")
    private String userClaimUrl;

    @GetMapping
    public List<PlainProductDto> getAllListingsByWallet(
            @RequestParam String walletId) {
        return productService.getAllListingsByWallet(walletId);
    }

    @GetMapping("{productId}")
    public PlainProductDto getProductById(
            @RequestHeader(value = AUTHORIZATION, required = false) String authorizationHeader,
            @PathVariable UUID productId) {
        // TODO: what is the role of the authorizationHeader in this case? We just check if it is present, but the value is not used.
        // TODO: temporary code, should be refactored
        if (authorizationHeader == null || !authorizationHeader.startsWith(BEARER_PREFIX)) {
            return productService.getPlainProductById(productId);
        }
        return productService.getFullProductById(productId);
    }

    @DeleteMapping("{productId}")
    public void deleteProductById(
            @RequestHeader(value = AUTHORIZATION, required = false) String authorizationHeader,
            @PathVariable UUID productId) {
        // TODO: what is the role of the authorizationHeader in this case? We just check if it is present, but the value is not used.
        // TODO: temporary code, should be refactored
        if (authorizationHeader == null || !authorizationHeader.startsWith(BEARER_PREFIX)) {
            throw new ResponseStatusException(UNAUTHORIZED);
        }
        productService.deleteProductById(productId);
    }

    // TODO: temporary code, should be refactored
    @PostMapping
    public Product createProduct(
            @RequestHeader(AUTHORIZATION) String authorizationHeader,
            @RequestBody CreateProductDto createProductDto) {
        if (authorizationHeader == null || !authorizationHeader.startsWith(BEARER_PREFIX)) {
            throw new ResponseStatusException(UNAUTHORIZED);
        }
        final String token = authorizationHeader.substring(7);
        validateToken(token);
        final Claims claims = JwtUtil.getClaims(token);
        // TODO: extract to properties
        final UUID userId = claims.get(userClaimUrl, UUID.class);
        return productService.createProduct(createProductDto, userId);
    }

    @GetMapping("page")
    public Page<PlainProductDto> getProductsPage(
            // TODO: can be replaced with Pageable after the purpose of this API is clarified
            @RequestParam(defaultValue = "40") int limit,
            @RequestParam(defaultValue = "0") int offset,
            @RequestBody ProductCriteria productCriteria) {
        return productService.getFilteredProducts(productCriteria, limit, offset);
    }
}
