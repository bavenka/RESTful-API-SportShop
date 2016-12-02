package com.test.service.Impl;

import com.test.dao.ProductDao;
import com.test.model.dto.ProductDto;
import com.test.model.dto.ReviewDto;
import com.test.model.entity.User;
import com.test.model.entity.Category;
import com.test.model.entity.Product;
import com.test.model.entity.Review;
import com.test.repository.CategoryRepository;
import com.test.repository.ProductRepository;
import com.test.repository.UserRepository;
import com.test.service.ProductService;
import com.test.utils.Constant;
import com.test.utils.Converter;
import lombok.NonNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Created by Павел on 15.11.2016.
 */
@Component
@Transactional
public class ProductServiceImpl implements ProductService {
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private CategoryRepository categoryRepository;
    @Autowired
    private ProductDao productDao;

    @Override
    public ProductDto editProduct(@NonNull ProductDto productDto) throws Exception {
        Product product = productRepository.findOne(productDto.getId());
        if (product == null) {
            throw new Exception(Constant.MESSAGE_NOT_FOUND_PRODUCT);
        }
        product = Converter.toProduct(productDto);
        product.setId(productDto.getId());
        productRepository.save(product);
        return productDto;
    }

    @Override
    public ProductDto addProduct(@NonNull ProductDto productDto) throws Exception {
        Product product = Converter.toProduct(productDto);
        productRepository.save(product);
        productDto.setId(product.getId());
        return productDto;
    }

    @Override
    public void deleteProduct(Long id) throws Exception {
        productRepository.delete(id);
    }

    @Override
    public ProductDto getProduct(Long productId, Long userId) throws Exception {
        Product product = productRepository.findOne(productId);
        if (product == null) {
            return null;
        }
        ProductDto productDto = Converter.toProductWithoutSpecificationsDto(product);
        if (userId != null) {
            User user = userRepository.findOne(userId);
            if (user != null) {
                if (user.getWishProducts() != null) {
                    Set<Product> products = user.getWishProducts();
                    for (Product productEntity : products) {
                        if (productEntity.getId().equals(productId)) {
                            productDto.setIsFavorite(true);
                            break;
                        }
                    }
                }
            }
        }
        return productDto;
    }

    @Override
    public List<ProductDto> getProducts(int offset, int limit, long categoryId) throws Exception {
        List<Product> products;
        List<ProductDto> productDtos = new ArrayList<>();
        if (categoryId != 0) {
            Category category = categoryRepository.findOne(categoryId);
            if (category == null) {
                return null;
            }
            products = category.getProducts().stream().collect(Collectors.toList());
            for (Product product : products) {
                productDtos.add(Converter.toProductWithoutSpecificationsDto(product));
            }
            return productDtos;
        }
        products = productDao.getProducts(offset, limit);
        if (products == null) {
            return null;
        }
        for (Product product : products) {
            productDtos.add(Converter.toProductWithoutSpecificationsDto(product));
        }
        return productDtos;
    }

    @Override
    public Set<ReviewDto> getProductReviews(Long productId) throws Exception {
        Product product = productRepository.findOne(productId);
        if (product == null) {
            throw new Exception(Constant.MESSAGE_NOT_FOUND_PRODUCT);
        }
        Set<Review> reviews = product.getProductReviews();
        if (reviews == null) {
            return null;
        }
        Set<ReviewDto> reviewDtos = new HashSet<>();
        for (Review review : reviews) {
            ReviewDto reviewDto = Converter.toReviewDto(review);
            if (review.getUser() != null) {
                reviewDto.setAuthor(review.getUser().getName());
            }
            reviewDtos.add(reviewDto);
        }
        return reviewDtos;
    }

    @Override
    public ProductDto getProductSpecifications(Long productId) throws Exception {
        Product product = productRepository.findOne(productId);
        if (product == null) {
            throw new Exception(Constant.MESSAGE_NOT_FOUND_PRODUCT);
        }
        return Converter.toProductSpecificationsDto(product);
    }

    @Override
    public ProductDto addCategoryToProduct(Long productId, Long categoryId) throws Exception {
        Product product = productRepository.findOne(productId);
        if (product == null) {
            throw new Exception(Constant.MESSAGE_NOT_FOUND_PRODUCT);
        }
        Category category = categoryRepository.findOne(categoryId);
        if (category == null) {
            throw new Exception(Constant.MESSAGE_NOT_FOUND_CATEGORY);
        }
        product.setCategory(category);
        productRepository.save(product);
        return Converter.toProductWithoutSpecificationsDto(product);
    }

    @Override
    public void deleteCategoryFromProduct(Long productId) throws Exception {
        Product product = productRepository.findOne(productId);
        if (product == null) {
            throw new Exception(Constant.MESSAGE_NOT_FOUND_PRODUCT);
        }
        if (product.getCategory() == null) {
            throw new Exception("Product has not a category!");
        }
        product.setCategory(null);
        productRepository.save(product);
    }
}
