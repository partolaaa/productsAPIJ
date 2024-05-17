package com.partola.productsapij.services;

import com.partola.productsapij.dto.PlainProductDTO;
import com.partola.productsapij.entities.Category;
import com.partola.productsapij.entities.Product;
import com.partola.productsapij.exceptions.WalletNotFoundException;
import com.partola.productsapij.mappers.ProductMapper;
import com.partola.productsapij.repositories.CategoriesRepository;
import com.partola.productsapij.repositories.ProductsRepository;
import com.partola.productsapij.repositories.UsersRepository;
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
}
