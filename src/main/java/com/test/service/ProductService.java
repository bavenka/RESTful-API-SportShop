package com.test.service;

import com.test.model.dto.ProductDto;
import com.test.model.dto.ReviewDto;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

/**
 * Created by Павел on 15.11.2016.
 */
@Service
public interface ProductService {
    ProductDto editProduct(ProductDto productDto) throws Exception;

    ProductDto addProduct(ProductDto productDto) throws Exception;

    void deleteProduct(Long id) throws Exception;

    ProductDto getProduct(Long productId, Long userId) throws Exception;

    List<ProductDto> getProducts(int offset, int limit, long categoryId) throws Exception;

    Set<ReviewDto> getProductReviews(Long productId) throws Exception;

    ProductDto getProductSpecifications(Long productId) throws Exception;

    ProductDto addCategoryToProduct(Long productId, Long categoryId) throws Exception;

    void deleteCategoryFromProduct(Long productId) throws Exception;
}
