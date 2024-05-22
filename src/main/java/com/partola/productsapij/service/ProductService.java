package com.partola.productsapij.service;

import com.partola.productsapij.exception.ProductNotFoundException;
import com.partola.productsapij.mapper.ProductMapper;
import com.partola.productsapij.model.criteria.ProductCriteria;
import com.partola.productsapij.model.dto.CreateProductDto;
import com.partola.productsapij.model.dto.FullProductDto;
import com.partola.productsapij.model.dto.PlainProductDto;
import com.partola.productsapij.model.entity.Product;
import com.partola.productsapij.model.entity.Product.Fields;
import com.partola.productsapij.repository.ProductRepository;
import com.partola.productsapij.repository.specification.ProductSpecification;
import java.util.List;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

/**
 * @author Ivan Partola
 */
@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;
    private final ProductMapper productMapper;
    private final UserService userService;

    public List<PlainProductDto> getAllListingsByWallet(String walletId) {
        userService.assertUserExistsByWalletId(walletId);
        return productRepository.findByUserWalletId(walletId).stream()
                .map(productMapper::toPlainProductDTO)
                .toList();
    }

    public FullProductDto getFullProductById(UUID productId) {
        final Product product = getProductById(productId);
        return productMapper.toFullProductDTO(product);
    }

    public PlainProductDto getPlainProductById(UUID productId) {
        final Product product = getProductById(productId);
        return productMapper.toPlainProductDTO(product);
    }

    public Product createProduct(CreateProductDto createProductDto, UUID userId) {
        final Product product = productMapper.toProduct(createProductDto, userId);
        return productRepository.save(product);
    }

    public void deleteProductById(UUID productId) {
        final Product product = getProductById(productId);
        productRepository.delete(product);
    }

    public Page<PlainProductDto> getFilteredProducts(ProductCriteria productCriteria, int limit, int offset) {
        final ProductSpecification productSpecification = ProductSpecification.of(productCriteria);
        final Sort sort = Sort.by(Fields.created).descending();
        final int pageNumber = offset / limit;
        final Pageable pageable = PageRequest.of(pageNumber, limit, sort);
        return productRepository.findAll(productSpecification, pageable).
                map(productMapper::toPlainProductDTO);
    }

    private Product getProductById(UUID id) {
        return productRepository.findById(id)
                .orElseThrow(() -> new ProductNotFoundException(id));
    }
}
