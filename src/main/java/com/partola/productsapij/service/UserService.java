package com.partola.productsapij.service;

import com.partola.productsapij.exception.WalletNotFoundException;
import com.partola.productsapij.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public void assertUserExistsByWalletId(String walletId) {
        if (!userRepository.existsByWalletId(walletId)) {
            throw new WalletNotFoundException(walletId);
        }
    }
}
