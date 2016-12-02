package com.test.service;

import com.test.model.dto.ProductDto;
import org.springframework.stereotype.Service;

import java.util.Set;

/**
 * Created by Павел on 24.11.2016.
 */
@Service
public interface WishListService {
    ProductDto addProductToWishList(Long userId, Long productId) throws Exception;

    void deleteProductFromWishList(Long userId, Long productId) throws Exception;

    Set<ProductDto> getProductsFromWishList(Long userId) throws Exception;
}
