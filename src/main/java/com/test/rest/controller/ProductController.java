package com.test.rest.controller;

import com.test.model.dto.product.ProductDto;
import com.test.model.dto.product.ReviewDto;
import com.test.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

/**
 * Created by Павел on 19.11.2016.
 */
@RestController
@RequestMapping("${route.catalog}")
public class ProductController {
    @Autowired
    private ProductService productService;

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<?> getProducts(@RequestParam("offset") int offset,
                                         @RequestParam("limit") int limit,
                                         @RequestParam("category") long categoryId) throws Exception {

        List<ProductDto> productDtos;
        try {
            productDtos = productService.getProducts(offset, limit, categoryId);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>(productDtos, HttpStatus.OK);
    }

    @RequestMapping(value = "{productId}/user/{userId}", method = RequestMethod.GET)
    public ResponseEntity<?> getProduct(@PathVariable("productId") Long productId,
                                        @PathVariable("userId") Long userId) throws Exception {
        ProductDto productDto;
        try {
            productDto = productService.getProduct(productId, userId);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>(productDto, HttpStatus.OK);
    }

    @RequestMapping(value = "{productId}/reviews", method = RequestMethod.GET)
    public ResponseEntity<?> getProductReviews(@PathVariable("productId") Long productId) throws Exception {
        Set<ReviewDto> reviewDtos;
        try {
            reviewDtos = productService.getProductReviews(productId);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>(reviewDtos, HttpStatus.OK);
    }

    @RequestMapping(value = "update", method = RequestMethod.PUT)
    public ResponseEntity<?> updateProduct(@RequestBody ProductDto productDto,
                                           @RequestHeader(name = "Authorization") String token) throws Exception {
        ProductDto existingProductDto;
        try {
            existingProductDto = productService.editProduct(productDto);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>(existingProductDto, HttpStatus.OK);
    }

    @RequestMapping(value = "add", method = RequestMethod.POST)
    public ResponseEntity<?> addProduct(@RequestBody ProductDto productDto,
                                        @RequestHeader(name = "Authorization") String token) throws Exception {
        ProductDto existingProductDto;
        try {
            existingProductDto = productService.addProduct(productDto);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>(existingProductDto, HttpStatus.OK);
    }

    @RequestMapping(value = "{productId}/add/category/{categoryId}", method = RequestMethod.PUT)
    public ResponseEntity<?> setCategoryToProduct(@PathVariable("productId") Long productId,
                                                  @PathVariable("categoryId") Long categoryId,
                                                  @RequestHeader(name = "Authorization") String token) throws Exception {
        ProductDto existingProductDto;
        try {
            existingProductDto = productService.addCategoryToProduct(productId, categoryId);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>(existingProductDto, HttpStatus.OK);
    }

    @RequestMapping(value = "{productId}/delete/category", method = RequestMethod.PUT)
    public ResponseEntity<?> deleteCategoryFromProduct(@PathVariable("productId") Long productId,
                                                       @RequestHeader(name = "Authorization") String token) throws Exception {
        try {
            productService.deleteCategoryFromProduct(productId);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return ResponseEntity.ok().build();
    }

    @RequestMapping(value = "{id}/delete", method = RequestMethod.DELETE)
    public ResponseEntity<?> deleteProduct(@PathVariable Long id,
                                           @RequestHeader(name = "Authorization") String token) throws Exception {
        try {
            productService.deleteProduct(id);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return ResponseEntity.ok().build();
    }

    @RequestMapping(value = "{productId}/specifications", method = RequestMethod.GET)
    public ResponseEntity<?> getProductSpecifications(@PathVariable("productId") Long id) throws Exception {
        ProductDto productSpecificationsDto;
        try {
            productSpecificationsDto = productService.getProductSpecifications(id);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>(productSpecificationsDto, HttpStatus.OK);
    }

    @RequestMapping(value = "{productId}/add/count", method = RequestMethod.PUT)
    public ResponseEntity<?> setCountToProduct(@PathVariable Long productId,
                                               @RequestParam("count") int count,
                                               @RequestHeader(name = "Authorization") String token) throws Exception {
        ProductDto existingProductDto;
        try {
            existingProductDto = productService.setCountToProduct(productId, count);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>(existingProductDto, HttpStatus.OK);
    }
}
