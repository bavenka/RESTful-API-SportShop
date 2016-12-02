package com.test.service;

import com.test.model.dto.ProductDto;
import org.springframework.stereotype.Service;

import java.util.Set;

/**
 * Created by Pavel on 28.11.2016.
 */
@Service
public interface CartService {
    ProductDto addProductToCart(Long userId, Long productId) throws Exception;

    void deleteProductFromCart(Long userId, Long productId) throws Exception;

    Set<ProductDto> getProductsFromCart(Long userId) throws Exception;
}
