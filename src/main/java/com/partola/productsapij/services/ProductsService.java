package com.partola.productsapij.services;

import com.partola.productsapij.dto.FullProductDTO;
import com.partola.productsapij.dto.PlainProductDTO;
import com.partola.productsapij.entities.Category;
import com.partola.productsapij.entities.Product;
import com.partola.productsapij.exceptions.ProductNotFoundException;
import com.partola.productsapij.exceptions.WalletNotFoundException;
import com.partola.productsapij.mappers.ProductMapper;
import com.partola.productsapij.repositories.CategoriesRepository;
import com.partola.productsapij.repositories.ProductsRepository;
import com.partola.productsapij.repositories.UsersRepository;
import com.partola.productsapij.util.ProductsUtil;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Ivan Partola
 */
@Service
@RequiredArgsConstructor
public class ProductsService {
    private final UsersRepository usersRepository;
    private final ProductsRepository productsRepository;
    private final ProductMapper productMapper;
    private final CategoriesRepository categoriesRepository;
    public List<PlainProductDTO> getAllListingsByWallet(String walletId) {
        if (!usersRepository.existsByWalletId(walletId)) {
            throw new WalletNotFoundException("Wallet with ID " + walletId + " not found.");
        }

        List<Product> products = productsRepository.findByUserWalletId(walletId);
        return products.stream()
                .map(productMapper::toPlainProductDTO)
                .collect(Collectors.toList());
    }

    public List<Category> getAllProductCategories() {
        return categoriesRepository.findAll();
    }
    public FullProductDTO getFullProductById(Long productId) {
        Product product = productsRepository.findByProductId(productId);
        ProductsUtil.exists(productId, product);

        return productMapper.toFullProductDTO(product);
    }
    public PlainProductDTO getPlainProductById(Long productId) {
        Product product = productsRepository.findByProductId(productId);
        ProductsUtil.exists(productId, product);

        return productMapper.toPlainProductDTO(product);
    }
    @Transactional
    public void createProduct(Product product) {
        productsRepository.save(product);
    }

    @Transactional
    public void deleteProductById(Long productId) {
        Product product = productsRepository.findByProductId(productId);
        if (product == null) {
            throw new ProductNotFoundException("Product with id " + productId + " was not found");
        }
        productsRepository.delete(product);
    }
}
