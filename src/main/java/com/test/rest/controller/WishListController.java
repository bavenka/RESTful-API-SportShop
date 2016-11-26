package com.test.rest.controller;

import com.test.model.dto.CategoryDto;
import com.test.model.dto.product.ProductDto;
import com.test.service.WishListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * Created by Павел on 24.11.2016.
 */
@RestController
@RequestMapping("${route.users}")
public class WishListController {
    @Autowired
    private WishListService wishListService;

    @RequestMapping(value = "{userId}/wishlist/add/{productId}", method = RequestMethod.PUT)
    public ResponseEntity<?> addProductToWishList(@PathVariable("userId") Long userId,
                                              @PathVariable("productId") Long productId,
                                              @RequestHeader(name = "Authorization") String token) throws Exception {
        ProductDto existingProductDto;
        try {
            existingProductDto = wishListService.addProductToWishList(userId, productId);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>(existingProductDto, HttpStatus.OK);
    }

    @RequestMapping(value = "{userId}/wishlist/delete/{productId}", method = RequestMethod.PUT)
    public ResponseEntity<?> deleteProductFromWishList(@PathVariable("userId") Long userId,
                                              @PathVariable("productId") Long productId,
                                              @RequestHeader(name = "Authorization") String token) throws Exception {
        try {
            wishListService.deleteProductFromWishList(userId, productId);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return ResponseEntity.ok().build();
    }

}
