package com.test.service.Impl;

import com.test.model.dto.ProductDto;
import com.test.model.entity.User;
import com.test.model.entity.Product;
import com.test.repository.ProductRepository;
import com.test.repository.UserRepository;
import com.test.service.CartService;
import com.test.utils.Constant;
import com.test.utils.Converter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by Pavel on 28.11.2016.
 */
@Component
@Transactional
public class CartServiceImpl implements CartService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private ProductRepository productRepository;

    @Override
    public ProductDto addProductToCart(Long userId, Long productId) throws Exception {
        User user = userRepository.findOne(userId);
        if (user == null) {
            throw new Exception(Constant.MESSAGE_NOT_FOUND_USER);
        }
        Product product = productRepository.findOne(productId);
        if (product == null) {
            throw new Exception(Constant.MESSAGE_NOT_FOUND_PRODUCT);
        }
        Set<Product> products = user.getCartProducts();
        if (products == null) {
            products = new HashSet<>();
        }
        products.add(product);
        user.setCartProducts(products);
        userRepository.save(user);
        return Converter.toProductWithoutSpecificationsDto(product);
    }

    @Override
    public void deleteProductFromCart(Long userId, Long productId) throws Exception {
        User user = userRepository.findOne(userId);
        if (user == null) {
            throw new Exception(Constant.MESSAGE_NOT_FOUND_USER);
        }
        Set<Product> products = user.getCartProducts();
        if (products == null) {
            throw new Exception(Constant.MESSAGE_EMPTY_CART);
        }
        Product searchedProduct = null;
        for (Product existingProduct : products) {
            if (existingProduct.getId().equals(productId)) {
                searchedProduct = existingProduct;
                break;
            }
        }
        if (searchedProduct == null) {
            throw new Exception("Shopping cart has not this product!");
        }
        products.remove(searchedProduct);
        user.setCartProducts(products);
        userRepository.save(user);
    }

    @Override
    public Set<ProductDto> getProductsFromCart(Long userId) throws Exception {
        User user = userRepository.findOne(userId);
        if (user == null) {
            throw new Exception(Constant.MESSAGE_NOT_FOUND_USER);
        }
        if (user.getCartProducts() == null) {
            return null;
        }
        Set<ProductDto> productDtos = new HashSet<>();
        Set<Product> products = user.getCartProducts();
        for (Product product : products) {
            productDtos.add(Converter.toProductWithoutSpecificationsDto(product));
        }
        return productDtos;
    }
}
