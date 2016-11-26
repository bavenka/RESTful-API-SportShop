package com.test.service.Impl;

import com.test.model.dto.product.ProductDto;
import com.test.model.entity.auth.User;
import com.test.model.entity.product.Product;
import com.test.repository.ProductRepository;
import com.test.repository.UserRepository;
import com.test.service.WishListService;
import com.test.utils.Constant;
import com.test.utils.Converter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by Павел on 24.11.2016.
 */
@Component
@Transactional
public class WishListServiceImpl implements WishListService{

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private ProductRepository productRepository;

    @Override
    public ProductDto addProductToWishList(Long userId, Long productId) throws Exception {
        User user = userRepository.findOne(userId);
        if (user == null) {
            throw new Exception(Constant.MESSAGE_NOT_FOUND_USER);
        }
        Product product = productRepository.findOne(productId);
        if (product == null) {
            throw new Exception(Constant.MESSAGE_NOT_FOUND_PRODUCT);
        }
        Set<Product> products = user.getWishProducts();
        if (products == null) {
            products = new HashSet<>();
        }
        for (Product productEntity : products) {
            if (productEntity.getId().equals(productId)) {
                throw new Exception("Product is already exists!");
            }
        }
        products.add(product);
        user.setWishProducts(products);
        userRepository.save(user);
        return Converter.toProductWithoutSpecificationsDto(product);
    }

    @Override
    public void deleteProductFromWishList(Long userId, Long productId) throws Exception {
        User user = userRepository.findOne(userId);
        if (user == null) {
            throw new Exception(Constant.MESSAGE_NOT_FOUND_USER);
        }
        Product product = productRepository.findOne(productId);
        if (product == null) {
            throw new Exception(Constant.MESSAGE_NOT_FOUND_PRODUCT);
        }
        Set<Product> products = user.getWishProducts();
        if (products == null) {
            throw new Exception("User has not wish products!");
        }
        Product searchedProduct = null;
        for (Product productEntity : products) {
            if (productEntity.getId().equals(productId)) {
                searchedProduct = productEntity;
                break;
            }
        }
        if (searchedProduct == null) {
            throw new Exception("User has not this wish product!");
        }
        products.remove(searchedProduct);
        user.setWishProducts(products);
        userRepository.save(user);
    }

    @Override
    public Set<ProductDto> getProductsFromWishList(Long userId) throws Exception {
        User user = userRepository.findOne(userId);
        if (user == null) {
            throw new Exception(Constant.MESSAGE_NOT_FOUND_USER);
        }
        if (user.getWishProducts() == null) {
            return null;
        }
        Set<ProductDto> productDtos = new HashSet<>();
        Set<Product> products = user.getWishProducts();
        for (Product product : products) {
            productDtos.add(Converter.toProductWithoutSpecificationsDto(product));
        }
        return productDtos;
    }
}
