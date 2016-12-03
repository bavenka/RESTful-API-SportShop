package com.test.rest.controller;

import com.test.model.dto.ProductDto;
import com.test.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

/**
 * Created by Pavel on 29.11.2016.
 */
@RestController
@RequestMapping("${route.users}")
public class CartController {

    @Autowired
    private CartService cartService;

    @RequestMapping(value = "{userId}/cart/add/{productId}", method = RequestMethod.POST)
    public ResponseEntity<?> addProductToCart(@PathVariable("userId") Long userId,
                                              @PathVariable("productId") Long productId,
                                              @RequestHeader(name = "Authorization") String token) throws Exception {
        ProductDto existingProductDto;
        try {
            existingProductDto = cartService.addProductToCart(userId, productId);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>(existingProductDto, HttpStatus.OK);
    }

    @RequestMapping(value = "{userId}/cart/delete/{productId}", method = RequestMethod.PUT)
    public ResponseEntity<?> deleteProductFromCart(@PathVariable("userId") Long userId,
                                                   @PathVariable("productId") Long productId,
                                                   @RequestHeader(name = "Authorization") String token) throws Exception {
        try {
            cartService.deleteProductFromCart(userId, productId);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return ResponseEntity.ok().build();
    }

    @RequestMapping(value = "{userId}/cart", method = RequestMethod.GET)
    public ResponseEntity<?> getProductsFromCart(@PathVariable("userId") Long userId) throws Exception {
        Set<ProductDto> productDtos;
        try {
            productDtos = cartService.getProductsFromCart(userId);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>(productDtos, HttpStatus.OK);
    }
}
